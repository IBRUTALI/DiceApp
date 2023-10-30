package ighorosipov.diceapp.presentation.game

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ighorosipov.diceapp.databinding.ItemLogBinding
import ighorosipov.diceapp.domain.entities.GameLog

class LogAdapter: RecyclerView.Adapter<LogAdapter.LogViewHolder>() {

    private var logList = listOf<GameLog>()

    class LogViewHolder(val binding: ItemLogBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        val binding = ItemLogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LogViewHolder(binding)
    }


    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        holder.binding.apply {
            logTextView.text = logList[position].message
        }
    }

    override fun getItemCount(): Int {
        return logList.size
    }

    fun setData(newList: List<GameLog>) {
        val diffUtil = LogDifUtil(logList, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        diffResult.dispatchUpdatesTo(this)
        logList = newList
    }
}