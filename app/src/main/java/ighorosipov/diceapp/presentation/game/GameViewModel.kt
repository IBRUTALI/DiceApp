package ighorosipov.diceapp.presentation.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ighorosipov.diceapp.domain.model.GameLog
import ighorosipov.diceapp.domain.model.entities.Monster
import ighorosipov.diceapp.domain.model.entities.Player
import ighorosipov.diceapp.domain.repository.EntitiesAction
import ighorosipov.diceapp.utils.GameState

class GameViewModel @AssistedInject constructor(
    @Assisted private val playerName: String?,
    private val repository: EntitiesAction
) : ViewModel() {

    private val _player = MutableLiveData<Player>()
    val player: LiveData<Player> = _player

    private val _monster = MutableLiveData<Monster>()
    val monster: LiveData<Monster> = _monster

    private val _log = MutableLiveData<List<GameLog>>()
    val log: LiveData<List<GameLog>> = _log

    private val _gameState = MutableLiveData<GameState>()
    val gameState: LiveData<GameState> = _gameState

    init {
        _player.value = Player(
            name = playerName ?: "Player",
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
        _monster.value = repository.entityAttack(player.value!!, monster.value!!) as Monster
        _log.value = repository.getLog()
        if (monster.value?.entityIsAlive() == false) {
            _gameState.value = GameState.WIN
            return
        }
        Thread.sleep(200)
        _player.value = repository.entityAttack(monster.value!!, player.value!!) as Player
        _log.value = repository.getLog()
        if (player.value?.entityIsAlive() == false) {
            _gameState.value = GameState.LOSE
            return
        }
    }

    fun drinkHealPotion() {
        if (player.value?.entityIsAlive() == true) {
            _player.value = repository.drinkHealPotion(player.value!!)
            _log.value = repository.getLog()
        }
    }

    fun restartGame() {
        _player.value = Player(
            name = player.value?.name ?: "Player",
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
        _gameState.value = GameState.STARTED
        _log.value = emptyList()
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted playerName: String?): GameViewModel
    }

}