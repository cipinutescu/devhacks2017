package com.devhacks.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Ciprian on 10/27/2017.
 */
@Entity
@Table(name = "developers")
public class Developer {

    private Integer id;
    private String name;
    private String genre;
    private Integer motivation_level;
    private Integer velocity_per_week;
    private String primary_tech;
    private String seconday_tech;

    public Developer() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "genre", nullable = false)
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Column(name = "motivation_level", nullable = false)
    public Integer getMotivation_level() {
        return motivation_level;
    }

    public void setMotivation_level(Integer motivation_level) {
        this.motivation_level = motivation_level;
    }

    @Column(name = "velocity_per_week", nullable = false)
    public Integer getVelocity_per_week() {
        return velocity_per_week;
    }

    public void setVelocity_per_week(Integer velocity_per_week) {
        this.velocity_per_week = velocity_per_week;
    }

    @Column(name = "primary_tech", nullable = false)
    public String getPrimary_tech() {
        return primary_tech;
    }

    public void setPrimary_tech(String primary_tech) {
        this.primary_tech = primary_tech;
    }

    @Column(name = "secondary_tech", nullable = false)
    public String getSeconday_tech() {
        return seconday_tech;
    }

    public void setSeconday_tech(String seconday_tech) {
        this.seconday_tech = seconday_tech;
    }
}
