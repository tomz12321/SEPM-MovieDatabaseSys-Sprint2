
/**
 * Write a description of class User here.
 * 
 * @author Jyh-woei Yang
 * @version 23/04/2018
 */
public class User
{
    // instance variables - replace the example below with your own
    private String name;
    private String userName;
    private String password;

    /**
     * Constructor for objects of class User
     */
    public User()
    {
        // initialise instance variables
        this.name = "";
        this.userName = "";
        this.password = "";
    }
    
    public User(String name, String userName, String password)
    {
        // initialise instance variables
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    public void setUserName(String name)
    {
        // setter of userName
        this.userName = name;
    }
    
    public void setName(String name)
    {
        // setter of name 
        this.name = name;
    }
    
    public void setPassword(String password)
    {
        // setter of password
        this.password = password;
    }
    
    public String getUserName()
    {
        // getter of userName
        return this.userName;
    }
    
    public String getPassword()
    {
        // getter of password
        return this.password;
    }
    
    public String getName()
    {
        // getter of name
        return this.name;
    }
   
    public void display(){
        
        System.out.println("Name:" + this.name);
        System.out.println("UserName:" + this.userName);
        System.out.println("Password:" + this.password);
    }
}
