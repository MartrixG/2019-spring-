package centralObject;

public class AtomicNucleus extends ConcreteCentralObject {
    private Integer neutron;
    private Integer proton;
    public AtomicNucleus(String S){
        this.name=S;
    }
    public AtomicNucleus(String S,int n,int p){
        this.name=S;
        this.neutron=n;
        this.proton=p;
    }

    @Override
    public AtomicNucleus clone(){
        return new AtomicNucleus(name);
    }
}
