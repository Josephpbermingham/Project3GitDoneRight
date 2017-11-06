package Project3GitDoneRight;
public class Employee {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword(boolean a) {
        if (a)
            return password;
        else
            return "";
    }

    public String getEmail() {
        return email;
    }


    public Employee(String fName, String lName, String uName, String Pword, String Email) {
        this.firstName = fName;
        this.lastName = lName;
        this.username = uName;
        this.password = Pword;
        this.email = Email;
    }

    public Employee() {
        this.firstName = "John";
        this.lastName = "doe";
        this.username = "SysAdmin";
        this.password = "A";
        this.email = "J@J.com";
    }
}
