package centralObject;

public class TrackGameNull extends ConcreteCentralObject {
    private final String Name = "null";

    public TrackGameNull() {
    }

    @Override
    public String getName() {
        return Name;
    }
}
