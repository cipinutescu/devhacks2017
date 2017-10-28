package com.devhacks.initial;

import com.devhacks.bean.InitialGameScenarioBean;
import com.devhacks.dao.DeveloperDao;
import com.devhacks.model.Developer;
import com.devhacks.model.UserStory;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.*;

/**
 * Created by Ciprian on 10/27/2017.
 */
@Service
public class InitialContextProvider {

    private List<String> technologies;

    @PostConstruct
    public void postConstruct() {
        technologies = new ArrayList<>();
        technologies.add("Front-End");
        technologies.add("Back-end");
        technologies.add("Integrators");
        technologies.add("A.I.");
    }

    @Inject
    private DeveloperDao developerDao;

    public InitialGameScenarioBean generateRandomInitialContext() {
        List<Developer> developerList = developerDao.getAllDevelopers();

        if (developerList == null || developerList.size() < 10) {
            developerList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                developerList.add(generateRandomDeveloper());
            }
        }

        Random random = new Random();

        List<UserStory> userStoryList = getUserStoriesList(random.nextInt(200) + 300);

        Integer expectedNumberOfWeeksForCompletion = random.nextInt(7) + 8;
        Integer initialCustomerSatisfaction = random.nextInt(40) + 50;
        Map<String, Integer> estimatedTechs = getProjectTechnologiesDistribution();
        Integer initialProjectInvestmentBudget = random.nextInt(500) + 500;

        return new InitialGameScenarioBean(developerList, userStoryList, estimatedTechs, initialCustomerSatisfaction, initialProjectInvestmentBudget, expectedNumberOfWeeksForCompletion);
    }

    private Developer generateRandomDeveloper() {
        Developer developer = new Developer();
        Random random = new Random();

        String genre = random.nextInt() % 2 == 0 ? "M" : "F";

        Integer motivation_level = 75 + random.nextInt(26);

        Integer velocity_per_week = random.nextInt(5)+1;

        Faker faker = new Faker();

        String name = faker.name().fullName();

        String primary_tech = technologies.get(Math.abs(random.nextInt()) % technologies.size());

        String secondary_tech = primary_tech;

        while (primary_tech.equals(secondary_tech)) {
            secondary_tech = technologies.get(Math.abs(random.nextInt()) % technologies.size());
        }

        developer.setName(name);
        developer.setGenre(genre);
        developer.setMotivation_level(motivation_level);
        developer.setVelocity_per_week(velocity_per_week);
        developer.setPrimary_tech(primary_tech);
        developer.setSeconday_tech(secondary_tech);

        //developerDao.addNewDeveloper(developer);


        return developer;
    }

    private int fibonnaci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonnaci(n - 1) + fibonnaci(n - 2);
        }
    }

    private List<UserStory> getUserStoriesList(Integer max_story_points) {

        List<UserStory> userStoryLsit = new ArrayList<>();
        int total_story_point = 0;
        while (total_story_point < max_story_points) {
            Random random = new Random();
            int number_of_sp = fibonnaci(Math.abs(random.nextInt(8)));
            UserStory userStory = new UserStory("story" + total_story_point, "", number_of_sp);
            userStoryLsit.add(userStory);
            total_story_point += number_of_sp;
        }
        return userStoryLsit;
    }

    public Map<String, Integer> getProjectTechnologiesDistribution() {
        Random random = new Random();
        Map<String, Integer> ptd = new HashMap<>();
        ptd.put(technologies.get(0), Math.abs(random.nextInt(30)));
        ptd.put(technologies.get(1), Math.abs(random.nextInt(30)));
        ptd.put(technologies.get(2), Math.abs(random.nextInt(30)));
        ptd.put(technologies.get(3), 100 - ptd.get(technologies.get(0)) - ptd.get(technologies.get(1)) - ptd.get(technologies.get(2)));
        return ptd;
    }

}
