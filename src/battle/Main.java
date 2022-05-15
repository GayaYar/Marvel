package battle;

import character.Character;
import java.util.List;
import java.util.Scanner;
import util.ContinuousAsker;

public class Main {

    public static void main(String[] args) {
        List<Character> characters = CharacterGenerator.initialiseList();
        Scanner input = new Scanner(System.in);
        boolean play = true;
        while (play) {
            int userHeroIndex = SelectionPhase.chooseChampion(characters, input);
            Character userHero = characters.remove(userHeroIndex);
            Character computerHero = characters.get((int)(Math.random()*characters.size()));
            System.out.println("Computer chose "+computerHero.getName());
            BattlePhase battlePhase = new BattlePhase();
            battlePhase.fight(userHero, computerHero, input);
//            System.out.println("Play again? (type 'y' for yes or 'n' for no)");
//            String response = input.nextLine();
//            response = response.toUpperCase();
//            if(response.startsWith("Y")) {
//                characters.add(userHero);
//                System.out.println("Let's go again!");
//            }else if(response.startsWith("N")) {
//                play = false;
//                System.out.println("Nice battle. Goodbye.");
//            }else {
//                play = false;
//                System.out.println("Could not understand your input. Exiting game. Goodbye.");
//            }
            String question = "Play again? (type 'y' for yes or 'n' for no)";
            String playAgain = ContinuousAsker.ask(question, input::nextLine
                    , (response) -> {
                response = response.toUpperCase();
                return response.startsWith("Y") || response.startsWith("N");
            }, "Could not understand your input.");

            if(playAgain.toUpperCase().startsWith("Y")) {
                characters.add(userHero);
                System.out.println("Let's go again!");
            }else {
                play = false;
                System.out.println("Nice battle. Goodbye.");
            }
        }
        input.close();
    }
}
