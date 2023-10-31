package ighorosipov.diceapp.di

import dagger.Binds
import dagger.Module
import ighorosipov.diceapp.data.EntitiesActionImpl
import ighorosipov.diceapp.domain.repository.EntitiesAction

@Module
interface RepositoryModule {

    @Binds
    fun bindEntitiesAction(entitiesAction: EntitiesActionImpl): EntitiesAction

}