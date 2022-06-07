package character;

import character_vars.Side;
import character_vars.StatsChart;
import character_vars.Type;

public class Human extends Character {

    public Human(StatsChart statsChart, int level, int rank, String tier, Type type, Side side, String name) {
        super(statsChart, level, rank, tier, type, side, name);
        statsChart.setPhysicalAttack(statsChart.getPhysicalAttack()+5.0);
    }
}
