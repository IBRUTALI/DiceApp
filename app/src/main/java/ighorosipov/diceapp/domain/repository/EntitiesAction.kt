package ighorosipov.diceapp.domain.repository

import ighorosipov.diceapp.domain.entities.Entity
import ighorosipov.diceapp.domain.entities.Player

interface EntitiesAction {

    fun entityAttack(attacker: Entity, defender: Entity): Int

    fun drinkHealPotion(player: Player) : Player

}