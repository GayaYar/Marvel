package battle;

import character.Character;
import java.util.Scanner;
import java.util.function.IntSupplier;
import util.DoubleFormatter;
import util.IntReader;

public class BattlePhase {
    private double hp1 = 0;
    private double hp2 = 0;


    /**
     * While no one lost (both characters have hp higher than 0):
     * asks the hero weather to play this turn
     * if so- gets the action to play (asks user to choose action or choose random for computer) and plays it
     * checks if anyone lost.
     *
     * When the fight is over, prints message to the user and levels up the winner character.
     * @param userHero
     * @param computerHero
     */
    public void fight(Character userHero, Character computerHero, Scanner input) {
        while (validateHP(userHero, computerHero)) {

//            if (userHero.takeTurn()) {
//                int action = askForAction(input, userHero.getName());
//                mapActions(userHero, computerHero, action, input);
//            }else {
//                System.out.println(userHero.getName()+"'s turn skipped");
//            }
//            System.out.println();
//            if(validateHP(userHero, computerHero) && computerHero.takeTurn()) {
//                mapActions(computerHero, userHero, (int)(Math.random()*3)+1, input);
//            }

        }
        if (hp1 <= 0 && hp2 <= 0) {
            System.out.println("Both sides are too injured to continue and have to wait to the sequel. Battle Over.");
        } else {
            Character winner = hp1<=0 ? computerHero : userHero;
            System.out.println(winner.getName()+" won! The opponent is dead for at least one movie...");
            winner.levelUp();
        }
    }

    private void playTurn(IntSupplier getAction, Character player, Character opponent, Scanner input) {
        if(player.takeTurn()) {
            int action = getAction.getAsInt();
            mapActions(player, opponent, action, input);
        }else {
            System.out.println(player.getName()+"'s turn is skipped.");
        }
    }

    private boolean validateHP(Character hero1, Character hero2) {
        this.hp1 = hero1.getHp();
        this.hp2 = hero2.getHp();
        return hp1 > 0 && hp2 > 0;
    }

    private int askForAction(Scanner input, String name) {
        System.out.println();
        System.out.println(name + ", it's your turn, what would you like to do?");
        boolean validAnswer = false;
        int answer = -1;
        while (!validAnswer) {
            System.out.println("Fight (mutants have a stronger fight that takes 2 turns): " + 1);
            System.out.println("Defend: " + 2);
            System.out.println("Side ability (moral for hero, slur for villain or neutral for neutral): "+3);
            System.out.println("Show stats: " + 0);
            answer = IntReader.readInt(input);
            if (answer > 3 || answer < 0) {
                System.out.println("Not a valid answer, what would you like to do?");
            } else {
                validAnswer = true;
            }
        }

        return answer;
    }

    private void mapActions(Character player, Character opponent, int action, Scanner input) {
        String playerName = player.getName();
        switch (action) {
            case 1:
                System.out.println(playerName+" chose to fight!");
                playerFights(player, opponent);
                break;
            case 2:
                System.out.println(playerName+" tries to defend.");
                playerDefends(player);
                break;
            case 3:
                System.out.println(playerName+" goes for special affect.");
                useSpecial(player, opponent);
                break;
            default:
                System.out.println(playerName+" stats:");
                System.out.println(player.showStatus());
                System.out.println();
                System.out.println(opponent.getName()+" stats:");
                System.out.println(opponent.showStatus());
                System.out.println();
                mapActions(player, opponent, askForAction(input, player.getName()), input);
        }
    }

    private void playerFights(Character player, Character opponent) {
        double attackPower = player.attack();
        if(player.getType().isAgainst(opponent.getType())) {
            attackPower *= 1.1;
        }
        System.out.println(player.getName()+" uses "+player.getType()+" and strikes at "
                + DoubleFormatter.formatToString(attackPower) +" strength power.");
        double lostHp = opponent.underAttack(attackPower);
        if(lostHp == 0){
            System.out.println(opponent.getName()+" stays safe.");
        }else {
            System.out.println(opponent.getName()+" loses "+DoubleFormatter.formatToString(lostHp)+" hp.");
        }
    }

    private void playerDefends(Character player) {
        boolean successfulDefence = player.defend();
        if(successfulDefence) {
            System.out.println(player.getName()+" defends successfully and will be protected from attacks at this turn.");
        }else {
            System.out.println(player.getName()+" tried to defend and failed...");
        }
    }

    private void useSpecial(Character player, Character opponent) {
        switch (player.getSide()) {
            case SUPER_HERO:
                String message =  player.superHeroSpecial()?
                        player.getName()+" uses moral affect and gains 20% more attack power for the next 3 rounds." :
                        player.getName()+" is unable to use the moral affect.";
                System.out.println(message);
                break;
            case SUPER_VILLAIN:
                double newDefence = opponent.hitBySuperVillain();
                System.out.println(player.getName()+" uses the slur affect and reduces his opponents defence power to "
                        +newDefence+" for 3 turns.");
                break;
            default:
                System.out.println(player.getName()+" says:");
                System.out.println(player.neutralHeroSpecial());
        }
    }
}
