package battle;

import pokemon.Pokemon;
import trainer.Trainer;

import java.util.Random;
import java.util.Scanner;

public class BattleSystem {

    private final Scanner scanner = new Scanner(System.in);

    private final Random random = new Random();

    public void startBattle(Trainer playerTrainer, Trainer enemyTrainer) {

        Pokemon player = playerTrainer.getFirstAlivePokemon();

        Pokemon enemy = enemyTrainer.getFirstAlivePokemon();

        System.out.println("\n===== BATTLE START =====");

        while (player != null && enemy != null) {

            System.out.println("\n====================");

            System.out.println(player.getName() + " VS " + enemy.getName());

            System.out.println("====================");

            System.out.println(player.getName() + " HP: " + player.getHp());

            System.out.println(enemy.getName() + " HP: " + enemy.getHp());

            System.out.println("\n1 - Attack");

            System.out.println("2 - Switch Pokemon");

            int option = readOption(1, 2);

            if (option == 2) {

                Pokemon newPokemon = playerTrainer.choosePokemon(scanner);

                if (newPokemon != null && !newPokemon.isFainted()) {

                    player = newPokemon;

                    System.out.println("Go " + player.getName() + "!");
                }

                continue;
            }

            player.showMoves();

            int playerMove = readOption(0, 3);

            int enemyMove = random.nextInt(enemy.getMoves().size());

            executeTurn(player, enemy, playerMove, enemyMove);

            if (player.isFainted()) {

                System.out.println(player.getName() + " fainted!");

                player = playerTrainer.getFirstAlivePokemon();

                if (player != null) {

                    System.out.println("Go " + player.getName() + "!");
                }
            }

            if (enemy.isFainted()) {

                System.out.println(enemy.getName() + " fainted!");

                enemy = enemyTrainer.getFirstAlivePokemon();

                if (enemy != null) {

                    System.out.println("Enemy sent " + enemy.getName() + "!");
                }
            }
        }

        if (player == null) {

            System.out.println("\nYou lost the battle!");
        }
        else {

            System.out.println("\nYou won the battle!");
        }
    }

    public void executeTurn(Pokemon player, Pokemon enemy, int playerMove, int enemyMove) {

        if (playerAttacksFirst(player, enemy)) {

            player.useMove(playerMove, enemy);

            if (!enemy.isFainted()) {
                enemy.useMove(enemyMove, player);
            }

            return;
        }

        enemy.useMove(enemyMove, player);

        if (!player.isFainted()) {
            player.useMove(playerMove, enemy);
        }
    }

    public boolean playerAttacksFirst(Pokemon player, Pokemon enemy) {
        return player.getSpeed() >= enemy.getSpeed();
    }

    private int readOption(int min, int max) {

        int option;

        while (true) {

            System.out.print("> ");

            if (scanner.hasNextInt()) {

                option = scanner.nextInt();

                if (option >= min && option <= max) {

                    return option;
                }
            }
            else {

                scanner.next();
            }

            System.out.println("Invalid option.");
        }
    }
}
