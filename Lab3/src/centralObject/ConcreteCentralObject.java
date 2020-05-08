package centralObject;

public abstract class ConcreteCentralObject implements CentralObject{
    protected String name;
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public int hashCode(){
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object o){
        if(this.hashCode()==o.hashCode()){
            return true;
        }else{
            return false;
        }
    }
}
