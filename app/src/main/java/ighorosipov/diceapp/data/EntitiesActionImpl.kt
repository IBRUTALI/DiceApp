package ighorosipov.diceapp.data

import ighorosipov.diceapp.domain.entities.Entity
import ighorosipov.diceapp.domain.entities.Player
import ighorosipov.diceapp.domain.repository.EntitiesAction

class EntitiesActionImpl: EntitiesAction {

    override fun entityAttack(attacker: Entity, defender: Entity): Int {
        val attackModifier = attacker.calculateAttackModifier(
            attacker.attackPower,
            defender.armor
        )
       return if(attacker.rollDice(attackModifier)) {
           attacker.calculateDamage(attackModifier, defender.currentHealPoint)
       } else 0
    }

    override fun drinkHealPotion(player: Player): Player {
       return player.copy(
            currentHealPoint = player.currentHealPoint + player.healPotionRestoredHP(player.healPotion),
            healPotion = player.healPotion - 1
       )
    }

}