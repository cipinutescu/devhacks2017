package com.devhacks.model;

import com.devhacks.bean.GameMetrics;
import com.devhacks.bean.InitialGameScenarioBean;

/**
 * Created by Ciprian on 10/28/2017.
 */
public class PlanningEvent {
    private String planningEventName;

    public PlanningEvent(String planningEventName) {
        this.planningEventName = planningEventName;
    }

    public String getPlanningEventName() {
        return planningEventName;
    }

    public void setPlanningEventName(String planningEventName) {
        this.planningEventName = planningEventName;
    }

    public GameMetrics computeNewGameMetrics(GameMetrics gameMetrics, InitialGameScenarioBean initialGameScenarioBean){
        switch (this.planningEventName){
            case "Team technical Training" : {
                gameMetrics.setProjectInvestmentFund(gameMetrics.getProjectInvestmentFund() - 20 * initialGameScenarioBean.getDeveloperList().size());
                gameMetrics.setVelocity(gameMetrics.getVelocity() + 10);
                gameMetrics.setTeamSatisfaction(gameMetrics.getTeamSatisfaction() + 5);
                return gameMetrics;
            }
            case "Introduce DevOps" : {
                gameMetrics.setProjectInvestmentFund(gameMetrics.getProjectInvestmentFund() - 50 * initialGameScenarioBean.getDeveloperList().size());
                gameMetrics.setVelocity(gameMetrics.getVelocity() + 20);
                gameMetrics.setCustomerSatisfaction(gameMetrics.getCustomerSatisfaction() - 15);
                return gameMetrics;
            }
            case "Team Building" : {
                gameMetrics.setTeamSatisfaction(gameMetrics.getTeamSatisfaction() + 5);
                return gameMetrics;
            }
            case "Diversity Training" : {
                gameMetrics.setProjectInvestmentFund(gameMetrics.getProjectInvestmentFund() - 5 * initialGameScenarioBean.getDeveloperList().size());
                gameMetrics.setVelocity(gameMetrics.getVelocity() + 5);
                gameMetrics.setTeamSatisfaction(gameMetrics.getTeamSatisfaction() + 5);
                return gameMetrics;
            }
            case "Stretch goals" : {
                gameMetrics.setTeamSatisfaction(gameMetrics.getTeamSatisfaction() - 10);
                gameMetrics.setCustomerSatisfaction(gameMetrics.getCustomerSatisfaction() + 10);
                return gameMetrics;
            }
            case "Team soft-skills training" : {
                gameMetrics.setProjectInvestmentFund(gameMetrics.getProjectInvestmentFund() - 10 * initialGameScenarioBean.getDeveloperList().size());
                gameMetrics.setVelocity(gameMetrics.getVelocity() + 5);
                gameMetrics.setTeamSatisfaction(gameMetrics.getTeamSatisfaction() + 5);
                return gameMetrics;
            }
            default: {
                return gameMetrics;
            }
        }
    }
}
