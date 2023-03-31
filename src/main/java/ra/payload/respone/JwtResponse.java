package ra.payload.respone;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ra.model.entity.Orders;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String userName;
    private String avatar;
    private String lastName;
    private String firstName;
    private String email;
    private String phone;
    private String address;
    @JsonIgnore
    private Orders orders;
    private List<String> listRoles;

    public JwtResponse(String token, String userName, String avatar, String lastName, String firstName, String email, String phone, String address, Orders orders, List<String> listRoles) {
        this.token = token;
        this.userName = userName;
        this.avatar = avatar;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.orders = orders;
        this.listRoles = listRoles;
    }


//    public JwtResponse(String token, String userName, String lastName, String firstName, String email, String phone, String address, List<String> listRoles) {
//        this.token = token;
//        this.userName = userName;
//        this.lastName = lastName;
//        this.firstName = firstName;
//        this.email = email;
//        this.phone = phone;
//        this.address = address;
//        this.listRoles = listRoles;
//    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public List<String> getListRoles() {
        return listRoles;
    }

    public void setListRoles(List<String> listRoles) {
        this.listRoles = listRoles;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
