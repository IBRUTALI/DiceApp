package ighorosipov.diceapp.presentation.game

import androidx.recyclerview.widget.DiffUtil
import ighorosipov.diceapp.domain.model.GameLog

class LogDifUtil(
    private val oldList: List<GameLog>,
    private val newList: List<GameLog>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[oldItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return false
    }

}