package ighorosipov.diceapp.domain.entities

data class Monster(
    override val attackPower: Int,
    override val armor: Int,
    override val maxHealPoint: Int,
    override val currentHealPoint: Int = maxHealPoint,
    override val entityIsDead: Boolean = false,
    override val damage: IntRange,
    override val lastNumberOnADice: Int? = null
) : Entity(
    attackPower,
    armor,
    maxHealPoint,
    currentHealPoint,
    entityIsDead,
    damage,
    lastNumberOnADice
) {
    override fun entityIsDead(): Boolean {
        return currentHealPoint < 1
    }

}
