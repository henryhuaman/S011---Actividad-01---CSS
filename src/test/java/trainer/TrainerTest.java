package trainer;

import moves.MoveType;
import org.junit.jupiter.api.Test;
import pokemon.DefaultPokemon;
import pokemon.Pokemon;
import pokemon.PokemonFactory;

import static org.junit.jupiter.api.Assertions.*;

class TrainerTest {

    @Test
    void teamCannotExceedSixPokemon() {
        Trainer trainer = new Trainer("Ash");

        for (int i = 1; i <= 7; i++) {
            trainer.addPokemon(PokemonFactory.createPokemon(i));
        }

        assertEquals(6, trainer.getTeamSize());
    }

    @Test
    void firstAlivePokemonSkipsFaintedPokemon() {
        Trainer trainer = new Trainer("Ash");
        Pokemon fainted = new DefaultPokemon(1, "Fainted", MoveType.NORMAL, null, 100, 1, 10, 0, 10, 0, 10);
        Pokemon alive = PokemonFactory.createPokemon(25);

        fainted.receiveDamage(10);
        trainer.addPokemon(fainted);
        trainer.addPokemon(alive);

        assertSame(alive, trainer.getFirstAlivePokemon());
        assertTrue(trainer.hasAlivePokemons());
    }

    @Test
    void exposedTeamListCannotBeModifiedDirectly() {
        Trainer trainer = new Trainer("Ash");
        trainer.addPokemon(PokemonFactory.createPokemon(25));

        assertThrows(UnsupportedOperationException.class, () -> trainer.getTeam().clear());
    }

    /*@Test
    void clearTeamRemovesAllPokemon() {
        Trainer trainer = new Trainer("Ash");
        trainer.addPokemon(PokemonFactory.createPokemon(25));

        trainer.clearTeam();

        assertEquals(0, trainer.getTeamSize());
        assertFalse(trainer.hasAlivePokemons());
    }*/
}
