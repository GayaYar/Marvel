package character;

import character_vars.Side;
import character_vars.StatsChart;
import character_vars.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;



public abstract class Character {

    protected StatsChart statsChart;
    protected int level;
    protected int rank;
    protected String tier;
    protected Type type;
    protected Side side;
    protected String name;
    protected int turn;
    protected boolean safe;
    protected List<Function<Integer, Boolean>> beforeTurnActions;

    public Character(StatsChart statsChart, int level, int rank, String tier, Type type, Side side, String name) {
        this.statsChart = statsChart;
        this.level = level;
        this.rank = rank;
        this.tier = tier;
        this.type = type;
        this.side = side;
        this.name = name;
        turn = 0;
        safe = false;
        beforeTurnActions = new LinkedList<>();
        beforeTurnActions.add((num) -> {
            increaseEnergy();
            return true;
        });
    }


    public double attack() {
        if (statsChart.getEnergy() > 0) {
            decreaseEnergy();
            return statsChart.getPhysicalAttack();
        }
        return 0;
    }

    public String saySomething() {
        return "I'm " + name + "!";
    }

    public String showStatus() {
        return statsChart.toString();
    }

    /**
     * calculates if the defence attempt was successful if so, sets the character as safe from attack for this turn
     * (adds a method to turn "safe" back to false at the next turn)
     *
     * @return if the defence attempt was successful
     */
    public boolean defend() {
        double random = Math.random()*100+1;
        if (random <= 70) {
            safe = true;
            int currentTurn = turn;
            beforeTurnActions.add((checkTurn) -> {
                if (checkTurn == currentTurn+1) {
                    safe = false;
                }
                return true;
            });
            return true;
        }
        return false;
    }

    public boolean takeTurn() {
        turn++;
        boolean shouldPlay = true;
        int currentTurn = turn;
        for (Function<Integer, Boolean> action : beforeTurnActions
        ) {
            shouldPlay = shouldPlay && action.apply(currentTurn);
        }
        return shouldPlay;
    }

    protected void decreaseEnergy() {
        statsChart.setEnergy((statsChart.getEnergy() - 1) / 2);
    }

    protected void increaseEnergy() {
        statsChart.setEnergy(statsChart.getEnergy() + 1);
    }

    public double underAttack(double power) {
        if(safe) {
            return 0;
        }else {
            double hpToReduce = power*((100-statsChart.getPhysicalDefence()) / 100.00);
            statsChart.changeHp(-hpToReduce);
            return hpToReduce;
        }
    }

    @Override
    public String toString() {
        return "character.Character{" +
                "stats=" + statsChart +
                ", level=" + level +
                ", rank=" + rank +
                ", tier='" + tier + '\'' +
                ", type=" + type +
                ", side=" + side +
                ", name='" + name + '\'' +
                '}';
    }

    public Type getType() {
        return type;
    }

    public double getHp() {
        return statsChart.getHp();
    }

    public String getName() {
        return name;
    }

    /**
     * Multiplies the current attack power by "timesStronger" if "timesStronger" is bigger than 0.
     * If "turns" is greater than 0, will return to the current attack power after the specified amount of turns.
     * @param timesStronger - number to multiply the current attack power with
     * @param turns - number of turns to wait before turning back to current (before this method) attack power. If<=0, will not change back.
     */
    public void updateAttack(double timesStronger, int turns) {
        if(timesStronger>0) {
            final double currentAttack = statsChart.getPhysicalAttack();
            statsChart.setPhysicalAttack(currentAttack*timesStronger);
            if(turns>0) {
                final int currentTurn = turn;
                beforeTurnActions.add((checkTurn) -> {
                    if(checkTurn == currentTurn+turns+1) {
                        statsChart.setPhysicalAttack(currentAttack);
                    };
                    return true;
                });
            }
        }
    }

    /**
     * Adds the amount to physical defence.
     * If "turns" is greater than 0, will return to the current defence power after the specified amount of turns.
     * @param amount - amount to add to defence
     * @param turns - number of turns to wait before turning back to current (before this method) defence power. If<=0, will not change back.
     */
    public void updateDefence(double amount, int turns) {
        final double currentDefence = statsChart.getPhysicalDefence();
        statsChart.setPhysicalDefence(currentDefence-amount);
        if(turns>0) {
            final int currentTurn = turn;
            beforeTurnActions.add((checkTurn) -> {
                if(checkTurn == currentTurn+turns+1) {
                    statsChart.setPhysicalDefence(currentDefence);
                };
                return true;
            });
        }
    }

    public String useSideAbility(Character opponent) {
        return side.useSpecial(this, opponent);
    }

    public Side getSide() {
        return side;
    }

    public void levelUp() {
        this.level++;
    }
}


