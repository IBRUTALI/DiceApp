package ighorosipov.diceapp.presentation.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import ighorosipov.diceapp.R
import ighorosipov.diceapp.databinding.FragmentCreateBinding
import ighorosipov.diceapp.presentation.start.dialog.SelectImageAlertDialog
import ighorosipov.diceapp.utils.extensions.appComponent
import ighorosipov.diceapp.utils.extensions.lazyViewModel

class CreateFragment : Fragment() {
    private var _binding: FragmentCreateBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CreateViewModel by lazyViewModel {
        requireContext().appComponent().createViewModel().create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe()
        binding.fightButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_createFragment_to_gameFragment,
                bundleOf(
                    PLAYER_NAME to binding.titleInputLayout.editText?.text.toString(),
                    PLAYER_IMAGE to viewModel.playerImage.value
                )
            )
        }
        binding.playerImage.setOnClickListener {
            val dialogFragment = SelectImageAlertDialog {
                viewModel.setImage(it)
            }
            dialogFragment.show(childFragmentManager, "SELECT_IMAGE_DIALOG")
        }
    }

    private fun subscribe() {
        viewModel.playerImage.observe(viewLifecycleOwner) { imageId ->
            Glide.with(requireContext())
                .load(imageId)
                .centerCrop()
                .into(binding.playerImage)
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val PLAYER_NAME = "PLAYER_NAME"
        const val PLAYER_IMAGE = "PLAYER_IMAGE"
    }
}