package ighorosipov.diceapp.domain.model.entities

data class Player(
    override val id: String? = null,
    override val name: String,
    override val image: String? = null,
    override val attackPower: Int,
    override val armor: Int,
    override val maxHealPoint: Int,
    override val currentHealPoint: Int = maxHealPoint,
    override val damage: IntRange,
    val healPotion: Int = 4
) : Entity(
    id,
    name,
    image,
    attackPower,
    armor,
    maxHealPoint,
    currentHealPoint,
    damage
) {

    fun healPotionRestoredHP(): Int {
            val restoredHP = (0.3 * maxHealPoint).toInt()
            if (currentHealPoint == maxHealPoint) return 0
            if (restoredHP + currentHealPoint > maxHealPoint) {
                return maxHealPoint - currentHealPoint
            }
        return restoredHP
    }

    override fun entityIsAlive(): Boolean {
        return currentHealPoint > 0
    }

}
