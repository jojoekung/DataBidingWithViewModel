package my.learing.com.recyclerviewbinding.di

import dagger.Component
import dagger.Subcomponent
import my.learing.com.recyclerviewbinding.MainActivity
import my.learing.com.recyclerviewbinding.home.HomeComponent
import my.learing.com.recyclerviewbinding.home.HomeFragment

@Component(modules = [Subcomponent::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }

    fun homeComponent(): HomeComponent.Factory

    fun inject(activity: MainActivity)
    fun inject(fragment: HomeFragment)
}