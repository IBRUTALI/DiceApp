package ighorosipov.diceapp.presentation.start.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import ighorosipov.diceapp.R
import ighorosipov.diceapp.databinding.ItemSelectImageBinding

class SelectImageAdapter: RecyclerView.Adapter<SelectImageAdapter.SelectImageViewHolder>() {
    private var selectedPos = RecyclerView.NO_POSITION
    private var onClickListener: OnClickListener? = null
    private val imageList = listOf(
        R.drawable.archer,
        R.drawable.bard,
        R.drawable.dwarf,
        R.drawable.paladin,
        R.drawable.death_knight,
        R.drawable.rogue,
        R.drawable.warrior,
        R.drawable.warrior_orc,
        R.drawable.warrior_spartan
    )

    class SelectImageViewHolder(val binding: ItemSelectImageBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectImageViewHolder {
        val binding = ItemSelectImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SelectImageViewHolder(binding)
    }


    override fun onBindViewHolder(holder: SelectImageViewHolder, position: Int) {
        holder.binding.apply {
            Glide.with(holder.itemView.context)
                .load(imageList[position])
                .centerCrop()
                .into(itemImage)
        }
        holder.itemView.isSelected = selectedPos == position
        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener?.onClick(position, imageList[position])
                notifyItemChanged(position)
                selectedPos = holder.layoutPosition
            }
        }
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, image: Int)
    }

    override fun getItemCount() = imageList.size

}