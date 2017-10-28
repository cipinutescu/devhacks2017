package com.devhacks.initial;

import com.devhacks.bean.GameMetrics;
import com.devhacks.bean.InitialGameScenarioBean;
import com.devhacks.model.Developer;
import com.devhacks.model.UserStory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ciprian on 10/27/2017.
 */
@Service
public class RealTimeInitialComputation {

    public GameMetrics computeMetrics(InitialGameScenarioBean initialGameScenarioBean,Integer wps) {
        Integer teamSatisfaction = 0;
        Integer customerSatisfaction = 0;
        Integer projectInvestmentFund = 0;
        Integer estimatedWeeksToCompletion = 0;
        Integer customerExpectedWeeksToCompletion = 0;
        Integer currentBacklogSize = 0;
        Integer velocity = 0;

        int devMales = 0;
        int devFems = 0;

        double fe1 = 0;
        double be1 = 0;
        double in1 = 0;
        double ai1 = 0;
        double fe2 = 0;
        double be2 = 0;
        double in2 = 0;
        double ai2 = 0;

        for (Developer developer : initialGameScenarioBean.getDeveloperList()) {
            teamSatisfaction += developer.getMotivation_level();
            velocity += (developer.getVelocity_per_week() * wps);
            if (developer.getGenre().equals("M"))
                devMales += 1;
            else
                devFems += 1;
            switch (developer.getPrimary_tech()){
                case "Front-End" : {
                    fe1 +=1;
                    break;
                }
                case "Back-end" :{
                    be1+=1;
                    break;
                }
                case "Integrators" : {
                    in1 +=1;
                    break;
                }
                case "A.I." :{
                    ai1 +=1;
                    break;
                }
            }
            switch (developer.getSeconday_tech()){
                case "Front-End" : {
                    fe2 +=1;
                    break;
                }
                case "Back-end" :{
                    be2+=1;
                    break;
                }
                case "Integrators" : {
                    in2 +=1;
                    break;
                }
                case "A.I." :{
                    ai2 +=1;
                    break;
                }
            }

        }
        Map<String,Double> technologies = new HashMap<>();
        int teamSize = initialGameScenarioBean.getDeveloperList().size();

        if(teamSize == 0)
            teamSatisfaction =0;
        else
            teamSatisfaction /= teamSize;

        technologies.put("Front-End", (fe1 * 2 /3 * teamSize + fe2 * 1/3 * teamSize) * 100 );
        technologies.put("Back-end", (be1 * 2 /3 * teamSize + be2 * 1/3 * teamSize) * 100);
        technologies.put("Integrators", (in1 * 2 /3 * teamSize + in2 * 1/3 * teamSize) * 100);
        technologies.put("A.I.", (ai1 * 2 /3 * teamSize + ai2 * 1/3 * teamSize) * 100);

        if((fe1 * 2 /3 * teamSize + fe2 * 1/3 * teamSize) * 100 < initialGameScenarioBean.getTechnologiesDistribution().get("Front-End"))
            velocity -=10;
        if((be1 * 2 /3 * teamSize + be2 * 1/3 * teamSize) * 100 < initialGameScenarioBean.getTechnologiesDistribution().get("Back-end"))
            velocity -=10;
        if((in1 * 2 /3 * teamSize + in2 * 1/3 * teamSize) * 100 < initialGameScenarioBean.getTechnologiesDistribution().get("Integrators"))
            velocity -=10;
        if((ai1 * 2 /3 * teamSize + ai2 * 1/3 * teamSize) * 100 < initialGameScenarioBean.getTechnologiesDistribution().get("A.I."))
            velocity -=10;

        customerSatisfaction = initialGameScenarioBean.getInitialCustomerSatisfaction();
        projectInvestmentFund = initialGameScenarioBean.getInitalProjectInvestmentBudget();
        customerExpectedWeeksToCompletion = initialGameScenarioBean.getEstimatedNumberOfWeeks();

        for (UserStory userStory : initialGameScenarioBean.getStoryList()) {
            currentBacklogSize += userStory.getStory_points();
        }

        //team overhead collaboration
        if (initialGameScenarioBean.getDeveloperList().size() > 8) {
            velocity -= 20;
        } else if (initialGameScenarioBean.getDeveloperList().size() == 7) {
            velocity -= 15;
        } else if (initialGameScenarioBean.getDeveloperList().size() == 6) {
            velocity -= 10;
        } else if (initialGameScenarioBean.getDeveloperList().size() == 5) {
            velocity -= 5;
        }

        if (devMales / (devFems + devMales+1) > 60 || devMales / (devFems + devMales+1) < 40) {
            velocity -= 20;
        }

        if(velocity == 0)
            estimatedWeeksToCompletion = 20;
        else
            estimatedWeeksToCompletion = currentBacklogSize / velocity;

        velocity = velocity > 0 ? velocity : 0;

        return new GameMetrics(teamSatisfaction, customerSatisfaction, projectInvestmentFund, estimatedWeeksToCompletion, customerExpectedWeeksToCompletion, currentBacklogSize, velocity);
    }
}
