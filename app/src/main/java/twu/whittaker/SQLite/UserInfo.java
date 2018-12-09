package twu.whittaker.SQLite;

public class UserInfo {
    //  Fields
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;

    //  Constructors
    public UserInfo() {}
    public UserInfo(int CustomerId, String FirstName, String LastName, String Email, String UserName, String Password){
        this.customerId = CustomerId;
        this.firstName = FirstName;
        this.lastName = LastName;
        this.email = Email;
        this.userName = UserName;
        this.password = Password;
    }

    //  Properties
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
