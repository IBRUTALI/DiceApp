package ighorosipov.diceapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ighorosipov.diceapp.data.EntitiesActionImpl
import ighorosipov.diceapp.databinding.ActivityMainBinding
import ighorosipov.diceapp.domain.entities.Monster
import ighorosipov.diceapp.domain.entities.Player

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private var binding = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}