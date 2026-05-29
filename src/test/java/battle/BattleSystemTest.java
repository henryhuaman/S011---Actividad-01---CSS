package battle;

import moves.Move;
import moves.MoveType;
import org.junit.jupiter.api.Test;
import pokemon.DefaultPokemon;
import pokemon.Pokemon;

import static org.junit.jupiter.api.Assertions.*;

class BattleSystemTest {

    private final BattleSystem battleSystem = new BattleSystem();

    @Test
    void fasterPokemonAttacksFirst() {
        Pokemon fast = pokemon("Fast", 100, 80);
        Pokemon slow = pokemon("Slow", 50, 40);

        assertTrue(battleSystem.playerAttacksFirst(fast, slow));
        assertFalse(battleSystem.playerAttacksFirst(slow, fast));
    }

    @Test
    void sameSpeedKeepsDeterministicPlayerPriority() {
        Pokemon player = pokemon("Player", 70, 50);
        Pokemon enemy = pokemon("Enemy", 70, 50);

        assertTrue(battleSystem.playerAttacksFirst(player, enemy));
    }

    @Test
    void defeatedPokemonDoesNotCounterAttack() {
        Pokemon player = new DefaultPokemon(1, "Player", MoveType.NORMAL, null, 600, 200, 150, 40, 150, 40, 100);
        Pokemon enemy = new DefaultPokemon(2, "Enemy", MoveType.NORMAL, null, 600, 10, 200, 10, 200, 10, 10);

        int initialPlayerHp = player.getHp();

        battleSystem.executeTurn(player, enemy, 1, 1);

        assertTrue(enemy.isFainted());
        assertEquals(initialPlayerHp, player.getHp());
    }

    @Test
    void damageIsAppliedOnlyOnceInATurn() {
        Pokemon player = new DefaultPokemon(1, "Player", MoveType.NORMAL, null, 600, 300, 100, 20, 40, 20, 100);
        Pokemon enemy = new DefaultPokemon(2, "Enemy", MoveType.NORMAL, null, 600, 300, 40, 10, 40, 10, 10);
        Move move = player.getMoves().get(1);

        int expectedDamage = Math.max(0, player.calculateDamage(move, enemy) - enemy.getDefense());

        battleSystem.executeTurn(player, enemy, 1, 0);

        assertEquals(300 - expectedDamage, enemy.getHp());
    }

    private Pokemon pokemon(String name, int speed, int attack) {
        return new DefaultPokemon(1, name, MoveType.NORMAL, null, 400, 100, attack, 50, 40, 50, speed);
    }
}
