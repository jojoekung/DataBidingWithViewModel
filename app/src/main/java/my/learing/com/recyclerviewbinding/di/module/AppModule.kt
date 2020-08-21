package my.learing.com.recyclerviewbinding.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import my.learing.com.recyclerviewbinding.database.NOTE_ID
import my.learing.com.recyclerviewbinding.detail.DetailFragment
import my.learing.com.recyclerviewbinding.home.HomeFragment

@Module
class AppModule(private val application: Application) {

    @Provides
    fun provideContext(): Context = application.applicationContext

    @Provides
    fun provideNoteId(homeFragment: DetailFragment): Long? {
        return homeFragment.arguments?.getLong(NOTE_ID)
    }
}