package ighorosipov.diceapp.presentation.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ighorosipov.diceapp.data.EntitiesActionImpl
import ighorosipov.diceapp.domain.entities.Monster
import ighorosipov.diceapp.domain.entities.Player

class GameViewModel: ViewModel() {
    private val repository = EntitiesActionImpl()

    private val _player = MutableLiveData<Player>()
    val player: LiveData<Player> = _player

    private val _monster = MutableLiveData<Monster>()
    val monster: LiveData<Monster> = _monster

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
            maxHealPoint = (1..30).random(),
            damage = (1..30)
        )
    }

    fun onPlayersMoved() {
        if (!player.value?.entityIsDead!! && !monster.value?.entityIsDead!!) {
            _monster.value = repository.entityAttack(player.value!!, monster.value!!) as Monster
            Thread.sleep(200)
        }
        if (!player.value?.entityIsDead!! && !monster.value?.entityIsDead!!) {
            _player.value = repository.entityAttack(monster.value!!, player.value!!) as Player
        }
    }

    fun drinkHealPotion() {
        if (!player.value?.entityIsDead!! && !monster.value?.entityIsDead!!) {
            _player.value = repository.drinkHealPotion(player.value!!)
        }
    }
}