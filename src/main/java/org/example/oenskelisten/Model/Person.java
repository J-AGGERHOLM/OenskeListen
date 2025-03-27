package org.example.oenskelisten.Model;

public class Person {
    private int personId;
    private String name;
    private String email;
    private String password;

    public Person(int personId,String name, String email, String password){
        this.personId = personId;
        this.name=name;
        this.email=email;
        this.password = password;
    }



    @Override
    public String toString(){
        return personId + name + email + password;
    }

}
