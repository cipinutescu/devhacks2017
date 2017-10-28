package com.devhacks.bean;

import com.devhacks.model.Developer;
import com.devhacks.model.UserStory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ciprian on 10/27/2017.
 */
public class InitialGameScenarioBean {

    private List<Developer> developerList;
    private List<UserStory> storyList;
    private Map<String,Integer> technologiesDistribution;
    private Integer initialCustomerSatisfaction;
    private Integer initalProjectInvestmentBudget;
    private Integer estimatedNumberOfWeeks;

    public InitialGameScenarioBean(List<Developer> developerList, List<UserStory> storyList, Map<String, Integer> technologiesDistribution, Integer initialCustomerSatisfaction, Integer initalProjectInvestmentBudget, Integer estimatedNumberOfWeeks) {
        this.developerList = developerList;
        this.storyList = storyList;
        this.technologiesDistribution = technologiesDistribution;
        this.initialCustomerSatisfaction = initialCustomerSatisfaction;
        this.initalProjectInvestmentBudget = initalProjectInvestmentBudget;
        this.estimatedNumberOfWeeks = estimatedNumberOfWeeks;
    }

    public List<Developer> getDeveloperList() {
        return developerList;
    }

    public void setDeveloperList(List<Developer> developerList) {
        this.developerList = developerList;
    }

    public List<UserStory> getStoryList() {
        return storyList;
    }

    public void setStoryList(List<UserStory> storyList) {
        this.storyList = storyList;
    }

    public Map<String, Integer> getTechnologiesDistribution() {
        return technologiesDistribution;
    }

    public void setTechnologiesDistribution(Map<String, Integer> technologiesDistribution) {
        this.technologiesDistribution = technologiesDistribution;
    }

    public Integer getInitialCustomerSatisfaction() {
        return initialCustomerSatisfaction;
    }

    public void setInitialCustomerSatisfaction(Integer initialCustomerSatisfaction) {
        this.initialCustomerSatisfaction = initialCustomerSatisfaction;
    }

    public Integer getInitalProjectInvestmentBudget() {
        return initalProjectInvestmentBudget;
    }

    public void setInitalProjectInvestmentBudget(Integer initalProjectInvestmentBudget) {
        this.initalProjectInvestmentBudget = initalProjectInvestmentBudget;
    }

    public Integer getEstimatedNumberOfWeeks() {
        return estimatedNumberOfWeeks;
    }

    public void setEstimatedNumberOfWeeks(Integer estimatedNumberOfWeeks) {
        this.estimatedNumberOfWeeks = estimatedNumberOfWeeks;
    }
}
