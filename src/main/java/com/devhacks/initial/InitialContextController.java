package com.devhacks.initial;

import com.devhacks.authentification.AuthentificationManager;
import com.devhacks.bean.GameMetrics;
import com.devhacks.bean.InitialGameScenarioBean;
import com.devhacks.game.GameStatisticsManager;
import com.devhacks.model.Developer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Ciprian on 10/27/2017.
 */
@Controller
public class InitialContextController {

    @Inject
    private InitialContextProvider initialContextProvider;

    @Inject
    private AuthentificationManager authentificationManager;

    @Inject
    private RealTimeInitialComputation realTimeInitialComputation;

    @Inject
    private GameStatisticsManager gameStatisticsManager;

    private InitialGameScenarioBean currentGameScenario;

    private InitialGameScenarioBean modifiedGameScenario;


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getInitialGameScenario", method = RequestMethod.GET)
    public  @ResponseBody
    InitialGameScenarioBean getScenario(@RequestParam(value = "username") String username,
                                          @RequestParam(value = "token") String token) {
        if(authentificationManager.validateAuthentification(username,token)){
            currentGameScenario = initialContextProvider.generateRandomInitialContext();
            return currentGameScenario;
        } else {
            return null;
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/simulateGameScenario", method = RequestMethod.GET)
    public  @ResponseBody
    GameMetrics computeMetricsRealtime(@RequestParam(value = "username") String username,
                                       @RequestParam(value = "token") String token,
                                       @RequestParam(value = "wps") String wps,
                                       @RequestParam(value = "devs") String ids) {
        if(authentificationManager.validateAuthentification(username,token)){
            StringTokenizer stringTokenizer = new StringTokenizer(ids,",");
            List<Developer> newDevsSelected = new ArrayList<>();
            while (stringTokenizer.hasMoreTokens()){
                newDevsSelected.add(currentGameScenario.getDeveloperList()
                        .get(Integer.parseInt(stringTokenizer.nextToken())));
            }
            modifiedGameScenario = new InitialGameScenarioBean(newDevsSelected,
                    currentGameScenario.getStoryList(),currentGameScenario.getTechnologiesDistribution(),currentGameScenario.getInitialCustomerSatisfaction(),currentGameScenario.getInitalProjectInvestmentBudget(),currentGameScenario.getEstimatedNumberOfWeeks());
            gameStatisticsManager.setModifiedGameScenario(modifiedGameScenario);
            gameStatisticsManager.setWps(Integer.parseInt(wps));
            gameStatisticsManager.setGameMetrics(realTimeInitialComputation.computeMetrics(modifiedGameScenario,Integer.parseInt(wps)));
            return realTimeInitialComputation.computeMetrics(modifiedGameScenario,Integer.parseInt(wps));
        } else {
            return null;
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/startGame", method = RequestMethod.GET)
    public  @ResponseBody
    String startGame(@RequestParam(value = "username") String username,
                                       @RequestParam(value = "token") String token) {
        if (authentificationManager.validateAuthentification(username, token)) {
            gameStatisticsManager.setSprintNo(0);
            gameStatisticsManager.setWeekNo(0);
            return "OK";
        } else {
            return "NOK";
        }
    }

    private InitialGameScenarioBean getCurrentGameScenario(){
        return this.modifiedGameScenario;
    }

}
