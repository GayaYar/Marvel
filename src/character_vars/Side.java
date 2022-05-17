package character_vars;

import character.Character;
import java.util.function.BiFunction;

public enum Side {
    SUPER_HERO((player, opponent) -> {
        player.updateAttack(1.2, 3);
        return "I used moral affect, now I'm 20% stronger for 3 turns.";}),
    SUPER_VILLAIN((player, opponent) -> {
        opponent.updateDefence(-15.0, 3);
        return "I used slur affect, my opponent's defence is weaker for 3 turns.";}),
    NEUTRAL((player, opponent) -> {
        return "I'm "+player.getName()+"! I'm neutral.";});

    private BiFunction<Character, Character, String> specialAbility;

    private Side(BiFunction<Character, Character, String> specialAbility) {
        this.specialAbility = specialAbility;
    }

    public String useSpecial(Character player, Character opponent) {
        return specialAbility.apply(player, opponent);
    }
}
