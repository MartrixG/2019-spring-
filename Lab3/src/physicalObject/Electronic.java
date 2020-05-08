package physicalObject;

public class Electronic extends ConcretePhysicalObject {
    public Electronic(Integer No) {
         this.Name=No.toString();
    }

    @Override
    public int hashCode(){
        return Name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(this.hashCode()==o.hashCode()){
            return true;
        }else{
            return false;
        }
    }
}
