package ighorosipov.diceapp

import android.app.Application
import ighorosipov.diceapp.di.AppComponent
import ighorosipov.diceapp.di.DaggerAppComponent

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }

}