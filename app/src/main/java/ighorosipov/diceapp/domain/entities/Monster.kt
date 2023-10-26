package ighorosipov.diceapp.domain.entities

data class Monster(
    override val attackPower: Int,
    override val armor: Int,
    override val maxHealPoint: Int,
    override val currentHealPoint: Int = maxHealPoint,
    override val damage: IntRange,
    override val lastNumberOnADice: Int? = null
) : Entity(
    attackPower,
    armor,
    maxHealPoint,
    currentHealPoint,
    damage,
    lastNumberOnADice
) {
    override fun entityIsAlive(): Boolean {
        return currentHealPoint > 0
    }

}
