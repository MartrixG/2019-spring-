package centralObject;

/**
 * @author Martrix
 * This interface is to describe a center object in a circular system.
 * It must be immutable.
 */
public interface CentralObject {
    /**
     * To get the name of this object
     *
     * @return A string of name.
     */
    String getName();
}
