package com.devhacks.machinelearning;

import java.util.List;

/**
 * Created by Ciprian on 10/28/2017.
 */
public class Individual {
    private List<Integer> dna;
    private Double fitness;

    public Individual(List<Integer> dna, Double fitness) {
        this.dna = dna;
        this.fitness = fitness;
    }

    public List<Integer> getDna() {
        return dna;
    }

    public void setDna(List<Integer> dna) {
        this.dna = dna;
    }

    public Double getFitness() {
        return fitness;
    }

    public void setFitness(Double fitness) {
        this.fitness = fitness;
    }

    @Override
    public String toString() {
        return "Individual{" +
                "dna=" + dna +
                ", fitness=" + fitness +
                '}';
    }
}
