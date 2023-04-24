package de.academy.backend_pping.break_group.foodoptions;

public class FoodOptionDTO {

    private Long id;
    private String name;
    private String address;
    private String link;
    private String timeDuration;
    private int costs;

    public FoodOptionDTO() {
    }

    public FoodOptionDTO(FoodOption foodOption) {
        this.id = foodOption.getId();
        this.name = foodOption.getName();
        this.address = foodOption.getAddress();
        this.link = foodOption.getLink();
        this.timeDuration = foodOption.getTimeDuration();
        this.costs = foodOption.getCosts();
    }

    public FoodOptionDTO(String name, String address, String link, String timeDuration, int costs) {
        this.name = name;
        this.address = address;
        this.link = link;
        this.timeDuration = timeDuration;
        this.costs = costs;
    }

    public FoodOptionDTO(Long id, String name, String address, String link, String timeDuration, int costs) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.link = link;
        this.timeDuration = timeDuration;
        this.costs = costs;
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
