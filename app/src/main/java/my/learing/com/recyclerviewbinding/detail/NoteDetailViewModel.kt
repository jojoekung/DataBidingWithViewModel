package my.learing.com.recyclerviewbinding.detail

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import my.learing.com.recyclerviewbinding.R
import my.learing.com.recyclerviewbinding.database.NoteDao
import my.learing.com.recyclerviewbinding.database.NoteItem

class NoteDetailViewModel(
    private val dataSource: NoteDao,
    private val context: Context,
    private val noteId: Long
) :
    ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val dao = dataSource
    private var _note: LiveData<NoteItem>

    fun getNote() = _note

    private var _isBackToHome = MutableLiveData<Boolean>()
    val isBackToHome: LiveData<Boolean>
        get() = _isBackToHome

    private var _isNavigateToEdit = MutableLiveData<Boolean?>()

    val isNavigateToEditNote: LiveData<Boolean?>
        get() = _isNavigateToEdit

    init {
        _note = dao.getNoteById(noteId)
    }

    fun getNoteTitle(): String {
        val title = context.getString(R.string.title)
        return String.format("%s: %s", title, _note.value?.title)
    }

    fun onDeleteNote() {
        uiScope.launch {
            deleteNoteById(noteId)
        }
        backToHome()
    }

    private suspend fun deleteNoteById(id: Long) {
        withContext(Dispatchers.IO) {
            dataSource.deleteNoteById(id)
        }
        Toast.makeText(context, "Delete Complete", Toast.LENGTH_SHORT).show()
    }


    fun gotoEditNote() {
        _isNavigateToEdit.postValue(true)
    }

    fun doneNavigateToEditNote() {
        _isNavigateToEdit.value = null
    }

    private fun backToHome() {
        _isBackToHome.postValue(true)
    }
}