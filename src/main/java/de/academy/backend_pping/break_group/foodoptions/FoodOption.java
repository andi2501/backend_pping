package de.academy.backend_pping.break_group.foodoptions;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FoodOption {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String address;
    private String link;
    private String timeDuration;
    private int costs;


    public FoodOption() {
    }
    public FoodOption(Long id, String name, String address, String link, String timeDuration, int costs) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.link = link;
        this.timeDuration = timeDuration;
        this.costs = costs;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(String timeDuration) {
        this.timeDuration = timeDuration;
    }

    public int getCosts() {
        return costs;
    }

    public void setCosts(int costs) {
        this.costs = costs;
    }
}
