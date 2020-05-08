/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import jdk.internal.dynalink.beans.StaticClass;

import java.util.regex.Matcher;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
    	Instant minTime=Instant.now();
    	Instant maxTime=Instant.now();
    	minTime=tweets.get(0).getTimestamp();
    	maxTime=tweets.get(0).getTimestamp();
    	for(Tweet eachOfTweet:tweets) {
    		if(eachOfTweet.getTimestamp().isAfter(maxTime)) {
    			maxTime=eachOfTweet.getTimestamp();
    		}
    		if(eachOfTweet.getTimestamp().isBefore(minTime)) {
    			minTime=eachOfTweet.getTimestamp();
    		}
    	}
    	Timespan ans=new Timespan(minTime, maxTime);
    	return ans;
        //throw new RuntimeException("not implemented");
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
    	Set<String> userNameSet=new HashSet<String>();
    	for(Tweet eachOfTweet:tweets) {
    		userNameSet.addAll(getMentionedUsers(eachOfTweet));
    	}
    	return userNameSet;
    }
    public static Set<String> getMentionedUsers(Tweet tweets){
    	Set<String> userNameSet=new HashSet<String>();
    	Pattern patternString=Pattern.compile("@[0-9,a-z,A-Z,-,_]+($| |:)");
    	Matcher matcherString=patternString.matcher(tweets.getText());
    	while(matcherString.find()) {
			String tempName=new String();
			tempName=matcherString.group();
			userNameSet.add(tempName.toLowerCase().substring(1,tempName.length()).replace(" ", "").replace(":", ""));
		}
    	return userNameSet;
    }
}
