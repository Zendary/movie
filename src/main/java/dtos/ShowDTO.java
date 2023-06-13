package dtos;

import entities.Show;

public class ShowDTO {

    private Long id;
    private String name;
    private String duration;
    private String location;
    private String startDate;
    private String startTime;

    public ShowDTO() {
    }

    public ShowDTO(Show s){
        this.id = s.getId();
        this.name = s.getName();
        this.duration = s.getDuration();
        this.location = s.getLocation();
        this.startDate = s.getStartDate();
        this.startTime = s.getStartTime();
    }

    public ShowDTO(String name, String duration, String location, String startDate, String startTime) {
        this.name = name;
        this.duration = duration;
        this.location = location;
        this.startDate = startDate;
        this.startTime = startTime;
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

    @Override
    public String toString() {
        return "ShowDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                ", location='" + location + '\'' +
                ", startDate='" + startDate + '\'' +
                ", startTime='" + startTime + '\'' +
                '}';
    }
}
