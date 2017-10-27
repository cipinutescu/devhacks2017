package com.devhacks.initial;

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
    public void postConstruct(){
        technologies = new ArrayList<>();
        technologies.add("Front-End");
        technologies.add("Back-end");
        technologies.add("Integrators");
        technologies.add("A.I.");
    }

    @Inject
    private DeveloperDao developerDao;

    public List<Developer> generateRandomInitialContext(){
        List<Developer> developerList = developerDao.getAllDevelopers();

        if(developerList == null || developerList.size() < 10){
            developerList = new ArrayList<>();
            for(int i=0;i< 10;i++){
                developerList.add(generateRandomDeveloper());
            }
        }
        return developerList;
    }

    private Developer generateRandomDeveloper(){
        Developer developer = new Developer();
        Random random = new Random();

        String genre = random.nextInt() % 2 == 0 ? "M" : "F";

        Integer motivation_level = Math.abs(random.nextInt() * 10 + 50) % 100;

        Integer velocity_per_week = (Math.abs(random.nextInt()) % 5 + 1);

        Faker faker = new Faker();

        String name = faker.name().fullName();

        String primary_tech = technologies.get(Math.abs(random.nextInt()) % technologies.size());

        String secondary_tech = primary_tech;

        while (primary_tech.equals(secondary_tech)){
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

    private int fibonnaci(int n){
        if (n <= 1) {
            return n;
        } else {
            return fibonnaci(n - 1) + fibonnaci(n - 2);
        }
    }

    private List<UserStory> getUserStoriesList(Integer max_story_points){

        List<UserStory> userStoryLsit = new ArrayList<>();
        int total_story_point = 0;
        while (total_story_point < max_story_points) {
            Random random = new Random(42);
            int number_of_sp = fibonnaci(Math.abs(random.nextInt(8)));
            UserStory userStory = new UserStory("story" + total_story_point,"",number_of_sp);
            userStoryLsit.add(userStory);
            total_story_point += number_of_sp;
        }
        return userStoryLsit;
    }

    public Map<String,Integer> getProjectTechnologiesDistribution(){
        Random random = new Random(42);
        Map<String,Integer> ptd = new HashMap<>();
        ptd.put(technologies.get(0),Math.abs(random.nextInt(30)));
        ptd.put(technologies.get(1),Math.abs(random.nextInt(30)));
        ptd.put(technologies.get(2),Math.abs(random.nextInt(30)));
        ptd.put(technologies.get(3), 100 - ptd.get(technologies.get(0)) - ptd.get(technologies.get(1)) - ptd.get(technologies.get(2)));
        return ptd;
    }

    public
}
