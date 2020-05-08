package centralObject;

import person.Person;
import types.socialNetworkTypes.PersonType;

import java.util.List;

public class CentralUser extends ConcreteCentralObject implements Person {
    private int year;
    private String sex;

    public CentralUser(List<String> args){
        this.name=args.get(0);
        this.year=Integer.valueOf(args.get(1));
        this.sex=args.get(2);
    }

    @Override
    public int hashCode(){
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object o){
        if(this.hashCode()==o.hashCode()){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public PersonType getType(){
        return PersonType.CentralUser;
    }
}
