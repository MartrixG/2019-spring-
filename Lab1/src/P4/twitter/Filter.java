/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import java.util.ArrayList;
import java.util.List;

import P4.twitter.Tweet;

/**
 * Filter consists of methods that filter a list of tweets for those matching a
 * condition.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Filter {

    /**
     * Find tweets written by a particular user.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param username
     *            Twitter username, required to be a valid Twitter username as
     *            defined by Tweet.getAuthor()'s spec.
     * @return all and only the tweets in the list whose author is username,
     *         in the same order as in the input list.
     */
    public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
        List<Tweet> ansList=new ArrayList<Tweet>();
    	for(Tweet tempTweet:tweets) {
        	if(tempTweet.getAuthor().equals(username)) {
        		ansList.add(tempTweet);
        	}
        }
    	return ansList;
    	//throw new RuntimeException("not implemented");
    }

    /**
     * Find tweets that were sent during a particular timespan.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param timespan
     *            timespan
     * @return all and only the tweets in the list that were sent during the timespan,
     *         in the same order as in the input list.
     */
    public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
        List<Tweet> ansList=new ArrayList<Tweet>();
    	for(Tweet tempTweet:tweets) {
    		if(tempTweet.getTimestamp().isAfter(timespan.getStart())&&tempTweet.getTimestamp().isBefore(timespan.getEnd())) {
    			ansList.add(tempTweet);
    		}
        }
    	return ansList;
    	//throw new RuntimeException("not implemented");
    }

    /**
     * Find tweets that contain certain words.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param words
     *            a list of words to search for in the tweets. 
     *            A word is a nonempty sequence of nonspace characters.
     * @return all and only the tweets in the list such that the tweet text (when 
     *         represented as a sequence of nonempty words bounded by space characters 
     *         and the ends of the string) includes *at least one* of the words 
     *         found in the words list. Word comparison is not case-sensitive,
     *         so "Obama" is the same as "obama".  The returned tweets are in the
     *         same order as in the input list.
     */
    public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
    	List<Tweet> ansList=new ArrayList<Tweet>();
    	for(Tweet eachTweet:tweets) {
    		List<String> tweetWords=new ArrayList<String>();
    		String eachText=eachTweet.getText();
    		int wordBegin=0,wordEnd=0;
    		while(wordBegin<eachText.length()) {
    			wordEnd=wordBegin+1;
    			while(wordEnd<eachText.length()&&eachText.charAt(wordEnd)!=' ') {
    				wordEnd++;
    			}
    			tweetWords.add(eachText.substring(wordBegin+1, wordEnd).toLowerCase());
    			wordBegin=wordEnd;
    		}
    		for(int i=0;i<tweetWords.size();i++) {
    			for(int j=0;j<words.size();j++) {
    				if(tweetWords.get(i).equals(words.get(j).toLowerCase())) {
    					ansList.add(eachTweet);
    					break;
    				}
    			}
    		}
    	}
    	return ansList;
    	//throw new RuntimeException("not implemented");
    }

}
