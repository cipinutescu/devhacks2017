package com.devhacks.model;

/**
 * Created by Ciprian on 10/27/2017.
 */
public class UserStory {
    private String name;
    private String description;
    private Integer story_points;

    public UserStory(String name, String description, Integer story_points) {
        this.name = name;
        this.description = description;
        this.story_points = story_points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStory_points() {
        return story_points;
    }

    public void setStory_points(Integer story_points) {
        this.story_points = story_points;
    }
}
