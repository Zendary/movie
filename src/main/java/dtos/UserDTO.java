package dtos;

import entities.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDTO {

    private String id;
    private String userName;
    private String phone;
    private String email;
    private String status;
    private String password;
    private List<String> roles;

    public UserDTO() {
    }

    public UserDTO(User u) {
        this.userName = u.getUserName();
        this.phone = u.getPhone();
        this.email = u.getEmail();
        this.status = u.getStatus();
    }

    public static List<UserDTO> getDtos(List<User> users){
        return users.stream().map(u -> new UserDTO(u)).collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
