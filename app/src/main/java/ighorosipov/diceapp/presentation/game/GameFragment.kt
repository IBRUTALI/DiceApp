package ighorosipov.diceapp.presentation.game

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import ighorosipov.diceapp.R
import ighorosipov.diceapp.databinding.FragmentGameBinding
import ighorosipov.diceapp.presentation.start.CreateFragment.Companion.PLAYER_IMAGE
import ighorosipov.diceapp.presentation.start.CreateFragment.Companion.PLAYER_NAME
import ighorosipov.diceapp.utils.GameState
import ighorosipov.diceapp.utils.extensions.appComponent
import ighorosipov.diceapp.utils.extensions.lazyViewModel
import ighorosipov.diceapp.utils.extensions.showAlert


class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GameViewModel by lazyViewModel {
        requireContext().appComponent().gameViewModel().create(
            arguments?.getString(PLAYER_NAME),
            arguments?.getInt(PLAYER_IMAGE)
        )
    }
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
        setupAdapter()

        binding.attackEntity.setOnClickListener {
            viewModel.onPlayersMoved()
        }

        binding.drinkHealPotion.setOnClickListener {
            viewModel.drinkHealPotion()
        }
    }

    private fun test() {
        binding.imageOfEntity.setImageResource(R.drawable.orc)
        Glide.with(requireContext())
            .load(viewModel.player.value?.image)
            .error(R.drawable.dwarf)
            .centerCrop()
            .into(binding.imageOfPlayer)
    }

    private fun subscribe() {
        viewModel.player.observe(viewLifecycleOwner) { player ->
            binding.apply {
                val playerArmor = "${player.armor}"
                armorOfPlayer.text = playerArmor
                val playerAttackPower = "${player.attackPower}"
                attackPowerOfPlayer.text = playerAttackPower
                val playerHP = "${player.currentHealPoint}/${player.maxHealPoint}"
                hpOfPlayer.text = playerHP
                val playerHealPotion = "${player.healPotion}"
                healPotionCount.text = playerHealPotion
                nameOfPlayer.text = player.name
                progressHPOfPlayer.max = player.maxHealPoint
                ObjectAnimator.ofInt(
                    progressHPOfPlayer,
                    getString(R.string.progress),
                    player.currentHealPoint
                )
                    .setDuration(50)
                    .start()
            }
        }

        viewModel.monster.observe(viewLifecycleOwner) { entity ->
            binding.apply {
                val entityArmor = "${entity.armor}"
                armorOfEntity.text = entityArmor
                val entityAttackPower = "${entity.attackPower}"
                attackPowerOfEntity.text = entityAttackPower
                val entityHP = "${entity.currentHealPoint}/${entity.maxHealPoint}"
                hpOfEntity.text = entityHP
                nameOfEntity.text = entity.name
                progressHPOfEntity.max = entity.maxHealPoint
                ObjectAnimator.ofInt(
                    progressHPOfEntity,
                    getString(R.string.progress),
                    entity.currentHealPoint
                )
                    .setDuration(200)
                    .start()
            }
        }

        viewModel.log.observe(viewLifecycleOwner) { log ->
            adapter.setData(log)
            binding.gameLog.scrollToPosition(log.size - 1)
        }

        viewModel.gameState.observe(viewLifecycleOwner) { state ->
            when (state) {
                GameState.WIN -> {
                    showAlert(
                        title = "Win",
                        message = "You are winner",
                        positiveButtonText = "Next fight",
                        onPositiveButtonClick = { viewModel.restartGame() },
                        onNegativeButtonClick = { activity?.finish() }
                    )
                }

                GameState.LOSE -> {
                    showAlert(
                        title = "Lose",
                        message = "You are loser",
                        positiveButtonText = "Restart",
                        onPositiveButtonClick = { viewModel.restartGame() },
                        onNegativeButtonClick = { activity?.finish() }
                    )
                }

                else -> {

                }
            }
        }
    }

    private fun setupAdapter() {
        binding.apply {
            val layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, true)
            gameLog.layoutManager = layoutManager
            gameLog.adapter = adapter
            val dividerItemDecoration = DividerItemDecoration(
                gameLog.context,
                layoutManager.orientation
            )
            gameLog.addItemDecoration(dividerItemDecoration)
        }
    }

    private fun inject() {
        requireContext().appComponent().inject(this)
    }

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}