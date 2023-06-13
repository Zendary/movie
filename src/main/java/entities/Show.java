package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "shows")
@NamedQueries({
        @NamedQuery(name = "Show.deleteAllRows", query = "DELETE from Show"),
        @NamedQuery(name = "Show.getAllFestivals", query = "SELECT s FROM Show s"),
})
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String duration;

    @Column
    private String location;

    @Column
    private String startDate;

    @Column
    private String startTime;

    @ManyToMany(mappedBy = "shows")
    private Set<User> users = new HashSet<>();

    public Show() {
    }

    public Show(Long id, String name, String duration, String location, String startDate, String startTime, Set<User> users) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.location = location;
        this.startDate = startDate;
        this.startTime = startTime;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}

