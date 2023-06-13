package entities;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.deleteAllRows", query = "DELETE from User"),
        @NamedQuery(name = "User.getAllUsers", query = "SELECT u FROM User u"),
        @NamedQuery(name = "User.getUserByUsername", query = "select u from User u WHERE u.userName = :username")
})
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Long id;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "user_name")
  private String userName;

  @Size(max = 20)
  @Column(name = "phone")
  private String phone;

  @Size(max = 255)
  @Column(name = "email")
  private String email;

  @Column(name = "status")
  private String status;

  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "user_pass")
  private String userPass;

  @JoinTable(name = "user_roles", joinColumns = {
          @JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {
          @JoinColumn(name = "role_name", referencedColumnName = "role_name")})
  @ManyToMany
  private List<Role> roleList = new ArrayList<>();

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "user_show",
          joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "show_id", referencedColumnName = "id"))
  private List<Show> shows = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "festival_id")
  private Festival festival;

  public List<String> getRolesAsStrings() {
    if (roleList.isEmpty()) {
      return null;
    }
    List<String> rolesAsStrings = new ArrayList<>();
    roleList.forEach((role) -> {
      rolesAsStrings.add(role.getRoleName());
    });
    return rolesAsStrings;
  }

  public User() {
  }

  public User(String userName, String userPass) {
    this.userName = userName;
    this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());
  }

  public User(String userName, String phone, String email, String status, String userPass) {
    this.userName = userName;
    this.phone = phone;
    this.email = email;
    this.status = status;
    this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());
  }

  public boolean verifyPassword(String pw) {
    return BCrypt.checkpw(pw, userPass);
  }

  // Getters and Setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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

  public String getUserPass() {
    return userPass;
  }

  public void setUserPass(String userPass) {
    this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());
  }

  public List<Role> getRoleList() {
    return roleList;
  }

  public void setRoleList(List<Role> roleList) {
    this.roleList = roleList;
  }

  public void addRole(Role userRole) {
    roleList.add(userRole);
  }

  public List<Show> getShows() {
    return shows;
  }

  public void setShows(List<Show> shows) {
    this.shows = shows;
  }

  public Festival getFestival() {
    return festival;
  }

  public void setFestival(Festival festival) {
    this.festival = festival;
  }

  // toString method
  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", userName='" + userName + '\'' +
            ", phone='" + phone + '\'' +
            ", email='" + email + '\'' +
            ", status='" + status + '\'' +
            ", userPass='" + userPass + '\'' +
            ", roleList=" + roleList +
            ", shows=" + shows +
            ", festival=" + festival +
            '}';
  }
}
