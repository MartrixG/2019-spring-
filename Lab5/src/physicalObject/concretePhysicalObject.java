package physicalObject;

abstract class ConcretePhysicalObject implements PhysicalObject {
    protected String Name;

    @Override
    public String getName() {
        return this.Name;
    }

    @Override
    public int hashCode() {
        return this.Name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return this.hashCode() == o.hashCode();
    }
}
