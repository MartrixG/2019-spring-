package relationship;

public class SocialNetworkRelationship implements RelationShip {
    private Double Intimacy;
    public SocialNetworkRelationship(String arg){
        this.Intimacy=Double.valueOf(arg);
    }
    @Override
    public String toString(){
        return Intimacy.toString();
    }
    public Double getIntimacy(){
        return new Double(Intimacy);
    }
}
