package my.learing.com.recyclerviewbinding.di

import dagger.Component
import my.learing.com.recyclerviewbinding.detail.DetailFragment
import my.learing.com.recyclerviewbinding.di.module.AppModule
import my.learing.com.recyclerviewbinding.di.module.DatabaseModule
import my.learing.com.recyclerviewbinding.home.HomeFragment

@Component(modules = [DatabaseModule::class, AppModule::class])
interface AppComponent {

    fun inject(fragment: HomeFragment)
    fun inject(fragment: DetailFragment)
}