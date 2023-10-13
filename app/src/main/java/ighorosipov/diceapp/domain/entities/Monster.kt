package ighorosipov.diceapp.domain.entities

data class Monster(
    override val attackPower: Int,
    override val armor: Int,
    override val maxHealPoint: Int,
    override val currentHealPoint: Int = maxHealPoint,
    override val isDead: Boolean = false,
    override val damage: IntRange
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

}
