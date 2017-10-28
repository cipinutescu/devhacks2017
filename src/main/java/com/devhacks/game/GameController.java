package com.devhacks.game;

import com.devhacks.authentification.AuthentificationManager;
import com.devhacks.bean.GameMetrics;
import com.devhacks.bean.WeekSprintBean;
import com.devhacks.events.EventsManager;
import com.devhacks.initial.InitialContextProvider;
import com.devhacks.initial.RealTimeInitialComputation;
import com.devhacks.model.PlanningEvent;
import com.devhacks.model.UnexpectedEvent;
import com.devhacks.model.UserStory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Ciprian on 10/28/2017.
 */
@Controller
public class GameController {
    @Inject
    private InitialContextProvider initialContextProvider;

    @Inject
    private AuthentificationManager authentificationManager;

    @Inject
    private RealTimeInitialComputation realTimeInitialComputation;

    @Inject
    private GameStatisticsManager gameStatisticsManager;

    @Inject
    private EventsManager eventsManager;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getGameStatistics", method = RequestMethod.GET)
    public
    @ResponseBody
    GameMetrics computeMetricsRealtime(@RequestParam(value = "username") String username,
                                       @RequestParam(value = "token") String token) {
        if (authentificationManager.validateAuthentification(username, token)) {

            return realTimeInitialComputation.computeMetrics(gameStatisticsManager.getModifiedGameScenario(), gameStatisticsManager.getWps());
        } else {
            return null;
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/usePlanningEvents", method = RequestMethod.GET)
    public
    @ResponseBody
    GameMetrics applyPlannedEvent(@RequestParam(value = "username") String username,
                                  @RequestParam(value = "token") String token,
                                  @RequestParam(value = "plannedEvent") String plannedEvent) {
        if (authentificationManager.validateAuthentification(username, token)) {
            PlanningEvent planningEvent = new PlanningEvent(plannedEvent);
            GameMetrics gameMetrics = planningEvent.computeNewGameMetrics(gameStatisticsManager.getGameMetrics(), gameStatisticsManager.getModifiedGameScenario());
            gameStatisticsManager.setGameMetrics(gameMetrics);
            return gameMetrics;

        } else {
            return null;
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getUnexpectedEvents", method = RequestMethod.GET)
    public
    @ResponseBody
    List<UnexpectedEvent> getUnExpectedEvents(@RequestParam(value = "username") String username,
                                              @RequestParam(value = "token") String token) {
        if (authentificationManager.validateAuthentification(username, token)) {
            List<UnexpectedEvent> unexpectedEventList = eventsManager.getUnexpectedEvents();
            gameStatisticsManager.setUnexpectedEventThisSprint(unexpectedEventList);
            return unexpectedEventList;
        } else {
            return null;
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/takeDecisions", method = RequestMethod.GET)
    public
    @ResponseBody
    String takeDecisions(@RequestParam(value = "username") String username,
                         @RequestParam(value = "token") String token,
                         @RequestParam(value = "ids1") String ids1,
                         @RequestParam(value = "ids1") String ids2,
                         @RequestParam(value = "ids1") String ids3,
                         @RequestParam(value = "ids1") String ids4) {
        if (authentificationManager.validateAuthentification(username, token)) {
            List<UnexpectedEvent> unexpectedEventList = gameStatisticsManager.getUnexpectedEventThisSprint();
            if(unexpectedEventList != null)
                for (UnexpectedEvent unexpectedEvent : unexpectedEventList) {
                switch (unexpectedEvent.getId()) {
                    case 1: {
                        unexpectedEvent.setAnswerId(Integer.parseInt(ids1));
                        break;
                    }
                    case 2: {
                        unexpectedEvent.setAnswerId(Integer.parseInt(ids2));
                        break;
                    }
                    case 3: {
                        unexpectedEvent.setAnswerId(Integer.parseInt(ids3));
                        break;
                    }
                    case 4: {
                        unexpectedEvent.setAnswerId(Integer.parseInt(ids4));
                    }
                }
                GameMetrics gameMetrics = eventsManager.computeNewGameMetrics(gameStatisticsManager.getGameMetrics(), gameStatisticsManager.getModifiedGameScenario(), unexpectedEvent);

                gameStatisticsManager.setGameMetrics(gameMetrics);
            }

            GameMetrics gameMetrics = gameStatisticsManager.getGameMetrics();

            gameMetrics.setCurrentBacklogSize(gameMetrics.getCurrentBacklogSize() - gameMetrics.getVelocity());
            if(gameMetrics.getTeamSatisfaction() > 100){
                gameMetrics.setVelocity(gameMetrics.getVelocity() + 30);
            } else if(gameMetrics.getTeamSatisfaction() > 90){
                gameMetrics.setVelocity(gameMetrics.getVelocity() + 10);
            } else if(gameMetrics.getTeamSatisfaction() < 85){
                gameMetrics.setVelocity(gameMetrics.getVelocity() - 10);
            } else if(gameMetrics.getTeamSatisfaction() < 75){
                gameMetrics.setVelocity(gameMetrics.getVelocity() - 20);
            }
            if(gameMetrics.getEstimatedWeeksToCompletion()-3 <= gameMetrics.getCustomerExpectedWeeksToCompletion()){
                gameMetrics.setCustomerSatisfaction(gameMetrics.getCustomerSatisfaction() + 25);
            } else if(gameMetrics.getEstimatedWeeksToCompletion()-1 == gameMetrics.getCustomerExpectedWeeksToCompletion()){
                gameMetrics.setCustomerSatisfaction(gameMetrics.getCustomerSatisfaction() + 10);
            } else if(gameMetrics.getEstimatedWeeksToCompletion() + 4 >= gameMetrics.getCustomerExpectedWeeksToCompletion()){
                gameMetrics.setCustomerSatisfaction(gameMetrics.getCustomerSatisfaction() - 10);
            }

            gameStatisticsManager.setSprintNo(gameStatisticsManager.getSprintNo() + 1);
            gameStatisticsManager.setWeekNo(gameStatisticsManager.getWps() * gameStatisticsManager.getSprintNo());
            if (gameMetrics.getCurrentBacklogSize().doubleValue() / gameStatisticsManager.getModifiedGameScenario().getStoryList().stream().mapToInt(UserStory::getStory_points).sum() > 0.85 &&
                    gameMetrics.getTeamSatisfaction() > 85 && gameMetrics.getCustomerSatisfaction() > 90 && gameMetrics.getProjectInvestmentFund() > 0) {
                return "WON";
            }
            if (gameMetrics.getTeamSatisfaction() < 70 || gameMetrics.getCustomerSatisfaction() < 70 || gameMetrics.getProjectInvestmentFund() < -10000) {
                return "LOST";
            }
            return "OK";

        } else {
            return null;
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getWeekSprint", method = RequestMethod.GET)
    public
    @ResponseBody
    WeekSprintBean getWeekSprint(@RequestParam(value = "username") String username,
                                        @RequestParam(value = "token") String token) {
        if (authentificationManager.validateAuthentification(username, token)) {
            return new WeekSprintBean(gameStatisticsManager.getWeekNo(),gameStatisticsManager.getSprintNo());
        } else {
            return null;
        }
    }
}
