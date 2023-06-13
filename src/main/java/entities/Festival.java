package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "festivals")
@NamedQueries({
        @NamedQuery(name = "Festival.deleteAllRows", query = "DELETE from Festival"),
        @NamedQuery(name = "Festival.getAllFestivals", query = "SELECT f FROM Festival f"),
})
public class Festival {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String city;

    @Column
    private String startDate;

    @Column
    private String duration;

    @OneToMany(mappedBy = "festival")
    private Set<User> users = new HashSet<>();

    public Festival() {
    }

    public Festival(Long id, String name, String city, String startDate, String duration, Set<User> users) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.startDate = startDate;
        this.duration = duration;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
