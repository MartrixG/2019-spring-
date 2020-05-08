package physicalObject;

import exception.InputException;

import java.util.List;

public class Athlete extends ConcretePhysicalObject {
    private String country;
    private int number;
    private int year;
    private double bestRecord;

    public Athlete(List<String> args) throws InputException {
        try {
            this.Name = args.get(0);
            this.number = Integer.valueOf(args.get(1));
            this.country = args.get(2);
            this.year = Integer.valueOf(args.get(3));
            this.bestRecord = Double.valueOf(args.get(4));
        } catch (Exception e) {
            throw new InputException("Args of athlete is illegal.");
        }
    }

    public Athlete(String[] args) throws InputException {
        try {
            this.Name = args[0];
            this.number = Integer.valueOf(args[1]);
            this.country = args[2];
            this.year = Integer.valueOf(args[3]);
            this.bestRecord = Double.valueOf(args[4]);
        } catch (Exception e) {
            throw new InputException("Args of athlete is illegal.");
        }
    }

    public Athlete(String args) {
        this.Name = args;
    }

    public double getBestRecord() {
        return this.bestRecord;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("<");
        s.append(Name + ",");
        s.append(number + ",");
        s.append(country + ",");
        s.append(year + ",");
        s.append(bestRecord);
        s.append(">");
        return s.toString();
    }
}
