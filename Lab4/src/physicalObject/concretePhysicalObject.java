package physicalObject;

public abstract class concretePhysicalObject implements PhysicalObject {
    protected String Name;
    @Override
    public String getName() {
        return this.Name;
    }

    @Override
    public int hashCode(){
        return this.Name.hashCode();
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
