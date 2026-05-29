# Quality analysis - Pokemon Battle System

## Scope

The tested scope is the business logic of the battle system:

- turn order
- damage and HP state
- type effectiveness
- defeat condition
- invariants of Pokemon attributes during battle

Console classes are treated as delivery/UI code. `app/**` and `battle/BattleMenu*`
are excluded from JaCoCo because they depend on interactive `Scanner` input and do
not contain the core business rules.

## Refactoring decisions

- `BattleSystem.playerAttacksFirst` makes turn-order logic directly testable.
- Speed ties are deterministic: the player attacks first. This follows the
  activity requirement that equal speed must keep a consistent order.
- `BattleSystem.executeTurn` makes one turn testable without running the full
  console loop.
- `Pokemon.calculateDamage` exposes the damage rule without mutating HP.
- `Trainer.getTeam` now returns an unmodifiable list. Team mutation is done
  through explicit methods such as `addPokemon` and `clearTeam`.
- Pokemon ID selection in `BattleMenu` was corrected to use IDs `1..151`, which
  matches `PokemonDatabase`.
- Random enemy selection was corrected so it cannot generate ID `0`.
- `Move` is immutable because battle rules should not accidentally alter move
  data during a test or fight.

## Mandatory business rules covered

- Rule 1: faster Pokemon attacks first; equal speed has deterministic order.
- Rule 2: attacks reduce HP, HP does not become negative, and damage is applied
  once per tested turn.
- Rule 3: effective, resisted and neutral type interactions produce different
  damage values.
- Rule 4: a fainted Pokemon does not counterattack.
- Rule 5: attacks only change HP; identity, types and stats remain constant.

## Coverage gate

JaCoCo is configured in `pom.xml` with a minimum line coverage of `85%`.
The GitHub Actions workflow runs `mvn -B verify` on Pull Requests. The `verify`
phase executes the JaCoCo check, so the Pull Request fails when coverage is
below the configured threshold.

Coverage is useful as a gate, but it is not sufficient by itself. A line can be
covered without verifying the expected business behavior. That is why the tests
assert domain outcomes instead of only executing methods.

If a Pull Request reports coverage below `85%`, the failed workflow is expected
evidence that the quality gate is active and preventing low-coverage changes
from being merged into `main`.
