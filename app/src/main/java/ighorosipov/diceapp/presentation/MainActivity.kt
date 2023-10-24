package ighorosipov.diceapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ighorosipov.diceapp.databinding.ActivityMainBinding
import ighorosipov.diceapp.presentation.game.GameFragment

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .add(GameFragment(), "Game fragment")
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}