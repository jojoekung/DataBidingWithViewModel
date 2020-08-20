package my.learing.com.recyclerviewbinding

import android.app.Application
import my.learing.com.recyclerviewbinding.di.AppComponent
import my.learing.com.recyclerviewbinding.di.DaggerAppComponent

class MyApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create()
    }

}