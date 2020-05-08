package centralObject;

import exception.InputException;
import person.Person;
import types.socialNetworkTypes.PersonType;

import java.util.List;

public class CentralUser extends ConcreteCentralObject implements Person {
    private int year;
    private String sex;

    public CentralUser(List<String> args) throws InputException {
        try{
            this.name=args.get(0);
            this.year=Integer.valueOf(args.get(1));
            this.sex=args.get(2);
        }catch(Exception e){
            throw new InputException("Args of center user is illegal.");
        }
    }

    @Override
    public PersonType getType(){
        return PersonType.CentralUser;
    }
}
