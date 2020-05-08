package physicalObject;

import exception.InputException;

import java.util.List;

public class Athlete extends concretePhysicalObject {
    private String Country;
    private int Number;
    private int Year;
    private double bestRecord;

    public Athlete(List<String> args) throws InputException {
        try{
            this.Name=args.get(0);
            this.Number=Integer.valueOf(args.get(1));
            this.Country=args.get(2);
            this.Year=Integer.valueOf(args.get(3));
            this.bestRecord=Double.valueOf(args.get(4));
        }catch(Exception e){
            throw new InputException("Args of athlete is illegal.");
        }
    }

    public Athlete(String[] args) throws InputException {
        try{
            this.Name=args[0];
            this.Number=Integer.valueOf(args[1]);
            this.Country=args[2];
            this.Year=Integer.valueOf(args[3]);
            this.bestRecord=Double.valueOf(args[4]);
        }catch (Exception e){
            throw new InputException("Args of athlete is illegal.");
        }
    }

    public Athlete(String args){
        this.Name=args;
    }

    public double getBestRecord() {
        return this.bestRecord;
    }
}
