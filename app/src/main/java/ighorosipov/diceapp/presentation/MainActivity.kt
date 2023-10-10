package ighorosipov.diceapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ighorosipov.diceapp.databinding.ActivityMainBinding
import ighorosipov.diceapp.domain.entities.Monster
import ighorosipov.diceapp.domain.entities.Player

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private var binding = _binding!!
    private var isPlayersMove = true
    private lateinit var player: Player
    private lateinit var monster: Monster

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.onPlayerCreate {
            createPlayer(

            )
        }
        binding.onMonsterCreate {
            createMonster(

            )
        }
        binding.onStartClick {

        }
        binding.onAttackClick {
            onPlayersMoved { playerAttack() }
        }

        binding.onHealDrink {
            onPlayersMoved { drinkHealPotion() }
        }
    }

    private fun onPlayersMoved(playerMove: () -> Unit) {
        if (!player.isDead && !monster.isDead && isPlayersMove) {
            playerMove.invoke()
            Thread.sleep(200)
        }
        if(!player.isDead && !monster.isDead && !isPlayersMove) {
            monsterAttack()
            Thread.sleep(200)
        }
    }

    private fun playerAttack() {
            val attackModifier = player.calculateAttackModifier(
                player.attackPower,
                monster.armor
            )
            if (player.rollDice(attackModifier)) {
                monster = monster.copy(
                    currentHealPoint = player.calculateDamage(attackModifier, monster.currentHealPoint)
                )
            }
            else {
                TODO()
            }
            isPlayersMove = false
    }

    private fun monsterAttack() {
        val attackModifier = monster.calculateAttackModifier(
            monster.attackPower,
            player.armor
        )
        if (monster.rollDice(attackModifier)) {
            player = player.copy(
                currentHealPoint = monster.calculateDamage(attackModifier, player.currentHealPoint)
            )
        }
        else {
            TODO()
        }
        isPlayersMove = true
    }

    private fun drinkHealPotion() {
        player = player.copy(
            currentHealPoint = player.currentHealPoint + player.healPotionRestoredHP(player.healPotion),
            healPotion = player.healPotion - 1
        )
    }

    private fun createPlayer(attackPower: Int, armor: Int, maxHealPoint: Int, damage: IntRange) {
        player = Player(
            attackPower = attackPower,
            armor = armor,
            maxHealPoint = maxHealPoint,
            damage = damage
        )
    }

    private fun createMonster(attackPower: Int, armor: Int, maxHealPoint: Int, damage: IntRange) {
        monster = Monster(
            attackPower = attackPower,
            armor = armor,
            maxHealPoint = maxHealPoint,
            damage = damage
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}