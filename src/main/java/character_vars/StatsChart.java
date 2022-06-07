package character_vars;

import util.DoubleFormatter;

public class StatsChart {
    private double physicalAttack;
    private double physicalDefence;
    private int energy;
    private double hp;

    public StatsChart(double physicalAttack, double physicalDefence, int energy) {
        setPhysicalAttack(physicalAttack);
        setPhysicalDefence(physicalDefence);
        this.energy = energy;
        this.hp = 100.0;
    }

    @Override
    public String toString() {
        return "Stats: " +
                "\n HP=" + hp +
                "\n Attack Power= " + DoubleFormatter.formatToString(physicalAttack) +
                "\n physicalDefence= " + DoubleFormatter.formatToString(physicalDefence) +
                "\n energy=" + energy ;
    }

    public double changeHp(double amount) {
        hp += amount;
        return hp;
    }
    public double getPhysicalAttack() {
        return physicalAttack;
    }

    public void setPhysicalAttack(double physicalAttack) {
        if(physicalAttack>100) {
            this.physicalAttack=100;
        }else if(physicalAttack<0) {
            this.physicalAttack=0;
        }else {
            this.physicalAttack = physicalAttack;
        }
    }

    public double getPhysicalDefence() {
        return physicalDefence;
    }

    public void setPhysicalDefence(double physicalDefence) {
        if(physicalDefence>100) {
            this.physicalDefence=100;
        }else if(physicalDefence<0) {
            this.physicalDefence=0;
        }else {
            this.physicalDefence = physicalDefence;
        }
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }
}
