package track;

public class ElectronShells extends CircleTrack {
    public ElectronShells(int n) {
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

    @Override
    public ElectronShells clone(){
        ElectronShells tmpShell = new ElectronShells(noNumber);
        return tmpShell;
    }
}
