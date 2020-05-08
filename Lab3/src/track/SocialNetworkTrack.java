package track;

public class SocialNetworkTrack extends CircleTrack{
    public SocialNetworkTrack(int n) {
        super(n);
    }

    @Override
    public int hashCode(){
        //base 7
        int reInt=0;
        reInt+=noNumber.hashCode();
        reInt+=7*Type.hashCode();
        reInt+=7*7*radius.hashCode();
        return reInt;
    }

    @Override
    public boolean equals(Object o){
        if(o.hashCode()==this.hashCode()){
            return true;
        }
        else{
            return false;
        }
    }
}
