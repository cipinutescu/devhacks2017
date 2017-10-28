package com.devhacks.bean;

/**
 * Created by Ciprian on 10/27/2017.
 */
public class GameMetrics {
    private Integer teamSatisfaction;
    private Integer customerSatisfaction;
    private Integer projectInvestmentFund;
    private Integer estimatedWeeksToCompletion;
    private Integer customerExpectedWeeksToCompletion;
    private Integer currentBacklogSize;
    private Integer velocity;

    public GameMetrics(Integer teamSatisfaction, Integer customerSatisfaction, Integer projectInvestmentFund, Integer estimatedWeeksToCompletion, Integer customerExpectedWeeksToCompletion, Integer currentBacklogSize, Integer velocity) {
        this.teamSatisfaction = teamSatisfaction;
        this.customerSatisfaction = customerSatisfaction;
        this.projectInvestmentFund = projectInvestmentFund;
        this.estimatedWeeksToCompletion = estimatedWeeksToCompletion;
        this.customerExpectedWeeksToCompletion = customerExpectedWeeksToCompletion;
        this.currentBacklogSize = currentBacklogSize;
        this.velocity = velocity;
    }

    public Integer getTeamSatisfaction() {
        return teamSatisfaction;
    }

    public void setTeamSatisfaction(Integer teamSatisfaction) {
        this.teamSatisfaction = teamSatisfaction;
    }

    public Integer getCustomerSatisfaction() {
        return customerSatisfaction;
    }

    public void setCustomerSatisfaction(Integer customerSatisfaction) {
        this.customerSatisfaction = customerSatisfaction;
    }

    public Integer getProjectInvestmentFund() {
        return projectInvestmentFund;
    }

    public void setProjectInvestmentFund(Integer projectInvestmentFund) {
        this.projectInvestmentFund = projectInvestmentFund;
    }

    public Integer getEstimatedWeeksToCompletion() {
        return estimatedWeeksToCompletion;
    }

    public void setEstimatedWeeksToCompletion(Integer estimatedWeeksToCompletion) {
        this.estimatedWeeksToCompletion = estimatedWeeksToCompletion;
    }

    public Integer getCustomerExpectedWeeksToCompletion() {
        return customerExpectedWeeksToCompletion;
    }

    public void setCustomerExpectedWeeksToCompletion(Integer customerExpectedWeeksToCompletion) {
        this.customerExpectedWeeksToCompletion = customerExpectedWeeksToCompletion;
    }

    public Integer getCurrentBacklogSize() {
        return currentBacklogSize;
    }

    public void setCurrentBacklogSize(Integer currentBacklogSize) {
        this.currentBacklogSize = currentBacklogSize;
    }

    public Integer getVelocity() {
        return velocity;
    }

    public void setVelocity(Integer velocity) {
        this.velocity = velocity;
    }

    @Override
    public String toString() {
        return "GameMetrics{" +
                "teamSatisfaction=" + teamSatisfaction +
                ", customerSatisfaction=" + customerSatisfaction +
                ", projectInvestmentFund=" + projectInvestmentFund +
                ", estimatedWeeksToCompletion=" + estimatedWeeksToCompletion +
                ", customerExpectedWeeksToCompletion=" + customerExpectedWeeksToCompletion +
                ", currentBacklogSize=" + currentBacklogSize +
                ", velocity=" + velocity +
                '}';
    }
}
