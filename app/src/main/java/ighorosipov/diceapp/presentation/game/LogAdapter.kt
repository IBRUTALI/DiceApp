package ighorosipov.diceapp.presentation.game

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class LogAdapter: RecyclerView.Adapter<LogAdapter.LogViewHolder>() {

    private val logList = listOf<String>()

    class LogViewHolder(view: View): ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        TODO("Not yet implemented")
    }


    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


    override fun getItemCount(): Int {
        return logList.size
    }
}