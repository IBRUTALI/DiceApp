package ighorosipov.diceapp.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ighorosipov.diceapp.presentation.MainActivity
import ighorosipov.diceapp.presentation.MainViewModel
import ighorosipov.diceapp.presentation.game.GameFragment
import ighorosipov.diceapp.presentation.game.GameViewModel
import ighorosipov.diceapp.presentation.start.CreateFragment
import ighorosipov.diceapp.presentation.start.CreateViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

    fun inject(mainActivity: MainActivity)
    fun inject(gameFragment: GameFragment)
    fun inject(createFragment: CreateFragment)

    fun gameViewModel(): GameViewModel.Factory
    fun createViewModel(): CreateViewModel.Factory

}