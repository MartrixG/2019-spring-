package physicalObject;

import exception.InputException;
import person.Person;
import types.socialNetworkTypes.PersonType;

import java.util.List;

public class Friend extends concretePhysicalObject implements Person {
    protected Integer year;
    protected String sex;

    public Friend(String args){
        this.Name=args;
    }

    public Friend(List<String> args) throws InputException {
        try{
            this.Name=args.get(0);
            this.year=Integer.valueOf(args.get(1));
            this.sex=args.get(2);
        }catch(Exception e){
            throw new InputException("Args of friend is illegal.");
        }
    }

    public Friend(String[] args) throws InputException {
        try{
            this.Name=args[0];
            this.year=Integer.valueOf(args[1]);
            this.sex=args[2];
        }catch(Exception e){
            throw new InputException("Args of friend is illegal.");
        }
    }

    @Override
    public PersonType getType(){
        return PersonType.Friend;
    }
}
