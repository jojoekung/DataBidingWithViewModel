package my.learing.com.recyclerviewbinding.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import my.learing.com.recyclerviewbinding.database.NoteDao
import my.learing.com.recyclerviewbinding.database.NoteDatabase

@Module
class DatabaseModule() {

    private lateinit var noteDatabase: NoteDatabase

    @Provides
    fun provideDatabase(context: Context): NoteDatabase {
        noteDatabase = NoteDatabase.getInstance(context)
        return noteDatabase
    }

    @Provides
    fun provideDao(noteDatabase: NoteDatabase): NoteDao {
        return noteDatabase.noteDao
    }
}