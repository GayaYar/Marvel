package battle;

import character.Alien;
import character.Character;
import character.Human;
import character.Mutant;
import character.Other;
import character_vars.Side;
import character_vars.StatsChart;
import character_vars.Type;
import java.util.ArrayList;
import java.util.List;

public class CharacterGenerator {
    public static List<Character> initialiseList() {
        List<Character> characters = new ArrayList<>(10);
        characters.add(new Mutant(new StatsChart(70.0, 90.0, 7), 0, 23,
                "XMen", Type.UNIVERSAL, Side.SUPER_VILLAIN, "Dark Phoenix"));
        characters.add(new Human(new StatsChart(40.0, 50.0, 8), 0, 52,
                "Avengers", Type.COMBAT, Side.SUPER_HERO, "Captain America"));
        characters.add(new Alien(new StatsChart(30.0, 55.0, 7), 0, 87,
                "Guardians of the Galaxy", Type.SPEED, Side.SUPER_HERO, "Gamorrah"));
        characters.add(new Alien(new StatsChart(85.0, 85.0, 9), 0, 15,
                "Titan", Type.UNIVERSAL, Side.SUPER_VILLAIN, "Thanos"));
        characters.add(new Human(new StatsChart(40, 70, 7), 0, 1,
                "Avengers", Type.BLAST, Side.SUPER_HERO, "Spider Man"));
        characters.add(new Other(new StatsChart(60.0, 45.0, 7), 0, 90,
                "Asgardian", Type.UNIVERSAL, Side.SUPER_VILLAIN, "Loki"));
        characters.add(new Mutant(new StatsChart(65.0, 30.0, 10), 0, 85,
                "XMen", Type.COMBAT, Side.NEUTRAL, "Deadpool"));
        characters.add(new Human(new StatsChart(90.0, 90.0, 8), 0, 26,
                "Avengers", Type.UNIVERSAL, Side.SUPER_HERO, "Wanda"));
        characters.add(new Human(new StatsChart(30.0, 60.0, 6), 0, 87,
                "Self", Type.SPEED, Side.SUPER_HERO, "Wasp"));
        characters.add(new Mutant(new StatsChart(60.0, 80.0, 8), 0, 88,
                "XMen", Type.COMBAT, Side.NEUTRAL, "Wolverine"));
        return characters;
    }
}
