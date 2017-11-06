package Project3GitDoneRight;
public class Employee {
    private String userType;
    private String phoneNumber;
    private String username;
    private String password;
    private String email;




    public String getUserType() {
        return userType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUsername() {
        return username;
    }


    public void setPassword(String pass){
        this.password = pass;
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


    public Employee(String uType, String pNum,  String Email, String uName, String Pword) {
        this.userType = uType;
        this.phoneNumber = pNum;
        this.username = uName;
        this.password = Pword;
        this.email = Email;
    }

    public Employee() {
        this.userType = "SysAdmin";
        this.phoneNumber = "4341234444";
        this.email = "J@J.com";
        this.username = "JohnSmith";
        this.password = "A";
    }

    @Override
    public String toString(){
        return userType + "," + phoneNumber+ "," + email +","+ username+"," + password;
    }
}

