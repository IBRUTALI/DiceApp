package ighorosipov.diceapp.data

import ighorosipov.diceapp.domain.model.entities.Entity
import ighorosipov.diceapp.domain.model.GameLog
import ighorosipov.diceapp.domain.model.entities.Monster
import ighorosipov.diceapp.domain.model.entities.Player
import ighorosipov.diceapp.domain.repository.EntitiesAction
import ighorosipov.diceapp.utils.Entities
import java.util.UUID
import javax.inject.Inject

class EntitiesActionImpl @Inject constructor() : EntitiesAction {
    private var log = mutableListOf<GameLog>()
    private var numbOnADice = 0

    override fun entityAttack(attacker: Entity, defender: Entity): Entity {
        val attackModifier = attacker.calculateAttackModifier(
            attacker.attackPower,
            defender.armor
        )
        numbOnADice = attacker.rollDice(attackModifier)
        val damage = if(numbOnADice > 4) " and deals $attackModifier damage" else ""
        log.add(
            GameLog(
            id = UUID.randomUUID().toString(),
            tag = if (attacker is Player) Entities.PLAYER else Entities.MONSTER,
            message = "${attacker.name} rolls a $numbOnADice" + damage
        )
        )
        return when (defender) {
            is Player -> {
                defender.copy(
                    currentHealPoint = if (numbOnADice > 4)
                        attacker.calculateDamage(
                            attackModifier, defender.currentHealPoint
                        ) else defender.currentHealPoint
                )
            }

            is Monster -> {
                defender.copy(
                    currentHealPoint = if (numbOnADice > 4)
                        attacker.calculateDamage(
                            attackModifier, defender.currentHealPoint
                        ) else defender.currentHealPoint
                )
            }
            else -> {
                defender
            }
        }
    }

    override fun drinkHealPotion(player: Player): Player {
        return if(player.healPotion > 0) {
            val restoredHP = player.healPotionRestoredHP()
            log.add(
                GameLog(
                id = UUID.randomUUID().toString(),
                tag = Entities.PLAYER,
                message = "${player.name} restore $restoredHP HP"
            )
            )
            player.copy(
                currentHealPoint = player.currentHealPoint + restoredHP,
                healPotion = player.healPotion - 1
            )
        } else player
    }

    override fun getLog() = log

    fun getNumbOnADice() = numbOnADice

}