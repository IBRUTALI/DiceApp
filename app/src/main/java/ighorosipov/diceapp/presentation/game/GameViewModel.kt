package ighorosipov.diceapp.presentation.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ighorosipov.diceapp.data.EntitiesActionImpl
import ighorosipov.diceapp.domain.entities.Monster
import ighorosipov.diceapp.domain.entities.Player

class GameViewModel(): ViewModel() {
    private val repository = EntitiesActionImpl()

    private val _player = MutableLiveData<Player>()
    private val player: LiveData<Player> = _player

    private val _monster = MutableLiveData<Monster>()
    private val monster: LiveData<Monster> = _monster

    private val isPlayersMove = false

    init {
       _player.value = Player(
            attackPower = (1..30).random(),
            armor = (1..30).random(),
            maxHealPoint = (0..30).random(),
            damage = (1..30)
        )
        _monster.value = Monster(
            attackPower = (1..30).random(),
            armor = (1..30).random(),
            maxHealPoint = (0..30).random(),
            damage = (1..30)
        )
    }

    fun onPlayersMoved() {
        if (!player.value?.isDead!! && !monster.value?.isDead!! && isPlayersMove) {
            repository.entityAttack(player.value!!, monster.value!!)
            Thread.sleep(200)
        }
        if (!player.value?.isDead!! && !monster.value?.isDead!! && isPlayersMove) {
            repository.entityAttack(monster.value!!, player.value!!)
            Thread.sleep(200)
        }
    }

    fun drinkHealPotion() {
        if (!player.value?.isDead!! && !monster.value?.isDead!! && isPlayersMove) {
            _player.value = repository.drinkHealPotion(player.value!!)
            Thread.sleep(200)
        }
    }
}