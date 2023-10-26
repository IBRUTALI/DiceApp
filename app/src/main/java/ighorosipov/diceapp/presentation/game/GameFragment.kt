package ighorosipov.diceapp.presentation.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import ighorosipov.diceapp.R
import ighorosipov.diceapp.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<GameViewModel>()
    private val adapter by lazy { LogAdapter() }

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

        test()

        subscribe()

        binding.attackEntity.setOnClickListener {
            viewModel.onPlayersMoved()
        }

        binding.drinkHealPotion.setOnClickListener {
            viewModel.drinkHealPotion()
        }
    }

    private fun test() {
        binding.imageOfEntity.setImageResource(R.drawable.orc)
        binding.imageOfPlayer.setImageResource(R.drawable.dwarf)
    }

    private fun subscribe() {
        viewModel.player.observe(viewLifecycleOwner) { player ->
            binding.apply {
                val playerArmor = resources.getString(R.string.armor) + ": ${player.armor}"
                armorOfPlayer.text = playerArmor
                val playerAttackPower = resources.getString(R.string.attack_power) + ": ${player.attackPower}"
                attackPowerOfPlayer.text = playerAttackPower
                val playerHP = "${player.currentHealPoint}/${player.maxHealPoint}"
                hpOfPlayer.text = playerHP
                val playerHealPotion = "${player.healPotion}"
                healPotionCount.text = playerHealPotion
            }
        }

        viewModel.monster.observe(viewLifecycleOwner) { entity ->
            binding.apply {
                val entityArmor = resources.getString(R.string.armor) + ": ${entity.armor}"
                armorOfEntity.text = entityArmor
                val entityAttackPower = resources.getString(R.string.attack_power) + ": ${entity.attackPower}"
                attackPowerOfEntity.text = entityAttackPower
                val entityHP = "${entity.currentHealPoint}/${entity.maxHealPoint}"
                hpOfEntity.text = entityHP
            }
        }
    }

    private fun setupAdapter() {
        binding.apply {
            gameLog.layoutManager = LinearLayoutManager(requireContext())
            gameLog.adapter = adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}