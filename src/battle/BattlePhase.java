package battle;

import character.Character;
import java.util.Scanner;
import java.util.function.IntSupplier;
import util.ContinuousAsker;
import util.DoubleFormatter;
import util.IntReader;

public class BattlePhase {
    private Character userHero;
    private Character computerHero;
    private double userHp = 0;
    private double computerHp = 0;
    private Scanner input;
    private String userHeroName;


    /**
     * While no one lost (both characters have hp higher than 0):
     * asks the hero weather to play this turn
     * if so- gets the action to play (asks user to choose action or choose random for computer) and plays it
     * checks if anyone lost.
     *
     * When the fight is over, prints message to the user and levels up the winning character.
     * @param userHero - the user's character
     * @param computerHero - the computer's character
     * @param input - an open scanner
     */
    public void fight(Character userHero, Character computerHero, Scanner input) {
        this.input = input;
        userHeroName = userHero.getName();
        this.userHero = userHero;
        this.computerHero = computerHero;
        while (validateHP()) {
            playTurn(this::askForAction, this.userHero, this.computerHero);
            if(validateHP()) {
                playTurn(()->{return ((int)(Math.random()*3)+1);}, this.computerHero, this.userHero);
            }
        }
        if (userHp <= 0 && computerHp <= 0) {
            System.out.println("Both sides are too injured to continue and have to wait to the sequel. Battle Over.");
        } else {
            Character winner = userHp <=0 ? computerHero : userHero;
            System.out.println(winner.getName()+" won! The opponent is dead for at least one movie...");
            winner.levelUp();
        }
    }

    private void playTurn(IntSupplier getAction, Character player, Character opponent) {
        if(player.takeTurn()) {
            int action = getAction.getAsInt();
            mapActions(player, opponent, action);
        }else {
            System.out.println(player.getName()+"'s turn is skipped.");
        }
    }

    private boolean validateHP() {
        this.userHp = userHero.getHp();
        this.computerHp = computerHero.getHp();
        return userHp > 0 && computerHp > 0;
    }

    /**
     * Continuously asks the user to choose an action from the action list until a valid response is received.
     * @return the chosen action number
     */
    private int askForAction() {
        System.out.println();
        System.out.println(userHeroName + ", it's your turn, what would you like to do?");

        String question = "Fight (mutants have a stronger fight that takes 2 turns): " + 1 + "\n"
                + "Defend: " + 2 + "\n"
                + "Side ability: " + 3 + "\n"
                + "Show stats: " + 0;
        Integer answer = ContinuousAsker.ask(question
                , () -> {
                    return IntReader.readInt(input);
                }, (num) -> {
                    return num >= 0 && num <= 3;
                },
                "Not a valid answer, what would you like to do?");

        return answer;
    }

    private void mapActions(Character player, Character opponent, int action) {
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
                System.out.println(playerName+" goes for special side ability.");
                String abilityResponse = player.useSideAbility(opponent);
                System.out.println(playerName+" says- "+abilityResponse);
                break;
            default:
                System.out.println(playerName+" stats:");
                System.out.println(player.showStatus());
                System.out.println();
                System.out.println(opponent.getName()+" stats:");
                System.out.println(opponent.showStatus());
                System.out.println();
                mapActions(player, opponent, askForAction());
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
}
