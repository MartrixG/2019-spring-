package physicalObject;

import java.util.List;

public class Athlete extends ConcretePhysicalObject{
    protected String Country;
    protected int Number;
    protected int Year;
    protected double bestRecord;

    public Athlete(List<String> args){
        this.Name=args.get(0);
        this.Number=Integer.valueOf(args.get(1));
        this.Country=args.get(2);
        this.Year=Integer.valueOf(args.get(3));
        this.bestRecord=Double.valueOf(args.get(4));
    }

    public Athlete(String[] args){
        this.Name=args[0];
        this.Number=Integer.valueOf(args[1]);
        this.Country=args[2];
        this.Year=Integer.valueOf(args[3]);
        this.bestRecord=Double.valueOf(args[4]);
    }

    public Athlete(String args){
        this.Name=args;
    }

    @Override
    public int hashCode(){
        return this.Name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(this.hashCode()==o.hashCode()){
            return true;
        }else{
            return false;
        }
    }

    public double getBestRecord() {
        return this.bestRecord;
    }
}
