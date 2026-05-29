package moves;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeMoves {

    private static final Map<
            MoveType,
            List<Move>
            > typeMoves = new HashMap<>();

    static {

        addMoves(
                MoveType.NORMAL,
                "Tackle",
                "Quick Attack",
                "Hyper Beam"
        );

        addMoves(MoveType.FIRE,
                "Fire Punch",
                "Flamethrower",
                "Inferno Blast"
        );

        addMoves(
                MoveType.WATER,
                "Water Gun",
                "Bubble Beam",
                "Hydro Pump"
        );

        addMoves(
                MoveType.ELECTRIC,
                "Thunder Shock",
                "Spark",
                "Thunder"
        );

        addMoves(
                MoveType.GRASS,
                "Vine Whip",
                "Razor Leaf",
                "Solar Beam"
        );

        addMoves(
                MoveType.ICE,
                "Ice Shard",
                "Ice Beam",
                "Blizzard"
        );

        addMoves(
                MoveType.FIGHTING,
                "Karate Chop",
                "Brick Break",
                "Close Combat"
        );

        addMoves(
                MoveType.POISON,
                "Poison Sting",
                "Sludge",
                "Gunk Shot"
        );

        addMoves(
                MoveType.GROUND,
                "Mud Slap",
                "Dig",
                "Earthquake"
        );

        addMoves(
                MoveType.FLYING,
                "Peck",
                "Wing Attack",
                "Hurricane"
        );

        addMoves(
                MoveType.PSYCHIC,
                "Confusion",
                "Psybeam",
                "Psychic"
        );

        addMoves(
                MoveType.BUG,
                "Bug Bite",
                "Signal Beam",
                "Megahorn"
        );

        addMoves(
                MoveType.ROCK,
                "Rock Throw",
                "Rock Slide",
                "Stone Edge"
        );

        addMoves(
                MoveType.GHOST,
                "Lick",
                "Shadow Punch",
                "Shadow Ball"
        );

        addMoves(
                MoveType.DRAGON,
                "Dragon Breath",
                "Dragon Claw",
                "Draco Meteor"
        );

        addMoves(
                MoveType.STEEL,
                "Metal Claw",
                "Iron Head",
                "Flash Cannon"
        );

        addMoves(
                MoveType.FAIRY,
                "Fairy Wind",
                "Draining Kiss",
                "Moonblast"
        );
    }

    private static void addMoves(
            MoveType type,
            String physicalMove,
            String specialMove,
            String strongMove
    ) {

        ArrayList<Move> moves =
                new ArrayList<>();

        moves.add(
                new Move(
                        physicalMove,
                        type,
                        MoveCategory.PHYSICAL,
                        50
                )
        );

        moves.add(
                new Move(
                        specialMove,
                        type,
                        MoveCategory.SPECIAL,
                        50
                )
        );

        moves.add(
                new Move(
                        strongMove,
                        type,
                        MoveCategory.STRONG,
                        75
                )
        );

        typeMoves.put(type, Collections.unmodifiableList(moves));
    }

    public static List<Move> getMoves(MoveType type) {
        return typeMoves.get(type);
    }
}
