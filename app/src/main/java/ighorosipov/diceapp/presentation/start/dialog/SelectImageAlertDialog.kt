package ighorosipov.diceapp.presentation.start.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import ighorosipov.diceapp.R
import ighorosipov.diceapp.databinding.DialogSelectImageBinding

class SelectImageAlertDialog(
    private val onImageClick: (Int) -> Unit
): DialogFragment() {
    private val adapter by lazy { SelectImageAdapter() }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogSelectImageBinding.inflate(layoutInflater)
        binding.apply {
            itemClickListener()
            btnCancel.setOnClickListener {
                dismiss()
            }
            imageRecyclerView.adapter = adapter
            imageRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        }

        val builder = AlertDialog.Builder(requireContext())
            .setView(binding.root)

        val alertDialog = builder.create()
        val drawable: Drawable? = AppCompatResources.getDrawable(requireContext(), R.drawable.shape_radius_54)
        alertDialog.window?.setBackgroundDrawable(drawable)

        return alertDialog
    }

    private fun itemClickListener() {
        adapter.setOnClickListener(object : SelectImageAdapter.OnClickListener {
            override fun onClick(position: Int, image: Int) {
                onImageClick.invoke(image)
                dismiss()
            }
        })
    }

}