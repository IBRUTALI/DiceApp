package ighorosipov.diceapp.domain.entities

data class Player(
    override val attackPower: Int,
    override val armor: Int,
    override val maxHealPoint: Int,
    override val currentHealPoint: Int = maxHealPoint,
    override val damage: IntRange,
    override val lastNumberOnADice: Int? = null,
    val healPotion: Int = 4
) : Entity(
    attackPower,
    armor,
    maxHealPoint,
    currentHealPoint,
    damage,
    lastNumberOnADice
) {

    fun healPotionRestoredHP(): Int {
            val restoredHP = (0.3 * maxHealPoint).toInt()
            if (currentHealPoint == maxHealPoint) return 0
            if (restoredHP + currentHealPoint > maxHealPoint) {
                return restoredHP + currentHealPoint - maxHealPoint
            }
        return restoredHP
    }

    override fun entityIsAlive(): Boolean {
        return currentHealPoint > 0
    }

}
