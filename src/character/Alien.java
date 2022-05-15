package character;

import character_vars.Side;
import character_vars.StatsChart;
import character_vars.Type;

public class Alien extends Character {

    public Alien(StatsChart statsChart, int level, int rank, String tier, Type type, Side side, String name) {
        super(statsChart, level, rank, tier, type, side, name);
        statsChart.setPhysicalDefence(statsChart.getPhysicalDefence()+15.0);
    }
}
