package track;

public class GameTrack extends CircleTrack{
    public GameTrack(int n, Double ra) {
        super(n);
        this.radius=ra;
    }
    public GameTrack(int n) {
        super(n);
    }

    @Override
    public int hashCode(){
        //base 7
        int reInt=0;
        reInt+=noNumber.hashCode();
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
