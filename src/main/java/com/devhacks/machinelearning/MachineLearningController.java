package com.devhacks.machinelearning;

import com.devhacks.bean.GameMetrics;
import com.devhacks.bean.InitialGameScenarioBean;
import com.devhacks.bean.WeekSprintBean;
import com.devhacks.events.EventsManager;
import com.devhacks.initial.InitialContextProvider;
import com.devhacks.initial.RealTimeInitialComputation;
import com.devhacks.model.Developer;
import com.devhacks.model.UnexpectedEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ciprian on 10/28/2017.
 */
@Controller
public class MachineLearningController {


    @Inject
    GAlgorithm gAlgorithm;

    private  InitialGameScenarioBean initialGameScenarioBean = null;

    private GameMetrics gameMetricsa = null;

    @Inject
    InitialContextProvider initialContextProvider;

    @Inject
    RealTimeInitialComputation realTimeInitialComputation;

    @Inject
    EventsManager eventsManager;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/determinateBestInitialContext", method = RequestMethod.GET)
    public
    @ResponseBody
    InitialGameScenarioBean determinateBestInitialContext() {

        initialGameScenarioBean = initialContextProvider.generateRandomInitialContext();

        Individual individual = gAlgorithm.computeGA(initialGameScenarioBean);

        List<Developer> devsList = new ArrayList<>();
        for(int i=0;i<individual.getDna().size();i++){
            if(individual.getDna().get(i) == 1){
                devsList.add(initialGameScenarioBean.getDeveloperList().get(i));
            }
        }

        initialGameScenarioBean.setDeveloperList(devsList);

        gameMetricsa = realTimeInitialComputation.computeMetrics(initialGameScenarioBean,2);

        return initialGameScenarioBean;

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/determinateBestGameContext", method = RequestMethod.GET)
    public
    @ResponseBody String determinateBestGameContext(){

        List<UnexpectedEvent> unexpectedEvents = eventsManager.getUnexpectedEvents();
        gAlgorithm.initPopulationGameScenario(initialGameScenarioBean,gameMetricsa,unexpectedEvents);
        Individual individual = gAlgorithm.computeGAReal(initialGameScenarioBean,gameMetricsa,unexpectedEvents);

        return individual.toString() + " " + unexpectedEvents.toString() + " " + gameMetricsa.toString();
    }
}
