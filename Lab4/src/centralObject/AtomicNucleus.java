package centralObject;

import exception.InputException;

public class AtomicNucleus extends ConcreteCentralObject {
    public AtomicNucleus(String S) throws InputException {
        if(S.length()>2){
            throw new InputException("ElementName is illegal.");
        }
        if(S.length()==2){
            if(S.charAt(1)<'a'||S.charAt(1)>'z'){
                throw new InputException("ElementName is illegal.");
            }
        }
        if(S.charAt(0)<'A'||S.charAt(0)>'Z'){
            throw new InputException("ElementName is illegal.");
        }
        this.name=S;
    }
}
