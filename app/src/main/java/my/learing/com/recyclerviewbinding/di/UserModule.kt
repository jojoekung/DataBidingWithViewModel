package my.learing.com.recyclerviewbinding.di

import dagger.Module
import dagger.Provides
import my.learing.com.recyclerviewbinding.home.User

@Module
class UserModule {
    @Provides
    fun provideUser() = User(name = "Joe", id = 1)
}