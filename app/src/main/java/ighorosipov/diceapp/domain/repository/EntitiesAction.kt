package ighorosipov.diceapp.domain.repository

import ighorosipov.diceapp.domain.entities.Entity
import ighorosipov.diceapp.domain.entities.GameLog
import ighorosipov.diceapp.domain.entities.Player

interface EntitiesAction {

    fun entityAttack(attacker: Entity, defender: Entity): Entity

    fun drinkHealPotion(player: Player) : Player

    fun getLog(): List<GameLog>

}