package com.devhacks.machinelearning;

import com.devhacks.bean.GameMetrics;
import com.devhacks.bean.InitialGameScenarioBean;
import com.devhacks.events.EventsManager;
import com.devhacks.game.GameStatisticsManager;
import com.devhacks.initial.RealTimeInitialComputation;
import com.devhacks.model.Developer;
import com.devhacks.model.PlanningEvent;
import com.devhacks.model.UnexpectedEvent;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Ciprian on 10/28/2017.
 */
@Service
public class GAlgorithm {

    private Integer individuals_per_population = 100;

    private Population population;

    private int numberOfCrossingOvers = (int) (individuals_per_population * 0.5);
    private int numberOfMutations = (int) (individuals_per_population * 0.4);
    private int maxIterations = 1000;


    @Inject
    private RealTimeInitialComputation realTimeInitialComputation;

    @Inject
    private GameStatisticsManager gameStatisticsManager;

    @Inject
    private EventsManager eventsManager;

    private void initPopulationInitialScenario(InitialGameScenarioBean initialGameScenarioBean){

        List<Individual> individualList = new ArrayList<>();
        for(int i=0; i< individuals_per_population; i++) {

            List<Integer> dna = new ArrayList<>();
            Random random = new Random();
            for (Developer developer : initialGameScenarioBean.getDeveloperList()) {
                int x = random.nextInt(100);
                dna.add(x % 2 == 0 ? 0 : 1);
            }
            individualList.add(new Individual(dna,0d));
        }

        population = new Population(individualList);
    }

    public void initPopulationGameScenario(InitialGameScenarioBean initialGameScenarioBean, GameMetrics gameMetrics, List<UnexpectedEvent> unexpectedEvents){
        List<Individual> individualList = new ArrayList<>();
        for(int i=0; i< individuals_per_population; i++) {
            Random random = new Random();
            List<Integer> dna = new ArrayList<>();
            for(int j=0;j<6;j++) {
                int x = random.nextInt(100);
                if (x % 2 == 0) {
                    dna.add(1);
                } else {
                    dna.add(0);
                }
            }
            for(int j=0;j<4;j++){
                int x = random.nextInt(100);
                if (x % 2 == 0) {
                    dna.add(1);
                } else {
                    dna.add(0);
                }
            }
            individualList.add(new Individual(dna,0d));
        }
    }

    public Individual computeGAReal(InitialGameScenarioBean initialGameScenarioBean, GameMetrics gameMetrics,List<UnexpectedEvent> unexpectedEvents){
        int currIt = 0;
        while (currIt < maxIterations){
            List<Individual> newIndivis = new ArrayList<>();
            for(int i=0;i<numberOfCrossingOvers;i++){
                Random random = new Random();
                Individual individual1 = population.getIndividualList().get(random.nextInt(population.getIndividualList().size()));
                Individual individual2 = population.getIndividualList().get(random.nextInt(population.getIndividualList().size()));
                Individual individual = doCrossingOver(individual1,individual2);
                newIndivis.add(individual);
            }
            for(int i=0;i< numberOfMutations;i++){
                Random random = new Random();
                int randX = random.nextInt(newIndivis.size());
                Individual individual = newIndivis.get(randX);
                individual = doMutation(individual);
                newIndivis.set(randX,individual);
            }

            for(int i=0;i < newIndivis.size();i++){
                newIndivis.get(i).setFitness(computeFitness(newIndivis.get(i),initialGameScenarioBean,gameMetrics,unexpectedEvents).getFitness());
            }
            population = new Population(newIndivis);

            currIt +=1;
        }
        return population.getIndividualList().get(0);
    }


    public Individual computeGA(InitialGameScenarioBean initialGameScenarioBean){
        initPopulationInitialScenario(initialGameScenarioBean);
        int currIt = 0;
        while (currIt < maxIterations){
            List<Individual> newIndivis = new ArrayList<>();
            for(int i=0;i<numberOfCrossingOvers;i++){
                Random random = new Random();
                Individual individual1 = population.getIndividualList().get(random.nextInt(population.getIndividualList().size()));
                Individual individual2 = population.getIndividualList().get(random.nextInt(population.getIndividualList().size()));
                Individual individual = doCrossingOver(individual1,individual2);
                newIndivis.add(individual);
            }
            for(int i=0;i< numberOfMutations;i++){
                Random random = new Random();
                int randX = random.nextInt(newIndivis.size());
                Individual individual = newIndivis.get(randX);
                individual = doMutation(individual);
                newIndivis.set(randX,individual);
            }

            for(int i=0;i < newIndivis.size();i++){
                newIndivis.get(i).setFitness(computeFitness(newIndivis.get(i),initialGameScenarioBean).getFitness());
            }
            population = new Population(newIndivis);

            currIt +=1;
        }
        return population.getIndividualList().get(0);
    }

