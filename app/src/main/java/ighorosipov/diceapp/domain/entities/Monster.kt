package ighorosipov.diceapp.domain.entities

data class Monster(
    override val id: String? = null,
    override val name: String,
    override val attackPower: Int,
    override val armor: Int,
    override val maxHealPoint: Int,
    override val currentHealPoint: Int = maxHealPoint,
    override val damage: IntRange
) : Entity(
    id,
    name,
    attackPower,
    armor,
    maxHealPoint,
    currentHealPoint,
    damage
) {
    override fun entityIsAlive(): Boolean {
        return currentHealPoint > 0
    }

}
