package ighorosipov.diceapp.domain.entities

abstract class Entity(
    open val attackPower: Int,
    open val armor: Int,
    open val maxHealPoint: Int,
    open val currentHealPoint: Int = maxHealPoint,
    open val isDead: Boolean = false,
    open val damage: IntRange,
) {

    fun calculateAttackModifier(attackerPower: Int, defenderDefense: Int): Int {
        return attackerPower - defenderDefense - 1
    }

    fun rollDice(attackModifier: Int): Boolean {
        var isSuccess = false

        return if (attackModifier < 1) {
            isSuccess = when ((1..6).random()) {
                5, 6 -> true
                else -> false
            }
            isSuccess
        } else {
            for (i in 1..attackModifier) {
                if (isSuccess) return isSuccess
                isSuccess = when ((1..6).random()) {
                    5, 6 -> true
                    else -> false
                }
            }
            isSuccess
        }

    }

    fun calculateDamage(attackerDamage: Int, defenderHP: Int): Int {
        return defenderHP - attackerDamage
    }

    abstract fun isDead(): Boolean
}