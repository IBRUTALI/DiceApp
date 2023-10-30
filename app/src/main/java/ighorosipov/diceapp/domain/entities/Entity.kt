package ighorosipov.diceapp.domain.entities

abstract class Entity(
    open val id: String? = null,
    open val name: String,
    open val attackPower: Int,
    open val armor: Int,
    open val maxHealPoint: Int,
    open val currentHealPoint: Int = maxHealPoint,
    open val damage: IntRange
) {

    fun calculateAttackModifier(attackerPower: Int, defenderDefense: Int): Int {
        val attackModifier = attackerPower - defenderDefense - 1
        return if (attackModifier > 0) attackModifier
        else 1
    }

    fun rollDice(attackModifier: Int): Int {
        var dice = (1..6).random()
        return if (attackModifier < 1) {
            dice
        } else {
            for (i in 1..attackModifier) {
                dice = (1..6).random()
                if (dice > 4) return dice
            }
            dice
        }
    }

    fun calculateDamage(attackerDamage: Int, defenderHP: Int): Int {
        return defenderHP - attackerDamage
    }

    abstract fun entityIsAlive(): Boolean
}