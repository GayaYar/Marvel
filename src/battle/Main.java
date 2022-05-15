package battle;

import character.Character;
import java.util.List;
import java.util.Scanner;

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
            System.out.println("Play again? (type 'y' for yes or 'n' for no)");
            String response = input.nextLine();
            response = response.toUpperCase();
            if(response.startsWith("Y")) {
                characters.add(userHero);
                System.out.println("Let's go again!");
            }else if(response.startsWith("N")) {
                play = false;
                System.out.println("Nice battle. Goodbye.");
            }else {
                play = false;
                System.out.println("Could not understand your input. Exiting game. Goodbye.");
            }
        }
        input.close();
    }
}