    private Individual doCrossingOver(Individual individual1, Individual individual2){
        int minsize = Math.min(individual1.getDna().size(),individual2.getDna().size());
        Random random = new Random();
        int xC = random.nextInt(minsize);
        List<Integer> integers = new ArrayList<>();
        integers.addAll(individual1.getDna().subList(0,xC));
        integers.addAll(individual2.getDna().subList(xC,individual2.getDna().size()));
        return new Individual(integers,0d);
    }

    private Individual doMutation(Individual individual){
        Random random = new Random();
        int xC = random.nextInt(individual.getDna().size());
        individual.getDna().set(xC,individual.getDna().get(xC) == 0 ? 1 : 0);
        return individual;
    }

    private Individual computeFitness(Individual individual, InitialGameScenarioBean initialGameScenarioBean,GameMetrics gameMetrics,List<UnexpectedEvent> unexpectedEvents){
        for(int i=0;i<individual.getDna().size();i++){
            if(i < 6){
                if(individual.getDna().get(i) == 0)
                    continue;
                String eventName = "";
                switch (i){
                    case 0:{
                        eventName = "Team technical Training";
                        break;
                    }
                    case 1: {
                        eventName = "Introduce DevOps";
                        break;
                    }
                    case 2 : {
                        eventName = "Team Building";
                        break;
                    }
                    case 3 : {
                        eventName = "Diversity Training";
                        break;
                    }
                    case 4 : {
                        eventName = "Stretch goals";
                        break;
                    }
                    case 5: {
                        eventName = "Team soft-skills training";
                        break;
                    }
                }
                PlanningEvent planningEvent = new PlanningEvent(eventName);
                gameMetrics = planningEvent.computeNewGameMetrics(gameMetrics,initialGameScenarioBean);
            } else {
                if(individual.getDna().get(i) == 0)
                    continue;
                for(UnexpectedEvent unexpectedEvent : unexpectedEvents){
                    switch (unexpectedEvent.getType()){
                        case "Planning": {
                            unexpectedEvent.setAnswerId(individual.getDna().get(6));
                        }
                        case "Daily Scrum":{
                            unexpectedEvent.setAnswerId(individual.getDna().get(7));
                        }
                        case "Retrospective":{
                            unexpectedEvent.setAnswerId(individual.getDna().get(8));
                        }
                        case "Review": {
                            unexpectedEvent.setAnswerId(individual.getDna().get(9));
                        }
                    }
                    gameMetrics = eventsManager.computeNewGameMetrics(gameMetrics,initialGameScenarioBean,unexpectedEvent);
                }
            }
        }
        return new Individual(individual.getDna(),computeFitnessFromGameMetrics(gameMetrics));
    }

    private Individual computeFitness(Individual individual, InitialGameScenarioBean initialGameScenarioBean){
        List<Developer> newDevs = new ArrayList<>();
        for(int i=0;i< individual.getDna().size();i++){
            if(individual.getDna().get(i) == 1){
                newDevs.add(initialGameScenarioBean.getDeveloperList().get(i));
            }
        }
        InitialGameScenarioBean initialGameScenarioBeanAltered = new InitialGameScenarioBean(newDevs,initialGameScenarioBean.getStoryList(),initialGameScenarioBean.getTechnologiesDistribution(),initialGameScenarioBean.getInitialCustomerSatisfaction(),initialGameScenarioBean.getInitalProjectInvestmentBudget(),initialGameScenarioBean.getEstimatedNumberOfWeeks());
        initialGameScenarioBeanAltered.setDeveloperList(newDevs);
        Random random = new Random();
        int wps = random.nextInt(2) + 2;
        GameMetrics gameMetrics = realTimeInitialComputation.computeMetrics(initialGameScenarioBeanAltered,wps);
        return new Individual(individual.getDna(),computeFitnessFromGameMetrics(gameMetrics));
    }

    private Double computeFitnessFromGameMetrics(GameMetrics gameMetrics){
        return gameMetrics.getTeamSatisfaction() * 0.4 + gameMetrics.getCustomerSatisfaction() * 0.4 +
                + gameMetrics.getProjectInvestmentFund() * 0.1 - gameMetrics.getCurrentBacklogSize() * 0.1;
    }
}
