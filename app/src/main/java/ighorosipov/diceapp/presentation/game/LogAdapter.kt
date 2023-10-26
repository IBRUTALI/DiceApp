package ighorosipov.diceapp.presentation.game

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ighorosipov.diceapp.databinding.ItemLogBinding

class LogAdapter: RecyclerView.Adapter<LogAdapter.LogViewHolder>() {

    private val logList = listOf<String>()

    class LogViewHolder(val binding: ItemLogBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        val binding = ItemLogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LogViewHolder(binding)
    }


    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        holder.binding.apply {
            logTextView.text = logList[position]
        }
    }

    override fun getItemCount(): Int {
        return logList.size
    }

    fun setData() {

    }
}