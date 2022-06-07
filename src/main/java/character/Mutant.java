package character;

import character_vars.Side;
import character_vars.StatsChart;
import character_vars.Type;

public class Mutant extends Character {

    public Mutant(StatsChart statsChart, int level, int rank, String tier, Type type, Side side, String name) {
        super(statsChart, level, rank, tier, type, side, name);
    }

    /**
     * strengthens the attack power, turn after this will not be played
     * @return the stronger attack power
     */
    @Override
    public double attack() {
        int currentTurn = turn;
        beforeTurnActions.add((checkTurn) -> { return checkTurn != currentTurn+1;});
        return super.attack()*1.5;
    }
}
