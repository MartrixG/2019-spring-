package centralObject;

import exception.InputException;

public class AtomicNucleus extends ConcreteCentralObject {
    public AtomicNucleus(String s) throws InputException {
        if (s.length() > 2) {
            throw new InputException("ElementName is illegal.");
        }
        if (s.length() == 2) {
            if (s.charAt(1) < 'a' || s.charAt(1) > 'z') {
                throw new InputException("ElementName is illegal.");
            }
        }
        if (s.charAt(0) < 'A' || s.charAt(0) > 'Z') {
            throw new InputException("ElementName is illegal.");
        }
        this.name = s;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
