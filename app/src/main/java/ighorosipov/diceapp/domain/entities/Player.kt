package ighorosipov.diceapp.domain.entities

data class Player(
    val attackPower: Int,
    val armor: Int,
    val maxHealPoint: Int,
    val currentHealPoint: Int = maxHealPoint,
    val isDead: Boolean = false,
    val damage: IntRange,
    val healPotion: Int = 4
) : Entity() {

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
