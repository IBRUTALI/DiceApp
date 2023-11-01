package ighorosipov.diceapp.presentation.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ighorosipov.diceapp.R
import ighorosipov.diceapp.databinding.FragmentCreateBinding
import ighorosipov.diceapp.presentation.start.dialog.SelectImageAlertDialog

class CreateFragment : Fragment() {
    private var _binding: FragmentCreateBinding? = null
    private val binding get() = _binding!!

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
        binding.fightButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_createFragment_to_gameFragment,
                bundleOf(
                    PLAYER_NAME to binding.titleInputLayout.editText?.text.toString()
                )
            )
        }
        binding.playerImage.setOnClickListener {
            val dialogFragment = SelectImageAlertDialog {

            }
            dialogFragment.show(childFragmentManager, "SELECT_IMAGE_DIALOG")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val PLAYER_NAME = "PLAYER_NAME"
    }
}