package ighorosipov.diceapp.domain.entities

data class Monster(
    val attackPower: Int,
    val armor: Int,
    val maxHealPoint: Int,
    val currentHealPoint: Int = maxHealPoint,
    val isDead: Boolean = false,
    val damage: IntRange
): Entity() {

    override fun isDead(): Boolean {
        return currentHealPoint < 1
    }

}
