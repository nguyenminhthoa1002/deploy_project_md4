package ra.payload.request;

import java.time.LocalDateTime;
import java.util.Set;

public class RegisterRequest {
    private String userName;
    private String password;
    private String avatar;
    private String lastName;
    private String firstName;
    private String email;
    private String phone;
    private String address;
    private LocalDateTime userCreateDate;
    private boolean userStatus;
    private Set<String> listRoles;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getUserCreateDate() {
        return userCreateDate;
    }

    public void setUserCreateDate(LocalDateTime userCreateDate) {
        this.userCreateDate = userCreateDate;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    public Set<String> getListRoles() {
        return listRoles;
    }

    public void setListRoles(Set<String> listRoles) {
        this.listRoles = listRoles;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
