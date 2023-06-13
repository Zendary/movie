package dtos;

import entities.Festival;

public class FestivalDTO {

    private String id;
    private String name;
    private String city;
    private String startDate;
    private String duration;

    public FestivalDTO() {
    }

    public FestivalDTO(Festival f){
        this.name = f.getName();
        this.city = f.getCity();
        this.startDate = f.getStartDate();
        this.duration = f.getDuration();
    }

    public FestivalDTO(String id, String name, String city, String startDate, String duration) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.startDate = startDate;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @Override
    public String toString() {
        return "FestivalDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", startDate='" + startDate + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
