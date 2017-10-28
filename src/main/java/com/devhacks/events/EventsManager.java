package com.devhacks.events;

import com.devhacks.bean.GameMetrics;
import com.devhacks.bean.InitialGameScenarioBean;
import com.devhacks.model.UnexpectedEvent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Ciprian on 10/28/2017.
 */
@Service
public class EventsManager {

    public List<UnexpectedEvent> getUnexpectedEvents(){
        Random random = new Random();
        int unEventsPerSprint = random.nextInt(5);
        List<UnexpectedEvent> unexpectedEvents = new ArrayList<>();
        boolean found1 = false;
        boolean found2 = false;
        boolean found3 = false;
        boolean found4 = false;
        for(int i=0;i<unEventsPerSprint;i++){
            int evTI = random.nextInt(100);
            UnexpectedEvent unexpectedEvent = null;
            if(evTI % 4 == 0 ){
                String type = "Planning";
                found1 = true;
                int idQ = random.nextInt(3);
                unexpectedEvent = new UnexpectedEvent(idQ,type,getQuestionByIdNumber(idQ,type),getAnswer1ByIdNumber(idQ,type),getAnswer2ByIdNumber(idQ,type),getAnswer3ByIdNumber(idQ,type),-1);
            } else if(evTI % 4 == 1 ){
                String type = "Daily Scrum";
                found2 = true;
                int idQ = random.nextInt(3);
                unexpectedEvent = new UnexpectedEvent(idQ,type,getQuestionByIdNumber(idQ,type),getAnswer1ByIdNumber(idQ,type),getAnswer2ByIdNumber(idQ,type),getAnswer3ByIdNumber(idQ,type),-1);
            } else if(evTI % 4 == 2 ){
                String type = "Retrospective";
                found3 = true;
                int idQ = random.nextInt(2);
                unexpectedEvent = new UnexpectedEvent(idQ,type,getQuestionByIdNumber(idQ,type),getAnswer1ByIdNumber(idQ,type),getAnswer2ByIdNumber(idQ,type),getAnswer3ByIdNumber(idQ,type),-1);
            } else if(evTI % 4 == 3 ){
                found4 = true;
                String type = "Review";
                int idQ = random.nextInt(2);
                unexpectedEvent = new UnexpectedEvent(idQ,type,getQuestionByIdNumber(idQ,type),getAnswer1ByIdNumber(idQ,type),getAnswer2ByIdNumber(idQ,type),getAnswer3ByIdNumber(idQ,type),-1);
            }

            unexpectedEvents.add(unexpectedEvent);
        }

        return unexpectedEvents;
    }

    private String getAnswer3ByIdNumber(int idQ, String type) {
        switch (type){
            case "Planning":  {
                if(idQ == 0){
                    return "c.Shrugs his shoulders and says : let’s work with what we have";
                } else if(idQ == 1){
                    return "c.PO will speak with a SME for helping the team with this issue";
                } else if(idQ == 2){
                    return "c.The scrum master agrees with PO and says: if they will not be using the manhour estimation method, then they will not know the number of user stories that will be delivered in the next sprint";
                }
            }
            case "Daily Scrum" : {
                if(idQ == 0){
                    return "c.Everybody ignores the issue and they move on";
                } else if(idQ == 1){
                    return "c.The other developers start laughing about their team member, because the issue with the laptop was caused by him, because he doesn’t know how to use a laptop";
                } else if(idQ == 2){
                    return "c.The scrum master says that the “daily scrum” meeting can be shorter, only fi they will talk about the tasks, not the unexpected issues";
                }
            }
            case "Retrospective" : {
                if(idQ == 0){
                    return "c.The other colleagues said they will help much more in the next sprint and this issue will not persist";
                } else if(idQ == 1){
                    return "c.The scrum master said he and the development team need to revise the tasks description, until the developers will start implementing the requirements.";
                }
            }

            case "Review" : {
                if(idQ == 0){
                    return "c.The development team said they can fix the problem right away, but first they need to ask the other clients as well. ";
                } else if(idQ == 1){
                    return "c.The scrum master will present himself, because he knows the business flow of the application";
                }
            }
        }
        return null;
    }

    private String getAnswer2ByIdNumber(int idQ, String type) {
        switch (type){
            case "Planning":  {
                if(idQ == 0){
                    return "b.Abandons the story";
                } else if(idQ == 1){
                    return "b.PO  will explain with more details what the expectations are for the functionality to be developed and then the development team will re-estimate";
                } else if(idQ == 2){
                    return "b.The scrum master explains the advantage of a generic method for estimations and the team will keep their estimations";
                }
            }
            case "Daily Scrum" : {
                if(idQ == 0){
                    return "b.The developer is told he has to solve the issue himself";
                } else if(idQ == 1){
                    return "b.The other developers start complaining as well about their laptops";
                } else if(idQ == 2){
                    return "b.The scrum master says that they will attend “daily scrum” meeting no matter what is the duration of the meeting, because they need to solve the problems. ";
                }
            }
            case "Retrospective" : {
                if(idQ == 0){
                    return "b.The scrum master said that the “trouble maker” is a good colleague, and the story it’s not true.";
                } else if(idQ == 1){
                    return "b.The scrum master said he will contact the client more often for the new questions and details required by development team";
                }
            }

            case "Review" : {
                if(idQ == 0){
                    return "b.The scrum master said that the impact of the component that was not developed well, will not impact the sprint review";
                } else if(idQ == 1){
                    return "b.The scrum master suggests splitting the functionality between the developers and then each of them will present";
                }
            }
        }
        return null;
    }

