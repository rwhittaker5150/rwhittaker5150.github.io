package twu.whittaker.UserInfo;

public class UserInfo {
    //  Fields
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String userid;
    //private String favLocation;
    private String password;

    //  Constructors
    public UserInfo() {}
    public UserInfo(int CustomerId, String FirstName, String LastName, String E_mail, String Userid, String Pwd) {
        this.customerId = CustomerId;
        this.firstName = FirstName;
        this.lastName = LastName;
        this.email = E_mail;
        this.userid = Userid;
        this.password = Pwd;
        //this.favLocation = FavLocation;

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


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
/*
    public String getFavLocation() {
        return favLocation;
    }

    public void setFavLocation(String favLocation) {
        this.favLocation = favLocation;
    }
 */

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
