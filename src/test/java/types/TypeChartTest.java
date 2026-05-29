package types;

import moves.MoveType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TypeChartTest {

    @Test
    void returnsConfiguredMultipliers() {
        assertEquals(2.0, TypeChart.getMultiplier(MoveType.FIRE, MoveType.GRASS));
        assertEquals(0.5, TypeChart.getMultiplier(MoveType.FIRE, MoveType.WATER));
        assertEquals(0.0, TypeChart.getMultiplier(MoveType.ELECTRIC, MoveType.GROUND));
    }

    @Test
    void returnsNeutralMultiplierWhenCombinationIsNotConfigured() {
        assertEquals(1.0, TypeChart.getMultiplier(MoveType.NORMAL, MoveType.FIRE));
    }
}
