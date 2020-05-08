package strategy;

import javafx.util.Pair;
import physicalObject.Athlete;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class

RecordStrategy implements Strategy {
    @Override
    public Map<Athlete, Pair<Integer, Integer>> doOperation(List<Athlete> athletes, int numOfTrack) {
        Map<Athlete, Pair<Integer, Integer>>reMap = new HashMap<>();
        athletes.sort((Athlete x, Athlete y)->(new Integer((x.getBestRecord()-y.getBestRecord())>0?1:-1)));
        int numOfGroup = athletes.size()/numOfTrack;
        if(athletes.size()%numOfTrack!=0) numOfGroup++;
        int cur=0;
        for(int i=0;i<numOfGroup;i++) {
            int tmpTrack=(numOfTrack+1)/2;
            for(int j=0;j<numOfTrack;j++){
                tmpTrack+=((j&1)==1)?-j:j;
                reMap.put(athletes.get(cur), new Pair(i, tmpTrack-1));
                cur++;
                if(cur==athletes.size()){
                    return reMap;
                }
            }
        }
        return reMap;
    }
}
