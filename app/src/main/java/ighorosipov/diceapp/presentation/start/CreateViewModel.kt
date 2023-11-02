package ighorosipov.diceapp.presentation.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class CreateViewModel @AssistedInject constructor(): ViewModel() {
    private val _playerImage = MutableLiveData<Int>()
    val playerImage: LiveData<Int> = _playerImage

    fun setImage(imageId: Int) {
        _playerImage.value = imageId
    }

    @AssistedFactory
    interface Factory {
        fun create(): CreateViewModel
    }

}