package physicalObject;

import person.Person;
import types.socialNetworkTypes.PersonType;

import java.util.ArrayList;
import java.util.List;

public class Friend extends ConcretePhysicalObject implements Person {
    protected Integer year;
    protected String sex;

    public Friend(String args){
        this.Name=args;
    }

    public Friend(List<String> args){
        this.Name=args.get(0);
        this.year=Integer.valueOf(args.get(1));
        this.sex=args.get(2);
    }

    public Friend(String[] args){
        this.Name=args[0];
        this.year=Integer.valueOf(args[1]);
        this.sex=args[2];
    }

    @Override
    public Friend clone(){
        List<String> args=new ArrayList<>();
        args.add(this.Name);
        args.add(this.year.toString());
        args.add(this.sex);
        return new Friend(args);
    }

    @Override
    public int hashCode(){
        return this.Name.hashCode();
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
        return PersonType.Friend;
    }
}
