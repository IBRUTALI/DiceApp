package ighorosipov.diceapp.presentation.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ighorosipov.diceapp.data.EntitiesActionImpl
import ighorosipov.diceapp.domain.entities.GameLog
import ighorosipov.diceapp.domain.entities.Monster
import ighorosipov.diceapp.domain.entities.Player

class GameViewModel : ViewModel() {
    private val repository = EntitiesActionImpl()

    private val _player = MutableLiveData<Player>()
    val player: LiveData<Player> = _player

    private val _monster = MutableLiveData<Monster>()
    val monster: LiveData<Monster> = _monster

    private val _log = MutableLiveData<List<GameLog>>()
    val log: LiveData<List<GameLog>> = _log

    init {
        _player.value = Player(
            name = "Dwarf",
            attackPower = (1..30).random(),
            armor = (1..30).random(),
            maxHealPoint = (0..30).random(),
            damage = (1..30)
        )
        _monster.value = Monster(
            name = "Orc",
            attackPower = (1..30).random(),
            armor = (1..30).random(),
            maxHealPoint = (1..30).random(),
            damage = (1..30)
        )
    }

    fun onPlayersMoved() {
        if (player.value?.entityIsAlive() == true && monster.value?.entityIsAlive() == true) {
            _monster.value = repository.entityAttack(player.value!!, monster.value!!) as Monster
            _log.value = repository.getLog()
            Thread.sleep(200)
        }
        if (player.value?.entityIsAlive() == true && monster.value?.entityIsAlive() == true) {
            _player.value = repository.entityAttack(monster.value!!, player.value!!) as Player
            _log.value = repository.getLog()
        }
    }

    fun drinkHealPotion() {
        if (player.value?.entityIsAlive() == true) {
            _player.value = repository.drinkHealPotion(player.value!!)
            _log.value = repository.getLog()
        }
    }
}