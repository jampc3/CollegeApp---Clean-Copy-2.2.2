package example.collegeapp;

import androidx.fragment.app.Fragment;

public abstract class FamilyMember extends ApplicantData {
    private String firstName;
    private String lastName;

    public FamilyMember() {
        firstName = "First Name Default";
        lastName = "Last Name Default";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
