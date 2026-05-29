package trainer;

import pokemon.Pokemon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Trainer {

    private final String name;
    private final ArrayList<Pokemon> team = new ArrayList<>();

    public Trainer(String name) {
        this.name = name;
    }

    public void addPokemon(Pokemon pokemon) {

        if (team.size() >= 6) {
            System.out.println("Team is full!");
            return;
        }

        if (team.contains(pokemon)) {
            System.out.println("Pokemon already in team!");
            return;
        }

        team.add(pokemon);
        System.out.println(pokemon.getName() + " added to team!");
    }

    public void removePokemon(int index) {

        if (index < 0 || index >= team.size()) {
            System.out.println("Invalid index.");
            return;
        }

        Pokemon removed = team.remove(index);
        System.out.println(removed.getName() + " removed from team!");
    }

    public void showTeam() {

        System.out.println("\n===== " + name + " TEAM =====");

        if (team.isEmpty()) {
            System.out.println("Empty team");
            return;
        }

        for (int i = 0; i < team.size(); i++) {

            Pokemon p = team.get(i);

            System.out.println(i + " - " + p.getName() + (p.isFainted() ? " (FAINTED)" : ""));
        }
    }

    public Pokemon getFirstAlivePokemon() {

        for (Pokemon p : team) {
            if (!p.isFainted()) {
                return p;
            }
        }

        return null;
    }

    public Pokemon choosePokemon(Scanner scanner) {

        showTeam();

        System.out.println("\nChoose a pokemon:");

        while (true) {

            System.out.print("> ");

            if (scanner.hasNextInt()) {

                int index = scanner.nextInt();

                if (index >= 0 && index < team.size()) {

                    Pokemon chosen = team.get(index);

                    if (chosen.isFainted()) {
                        System.out.println("This pokemon has fainted!");
                        continue;
                    }

                    return chosen;
                }
            } else {
                scanner.next();
            }

            System.out.println("Invalid option.");
        }
    }

    public List<Pokemon> getTeam() {
        return Collections.unmodifiableList(team);
    }

    public void clearTeam() {
        team.clear();
    }

    public int getTeamSize() {
        return team.size();
    }

    public String getName() {
        return name;
    }

    public boolean hasAlivePokemons() {
        return getFirstAlivePokemon() != null;
    }
}