    private String getAnswer1ByIdNumber(int idQ, String type) {
        switch (type){
            case "Planning":  {
                if(idQ == 0){
                    return "a.Contacts one of the stakeholders for clarifications";
                } else if(idQ == 1){
                    return "a.PO chooses one estimation that a developer provided and says: ”This is the one!”";
                } else if(idQ == 2){
                    return "a.The development team estimates using “manhour” procedure instead of story points";
                }
            }
            case "Daily Scrum" : {
                if(idQ == 0){
                    return "a.The Scrum Master notes the problem and asks who could help with the issue";
                } else if(idQ == 1){
                    return "a.The scrum master documents the issue and recommends to the developer to contact IT support";
                } else if(idQ == 2){
                    return "a.The scrum master agrees with what the developers said, because the “daily scrum” meeting should have maxim 15 minutes, even if some problems will appear";
                }
            }
            case "Retrospective" : {
                if(idQ == 0){
                    return "a.The scrum master needs more details to understand this issue and asks the team member about this issue and propose to the other colleagues to give the developer a solution.";
                } else if(idQ == 1){
                    return "a.The scrum master said the developers need to prepare more questions for the PO";
                }
            }

            case "Review" : {
                if(idQ == 0){
                    return "a.The development team said they understand well and they are trying to explain the PO that the component is developed right";
                } else if(idQ == 1){
                    return "a.The scrum master tells other developers to present the functionality";
                }
            }
        }
        return null;
    }

    private String getQuestionByIdNumber(int idQ, String type) {
        switch (type){
            case "Planning":  {
                if(idQ == 0){
                    return "During clarifications for a user story the team asks questions to Product Owner which he cannot answer. What does the PO do?";
                } else if(idQ == 1){
                    return "During the estimation for a user story the developers have different estimations and big differences between their estimations. What should be the next steps?";
                } else if(idQ == 2){
                    return "During estimation the PO says that it’s mandatory to use the “man-hour” estimation method. What is the most appropriate answer?";
                }
            }
            case "Daily Scrum" : {
                if(idQ == 0){
                    return "One developers task is getting delayed because of technical issues he cannot solve. What should the Scrum Master do:";
                } else if(idQ == 1){
                    return "One developer complains about his laptop, he doesn’t know what are the next steps for his issue";
                } else if(idQ == 2){
                    return "The developers complain about the fact that they don’t have enough time for development, because they attend the “daily scrum” meeting. (25 minutes). What will decide the Scrum master?";
                }
            }
            case "Retrospective" : {
                if(idQ == 0){
                    return "During the sprint retrospective one developer complains about another team member, because a component was not developed in time. What should do the scrum master?";
                } else if(idQ == 1){
                    return "During the sprint retrospective the developers agreed that the description of the tasks can improve. What should the scrum master do to improve the description of the tasks in the next sprint?";
                }
            }

            case "Review" : {
                if(idQ == 0){
                    return "The PO said that one component was not developed as well as he described it to the development team. What Scrum master or development team should do to fix this issue?";
                } else if(idQ == 1){
                    return "Two developers start a fight because each of them wants to present the sprint components to the customer team and PO. What should do the scrum master next?";
                }
            }
        }
        return null;
    }




