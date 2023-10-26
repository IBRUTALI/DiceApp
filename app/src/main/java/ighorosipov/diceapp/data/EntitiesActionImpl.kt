package ighorosipov.diceapp.data

import ighorosipov.diceapp.domain.entities.Entity
import ighorosipov.diceapp.domain.entities.Monster
import ighorosipov.diceapp.domain.entities.Player
import ighorosipov.diceapp.domain.repository.EntitiesAction

class EntitiesActionImpl : EntitiesAction {

    override fun entityAttack(attacker: Entity, defender: Entity): Entity {
        val attackModifier = attacker.calculateAttackModifier(
            attacker.attackPower,
            defender.armor
        )
        val numbOnADice = attacker.rollDice(attackModifier)
        return when (defender) {
            is Player -> {
                defender.copy(
                    currentHealPoint = if (numbOnADice > 4)
                        attacker.calculateDamage(
                            attackModifier, defender.currentHealPoint
                        ) else defender.currentHealPoint,
                    lastNumberOnADice = numbOnADice
                )
            }

            is Monster -> {
                defender.copy(
                    currentHealPoint = if (numbOnADice > 4)
                        attacker.calculateDamage(
                            attackModifier, defender.currentHealPoint
                        ) else defender.currentHealPoint,
                    lastNumberOnADice = numbOnADice
                )
            }
            else -> {
                defender
            }
        }
    }

    override fun drinkHealPotion(player: Player): Player {
        return if(player.healPotion > 0) {
            player.copy(
                currentHealPoint = player.currentHealPoint + player.healPotionRestoredHP(),
                healPotion = player.healPotion - 1
            )
        } else player
    }


}