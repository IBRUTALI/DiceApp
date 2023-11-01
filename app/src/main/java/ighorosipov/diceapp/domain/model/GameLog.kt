package ighorosipov.diceapp.domain.model

import ighorosipov.diceapp.utils.Entities

data class GameLog(
    val id: String,
    val tag: Entities,
    val message: String
)