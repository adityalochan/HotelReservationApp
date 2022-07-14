package model;

import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    private String regex = "^(.+)@(.+)$";
    private Pattern pattern = Pattern.compile(regex);

    public Customer(String firstName, String lastName, String email){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        if(!(pattern.matcher(this.email).matches())){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString(){
        return "Customer '" + firstName + " " + lastName + "' has valid email address ";
    }

}
