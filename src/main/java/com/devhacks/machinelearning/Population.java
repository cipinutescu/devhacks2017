package com.devhacks.machinelearning;

import java.util.List;

/**
 * Created by Ciprian on 10/28/2017.
 */
public class Population {
    List<Individual> individualList;

    public Population(List<Individual> individualList) {
        this.individualList = individualList;
    }

    public List<Individual> getIndividualList() {
        return individualList;
    }

    public void setIndividualList(List<Individual> individualList) {
        this.individualList = individualList;
    }
}
