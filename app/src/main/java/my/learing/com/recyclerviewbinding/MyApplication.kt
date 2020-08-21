package my.learing.com.recyclerviewbinding

import android.app.Application
import my.learing.com.recyclerviewbinding.di.AppComponent
import my.learing.com.recyclerviewbinding.di.DaggerAppComponent
import my.learing.com.recyclerviewbinding.di.module.AppModule

class MyApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

}