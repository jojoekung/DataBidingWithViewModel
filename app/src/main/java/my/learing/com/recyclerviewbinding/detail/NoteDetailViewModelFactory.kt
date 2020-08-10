package my.learing.com.recyclerviewbinding.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import my.learing.com.recyclerviewbinding.database.NoteDao

class NoteDetailViewModelFactory(
    private val dataSource: NoteDao,
    private val context: Context,
    private val noteId: Long
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteDetailViewModel::class.java)) {
            return NoteDetailViewModel(
                dataSource = dataSource,
                context = context,
                noteId = noteId
            ) as T
        }
        throw IllegalAccessException("Unknown class")
    }
}