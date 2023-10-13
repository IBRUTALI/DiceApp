package ighorosipov.diceapp.domain.entities

data class Player(
    override val attackPower: Int,
    override val armor: Int,
    override val maxHealPoint: Int,
    override val currentHealPoint: Int = maxHealPoint,
    override val isDead: Boolean = false,
    override val damage: IntRange,
    val healPotion: Int = 4
) : Entity(
    attackPower,
    armor,
    maxHealPoint,
    currentHealPoint,
    isDead,
    damage
) {

    override fun isDead(): Boolean {
        return currentHealPoint < 1
    }

    fun healPotionRestoredHP(healPotion: Int): Int {
        return if (healPotion > 0) {

            val restoredHP = (0.3 * maxHealPoint).toInt()

            if (restoredHP + currentHealPoint > maxHealPoint) {
                restoredHP + currentHealPoint - maxHealPoint
            } else restoredHP

        } else 0
    }

}