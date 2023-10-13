package ighorosipov.diceapp.presentation.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ighorosipov.diceapp.databinding.FragmentGameBinding
import ighorosipov.diceapp.domain.entities.Monster
import ighorosipov.diceapp.domain.entities.Player

class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private var isPlayersMove = true
    private lateinit var player: Player
    private lateinit var monster: Monster

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.attackEntity.setOnClickListener {
            onPlayersMoved { playerAttack() }
        }

        binding.drinkHealPotion.setOnClickListener {
            onPlayersMoved { drinkHealPotion() }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}