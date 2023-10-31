package ighorosipov.diceapp.presentation.game

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ighorosipov.diceapp.databinding.ItemLogBinding
import ighorosipov.diceapp.domain.entities.GameLog
import ighorosipov.diceapp.utils.Entities

class LogAdapter: RecyclerView.Adapter<LogAdapter.LogViewHolder>() {

    private var logList = listOf<GameLog>()

    class LogViewHolder(val binding: ItemLogBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        val binding = ItemLogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LogViewHolder(binding)
    }


    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        holder.binding.apply {
            val text = logList[position].message
            val color = if(logList[position].tag == Entities.PLAYER) {
                Color.BLUE
            } else Color.RED
            val spannable = SpannableString(text)
            spannable.setSpan(ForegroundColorSpan(color), 0, (text).length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            logTextView.setText(spannable, TextView.BufferType.SPANNABLE)
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