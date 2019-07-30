package example.collegeapp;

public class Guardian extends FamilyMember {

    String occupation;

    public Guardian(){
        super();
        occupation = "Default occupation";
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }



}
