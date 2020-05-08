package physicalObject;

public abstract class ConcretePhysicalObject implements PhysicalObject {
    protected String Name;
    @Override
    public String getName() {
        return this.Name;
    }
}
