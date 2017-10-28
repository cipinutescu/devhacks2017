package com.devhacks.game;

import com.devhacks.bean.GameMetrics;
import com.devhacks.bean.InitialGameScenarioBean;
import com.devhacks.model.UnexpectedEvent;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ciprian on 10/27/2017.
 */
@Service
public class GameStatisticsManager {

    private List<UnexpectedEvent> unexpectedEventThisSprint;

    private InitialGameScenarioBean currentGameScenario;

    private InitialGameScenarioBean modifiedGameScenario;

    private GameMetrics gameMetrics;

    private int wps;

    private int sprintNo=0;

    private int weekNo=0;

    public int getSprintNo() {
        return sprintNo;
    }

    public void setSprintNo(int sprintNo) {
        this.sprintNo = sprintNo;
    }

    public int getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(int weekNo) {
        this.weekNo = weekNo;
    }

    public InitialGameScenarioBean getCurrentGameScenario() {
        return currentGameScenario;
    }

    public void setCurrentGameScenario(InitialGameScenarioBean currentGameScenario) {
        this.currentGameScenario = currentGameScenario;
    }

    public InitialGameScenarioBean getModifiedGameScenario() {
        return modifiedGameScenario;
    }

    public void setModifiedGameScenario(InitialGameScenarioBean modifiedGameScenario) {
        this.modifiedGameScenario = modifiedGameScenario;
    }

    public int getWps() {
        return wps;
    }

    public void setWps(int wps) {
        this.wps = wps;
    }

    public GameMetrics getGameMetrics() {
        return gameMetrics;
    }

    public void setGameMetrics(GameMetrics gameMetrics) {
        this.gameMetrics = gameMetrics;
    }

    public List<UnexpectedEvent> getUnexpectedEventThisSprint() {
        return unexpectedEventThisSprint;
    }

    public void setUnexpectedEventThisSprint(List<UnexpectedEvent> unexpectedEventThisSprint) {
        this.unexpectedEventThisSprint = unexpectedEventThisSprint;
    }
}
