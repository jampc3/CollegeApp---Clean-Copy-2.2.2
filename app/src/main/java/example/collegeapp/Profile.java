package example.collegeapp;

public class Profile extends ApplicantData {
    private String firstName;
    private String lastName;
    private String date;
    private String Backendless;

    public String getBackendless() {
        return Backendless;
    }

    public void setBackendless(String backendless) {
        Backendless = backendless;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
