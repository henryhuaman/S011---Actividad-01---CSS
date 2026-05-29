package pokemon;

import moves.MoveType;

public class PokemonFactory {

    public static Pokemon createPokemon(int pokemonId) {

        for (String data : PokemonDatabase.POKEMONS) {

            String[] values = data.split(";");

            int id = Integer.parseInt(values[0]);

            if (id == pokemonId) {

                MoveType type1 = MoveType.valueOf(values[2].toUpperCase());

                MoveType type2 = null;

                if (!values[3].equalsIgnoreCase("null")) {

                    type2 = MoveType.valueOf(values[3].toUpperCase());
                }

                return new DefaultPokemon(

                        id,
                        values[1],
                        type1,
                        type2,
                        Integer.parseInt(values[4]),
                        Integer.parseInt(values[5]),
                        Integer.parseInt(values[6]),
                        Integer.parseInt(values[7]),
                        Integer.parseInt(values[8]),
                        Integer.parseInt(values[9]),
                        Integer.parseInt(values[10])
                );
            }
        }

        throw new IllegalArgumentException("Pokemon ID not found: " + pokemonId);
    }
}