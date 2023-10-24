package ighorosipov.diceapp.domain.entities

data class Monster(
    override val attackPower: Int,
    override val armor: Int,
    override val maxHealPoint: Int,
    override val currentHealPoint: Int = maxHealPoint,
    override val entityIsDead: Boolean = false,
    override val damage: IntRange
) : Entity(
    attackPower,
    armor,
    maxHealPoint,
    currentHealPoint,
    entityIsDead,
    damage
) {

    fun entityIsDead(): Boolean {
        return currentHealPoint < 1
    }

    override fun isDead(): Boolean {
        TODO("Not yet implemented")
    }

}