    public GameMetrics computeNewGameMetrics(GameMetrics gameMetrics, InitialGameScenarioBean initialGameScenarioBean,UnexpectedEvent ue) {
        switch (ue.getType()){
            case "Planning": {
                switch (ue.getId()){
                    case 0 : {
                        if(ue.getAnswerId() == 0){
                            return gameMetrics;
                        } else if(ue.getAnswerId() == 1) {
                            gameMetrics.setCustomerSatisfaction(gameMetrics.getCustomerSatisfaction() - 3);
                            return gameMetrics;
                        } else if(ue.getAnswerId() == 2) {
                            gameMetrics.setCustomerSatisfaction(gameMetrics.getCustomerSatisfaction() -3 );
                            gameMetrics.setTeamSatisfaction(gameMetrics.getTeamSatisfaction() -3);
                            return gameMetrics;
                        }
                    }
                    case 1 : {
                        if(ue.getAnswerId() == 0){
                            gameMetrics.setTeamSatisfaction(gameMetrics.getTeamSatisfaction() -3);
                            return gameMetrics;
                        } else if(ue.getAnswerId() == 1) {
                            gameMetrics.setVelocity(gameMetrics.getVelocity() + 5);
                            return gameMetrics;
                        } else if(ue.getAnswerId() == 2) {
                            gameMetrics.setVelocity(gameMetrics.getVelocity() + 5);
                            return gameMetrics;
                        }
                    }
                    case 2 : {
                        if(ue.getAnswerId() == 0){
                            gameMetrics.setTeamSatisfaction(gameMetrics.getTeamSatisfaction() - 5);
                            gameMetrics.setVelocity(gameMetrics.getVelocity() - 5);
                            return gameMetrics;
                        } else if(ue.getAnswerId() == 1) {
                            return gameMetrics;
                        } else if(ue.getAnswerId() == 2) {
                            gameMetrics.setTeamSatisfaction(gameMetrics.getTeamSatisfaction() - 5);
                            gameMetrics.setVelocity(gameMetrics.getVelocity() - 5);
                            return gameMetrics;
                        }
                    }
                    default:{
                        return gameMetrics;
                    }
                }
            }
            case "Daily Scrum" : {
                switch (ue.getId()){
                    case 0 : {
                        if(ue.getAnswerId() == 0){
                            gameMetrics.setTeamSatisfaction(gameMetrics.getTeamSatisfaction() + 2);
                            return gameMetrics;
                        } else if(ue.getAnswerId() == 1) {
                            gameMetrics.setTeamSatisfaction(gameMetrics.getTeamSatisfaction() - 2);
                            gameMetrics.setVelocity(gameMetrics.getVelocity() -5 );
                            return gameMetrics;
                        } else if(ue.getAnswerId() == 2) {
                            gameMetrics.setVelocity(gameMetrics.getVelocity() - 5);
                            return gameMetrics;
                        }
                    }
                    case 1 : {
                        if(ue.getAnswerId() == 0){
                            gameMetrics.setVelocity(gameMetrics.getVelocity() -3);
                            return gameMetrics;
                        } else if(ue.getAnswerId() == 1) {
                            gameMetrics.setTeamSatisfaction(gameMetrics.getTeamSatisfaction() - 5);
                            return gameMetrics;
                        } else if(ue.getAnswerId() == 2) {
                            gameMetrics.setTeamSatisfaction(gameMetrics.getTeamSatisfaction() - 10);
                            return gameMetrics;
                        }
                    }
                    case 2 : {
                        if(ue.getAnswerId() == 0){
                            gameMetrics.setVelocity(gameMetrics.getVelocity() - 5);
                            return gameMetrics;
                        } else if(ue.getAnswerId() == 1) {
                            gameMetrics.setVelocity(gameMetrics.getVelocity() - 5);
                            return gameMetrics;
                        } else if(ue.getAnswerId() == 2) {
                            return gameMetrics;
                        }
                    }
                    default:{
                        return gameMetrics;
                    }
                }
            }
            case "Retrospective": {
                switch (ue.getId()){
                    case 0 : {
                        if(ue.getAnswerId() == 0){
                            gameMetrics.setTeamSatisfaction(gameMetrics.getTeamSatisfaction() + 3);
                            return gameMetrics;
                        } else if(ue.getAnswerId() == 1) {
                            gameMetrics.setTeamSatisfaction(gameMetrics.getTeamSatisfaction() - 5);
                            return gameMetrics;
                        } else if(ue.getAnswerId() == 2) {
                            gameMetrics.setTeamSatisfaction(gameMetrics.getTeamSatisfaction() + 5);
                            return gameMetrics;
                        }
                    }
                    case 1 : {
                        if(ue.getAnswerId() == 0){
                            gameMetrics.setTeamSatisfaction(gameMetrics.getTeamSatisfaction() + 2);
                            return gameMetrics;
                        } else if(ue.getAnswerId() == 1) {
                            gameMetrics.setTeamSatisfaction(gameMetrics.getTeamSatisfaction() + 5);
                            return gameMetrics;
                        } else if(ue.getAnswerId() == 2) {
                            gameMetrics.setTeamSatisfaction(gameMetrics.getTeamSatisfaction() + 5);
                            return gameMetrics;
                        }
                    }
                    default:{
                        return gameMetrics;
                    }
                }
            }
            case "Review" : {
                switch (ue.getId()){
                    case 0 : {
                        if(ue.getAnswerId() == 0){
                            return gameMetrics;
                        } else if(ue.getAnswerId() == 1) {
                            gameMetrics.setCustomerSatisfaction(gameMetrics.getCustomerSatisfaction() - 5);
                            return gameMetrics;
                        } else if(ue.getAnswerId() == 2) {
                            gameMetrics.setCustomerSatisfaction(gameMetrics.getCustomerSatisfaction() + 5);
                            return gameMetrics;
                        }
                    }
                    case 1 : {
                        if(ue.getAnswerId() == 0){
                            gameMetrics.setTeamSatisfaction(gameMetrics.getTeamSatisfaction() + 3);
                            return gameMetrics;
                        } else if(ue.getAnswerId() == 1) {
                            return gameMetrics;
                        } else if(ue.getAnswerId() == 2) {
                            return gameMetrics;
                        }
                    }
                    default:{
                        return gameMetrics;
                    }
                }
            }
        }
        return gameMetrics;
    }
}
