package battle;

import character.Character;
import java.util.List;
import java.util.Scanner;
import util.IntReader;

public class SelectionPhase {

    /**
     * Presents the character list to the user, allows the user to check the characters, and ask the user to choose one.
     * @param characters- a list of characters to choose from
     * @param input - an open scanner to ask the user
     * @return the chosen character's index
     */
    public static int chooseChampion(List<Character> characters, Scanner input) {
        return checkCharacter(characters, input, whoToCheck(characters, input));
    }

    /**
     * Shows character list and asks the user to choose a character to check out.
     * @param characters- a list of characters to choose from
     * @param input - an open scanner to ask the user
     * @return the index of the character to check
     */
    private static int whoToCheck(List<Character> characters, Scanner input) {
        boolean chosen = false;
        int charNum = -1;
        while (!chosen) {
            System.out.println("\n Who would you like to check? (type in the number)");
            for(int i=0; i<characters.size(); i++) {
                System.out.println((i+1)+"- "+characters.get(i).getName());
            }
            charNum = IntReader.readInt(input);
            chosen = charNum>0 && charNum<=characters.size();
            if(!chosen) {
                System.out.println("Invalid input, please enter the number of the character you would like to check.");
            }
        }
        return charNum-1;
    }

    /**
     * Allows the user to ask the character to say something, see the character's stats, choose the character or view a different character.
     * Continues asking the user to choose an action until a character is chosen.
     * @param characters- a list of characters to choose from
     * @param input - an open scanner to ask the user
     * @param index - the index of the character for check
     * @return the chosen character's index
     */
    private static int checkCharacter(List<Character> characters, Scanner input, int index) {
        boolean chosen = false;
        Character current = characters.get(index);
        while(!chosen) {
            System.out.println("\n What would you like to do with "+current.getName()+"?");
            System.out.println("1- ask to say something");
            System.out.println("2- see stats");
            System.out.println("3- choose for battle");
            System.out.println("4- go back to character list");
            System.out.print("Your response (number of the action): ");
            int response = IntReader.readInt(input);
            switch (response){
                case 1:
                    System.out.println("\n "+current.getName()+" says:");
                    System.out.println(current.saySomething());
                    break;
                case 2:
                    System.out.println(current.showStatus());
                    break;
                case 3:
                    System.out.println("Chosen!");
                    chosen = true;
                    break;
                case 4:
                    index = whoToCheck(characters, input);
                    current = characters.get(index);
                    break;
                default:
                    System.out.println("Invalid input, please enter the number of the action you would like to take");
            }
        }
        return index;
    }
}
