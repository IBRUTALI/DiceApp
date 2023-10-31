package ighorosipov.diceapp.presentation.start

import androidx.lifecycle.ViewModel
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class CreateViewModel @AssistedInject constructor(): ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(): CreateViewModel
    }

}