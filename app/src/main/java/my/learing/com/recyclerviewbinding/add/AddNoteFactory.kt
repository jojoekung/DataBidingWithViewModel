package my.learing.com.recyclerviewbinding.add

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import my.learing.com.recyclerviewbinding.database.NoteDao

class AddNoteFactory(
    private val dataSource: NoteDao,
    private val context: Context,
    private val noteId: Long?
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddNoteViewModel::class.java)) {
            return AddNoteViewModel(
                dataSource = dataSource,
                context = context,
                noteId = noteId
            ) as T
        }
        throw IllegalAccessException("Unknown class")
    }
}