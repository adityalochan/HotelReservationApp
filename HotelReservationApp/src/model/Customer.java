package model;

import java.util.regex.Pattern;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String regex = "^(.+)@(.+)$";
    private final Pattern pattern = Pattern.compile(regex);

    public Customer(final String firstName, final String lastName, final String email){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        if(!(pattern.matcher(email).matches())){
            throw new IllegalArgumentException("The email address is invalid ");
        }
    }

    @Override
    public String toString(){
        return "Customer::" + this.firstName + " " + this.lastName + " Email::"+this.email;
    }

    public String getEmail(){return this.email;}
}
