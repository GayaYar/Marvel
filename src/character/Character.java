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
            double hpToReduce = power*(statsChart.getPhysicalDefence() / 100.00);
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

    public boolean superHeroSpecial() {
        if(this.side == Side.SUPER_HERO) {
            final double currentAttack = statsChart.getPhysicalAttack();
            statsChart.setPhysicalAttack(currentAttack*1.2);
            final int currentTurn = turn;
            beforeTurnActions.add((checkTurn) -> {
                if(checkTurn == currentTurn+4) {
                    statsChart.setPhysicalAttack(currentAttack);
                };
                return true;
            });
            return true;
        }
        return false;
    }

    public double hitBySuperVillain() {
       final double currentDefence = statsChart.getPhysicalDefence();
       final int currentTurn = turn;
       statsChart.setPhysicalDefence(currentDefence-15);
        beforeTurnActions.add((checkTurn) -> {
            if(checkTurn == currentTurn+4) {
                statsChart.setPhysicalDefence(currentDefence);
            };
            return true;
        });
        return statsChart.getPhysicalDefence();
    }

    public String neutralHeroSpecial() {
        if(this.side == Side.NEUTRAL) {
            return "I'm "+name+"! I'm neutral.";
        }
        return "I'm not neutral in this.";
    }

    public Side getSide() {
        return side;
    }

    public void levelUp() {
        this.level++;
    }
}


