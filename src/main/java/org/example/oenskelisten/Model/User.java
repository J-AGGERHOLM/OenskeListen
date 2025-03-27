package org.example.oenskelisten.Model;

public class User {
    private final int personId;
    private final String name;
    private final String email;
    private final String password;

    public User(int personId, String name, String email, String password){
        this.personId = personId;
        this.name=name;
        this.email=email;
        this.password = password;
    }



    @Override
    public String toString(){
        return "ID: " + personId + " name: " + name + " email: " + email + " Password: " + password;
    }

}
