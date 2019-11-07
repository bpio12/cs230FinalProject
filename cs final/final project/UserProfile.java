import java.util.Scanner;
/**
 * UserProfile creates a UserProfile object which contains information about
 * the child who will be using our program to calculate their language proficiency
 *
 * @author bpio
 * @version 5/11/2019
 */
public class UserProfile
{
    private Scanner s;
    private String name;
    private int age;
    private String gender;
    public UserProfile(String n, int a, String g){
       
        this.name = n;
        this.age = a;
        this.gender = g;
    }
    
    public UserProfile(){
        name = "";
        age = 0;
        gender = "";
    }
    
    /**
     * Obtains the age of the child
     * 
     * @return int the age of the child
     */
    public int getAge(){
        //This method will retrieve the age of the child that the user input, 
        //in order to easily access the data corresponding to that age.
        return age;
    }
    
 /**
     * Sets the age of the child
     * 
     * @param int the age of the child
     */
    public void setAge(int a){
        //This method will set the age variable of the child to a certain value.
        age = a;
    }

     /**
     * Obtains the gender of the child
     * 
     * @return String the gender of the child
     */
    public String getGender(){
        //This method will retrieve the gender of the child 
        //that the user input in order to access the data corresponding to that gender.
        return gender;
    }

    /**
     * Sets the gender of the child
     * 
     * @param String the gender of the child
     */
    public void setGender(String g){
        //This method will set the gender of the child to a certain String.
        gender = g;
    }
    
    /**
     * Sets the name of the child
     * 
     * @param String the name of the child
     */
    public void setName(String n){
        //This method will set the name of the child to a certain String.
        name = n;
    }


    public String toString(){
        return "Information on the given child: \n" + " name: "+ name 
        + "\n age: " + age + "\n gender: " + gender ;
    }
    
    public static void main(String[] args){
        UserProfile lilJohn = new UserProfile("bob", 16, "male");
        System.out.println(lilJohn);
    }
}