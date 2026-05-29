package pokemon;

import moves.Move;
import moves.MoveType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokemonTest {

    @Test
    void typeEffectivenessChangesCalculatedDamage() {
        Pokemon charmander = PokemonFactory.createPokemon(4);
        Move fireMove = charmander.getMoves().get(1);

        int effectiveDamage = charmander.calculateDamage(fireMove, PokemonFactory.createPokemon(1));
        int neutralDamage = charmander.calculateDamage(fireMove, PokemonFactory.createPokemon(19));
        int resistedDamage = charmander.calculateDamage(fireMove, PokemonFactory.createPokemon(7));

        assertTrue(effectiveDamage > neutralDamage);
        assertTrue(resistedDamage < neutralDamage);
    }

    @Test
    void attackNeverIncreasesHpAndHpDoesNotGoBelowZero() {
        Pokemon attacker = new DefaultPokemon(1, "Attacker", MoveType.NORMAL, null, 600, 100, 500, 10, 500, 10, 100);
        Pokemon defender = new DefaultPokemon(2, "Defender", MoveType.NORMAL, null, 200, 20, 10, 0, 10, 0, 10);

        attacker.useMove(1, defender);

        assertEquals(0, defender.getHp());
        assertTrue(defender.isFainted());
    }

    @Test
    void defenseMoveDoesNotDamageTarget() {
        Pokemon attacker = PokemonFactory.createPokemon(25);
        Pokemon defender = PokemonFactory.createPokemon(7);
        int initialHp = defender.getHp();

        attacker.useMove(0, defender);

        assertEquals(initialHp, defender.getHp());
    }

    @Test
    void attackOnlyChangesHpOfTarget() {
        Pokemon attacker = PokemonFactory.createPokemon(25);
        Pokemon defender = PokemonFactory.createPokemon(7);

        int id = defender.getId();
        String name = defender.getName();
        MoveType type1 = defender.getType1();
        MoveType type2 = defender.getType2();
        int attack = defender.getAttack();
        int defense = defender.getDefense();
        int specialAttack = defender.getSpAttack();
        int specialDefense = defender.getSpDefense();
        int speed = defender.getSpeed();

        attacker.useMove(1, defender);

        assertAll(
                () -> assertEquals(id, defender.getId()),
                () -> assertEquals(name, defender.getName()),
                () -> assertEquals(type1, defender.getType1()),
                () -> assertEquals(type2, defender.getType2()),
                () -> assertEquals(attack, defender.getAttack()),
                () -> assertEquals(defense, defender.getDefense()),
                () -> assertEquals(specialAttack, defender.getSpAttack()),
                () -> assertEquals(specialDefense, defender.getSpDefense()),
                () -> assertEquals(speed, defender.getSpeed()),
                () -> assertTrue(defender.getHp() < 44)
        );
    }

    @Test
    void invalidMoveIndexIsRejected() {
        Pokemon attacker = PokemonFactory.createPokemon(25);
        Pokemon defender = PokemonFactory.createPokemon(7);

        assertThrows(IllegalArgumentException.class, () -> attacker.useMove(99, defender));
    }
}
