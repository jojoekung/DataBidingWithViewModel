package my.learing.com.recyclerviewbinding.home

import dagger.Subcomponent

@HomeScope
@Subcomponent
interface HomeComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(fragment: HomeFragment)
}