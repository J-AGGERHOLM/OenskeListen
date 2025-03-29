package org.example.oenskelisten.Model;

public class User {
    private final int personId;
    private String name;
    private String email;
    private String password;

    public User(int personId, String name, String email, String password){
        this.personId = personId;
        this.name=name;
        this.email=email;
        this.password = password;
    }

    public User(){
    }

    public int getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return "ID: " + personId + " name: " + name + " email: " + email + " Password: " + password;
    }

}
