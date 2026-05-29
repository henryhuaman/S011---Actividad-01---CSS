package pokemon;

import moves.MoveType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokemonFactoryTest {

    @Test
    void createsPokemonByExistingId() {
        Pokemon mew = PokemonFactory.createPokemon(151);

        assertEquals("Mew", mew.getName());
        assertEquals(MoveType.PSYCHIC, mew.getType1());
        assertNull(mew.getType2());
    }

    @Test
    void rejectsNonExistingId() {
        assertThrows(IllegalArgumentException.class, () -> PokemonFactory.createPokemon(0));
    }
}
