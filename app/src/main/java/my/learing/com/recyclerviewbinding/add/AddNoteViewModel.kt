package my.learing.com.recyclerviewbinding.add

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import my.learing.com.recyclerviewbinding.database.NoteDao
import my.learing.com.recyclerviewbinding.database.NoteItem

class AddNoteViewModel(
    val dataSource: NoteDao,
    private val context: Context,
    private val noteId: Long?
) : ViewModel() {

    var title = MutableLiveData<String>("")
    var noteDetail = MutableLiveData<String>("")

    private val _note = dataSource.getNoteById(noteId)

    private var _isSaveNote = MutableLiveData<Boolean>()
    val isSaveNote: LiveData<Boolean>
        get() = _isSaveNote

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var isEditNote = false

    val note: LiveData<NoteItem>
        get() = _note

    init {
        noteId?.let {
            isEditNote = true
        }
    }

    fun onSave() {
        uiScope.launch {
            if (isEditNote) {
                updateData()
            } else {
                insertData()
            }
        }
    }

    private suspend fun insertData() {
        val detail = this.noteDetail.value ?: ""
        val title = this.title.value ?: ""

        val isValidateNote = detail.isNotEmpty() && title.isNotEmpty()

        if (isValidateNote) {
            withContext(Dispatchers.IO) {
                val data = NoteItem(note = detail, title = title)
                dataSource.insertNote(data)
            }
            _isSaveNote.postValue(true)
            Toast.makeText(context, "save complete", Toast.LENGTH_SHORT).show()
        } else {
            showInvalidData()
        }
    }

    private suspend fun updateData() {
        val detail = this.noteDetail.value ?: ""
        val title = this.title.value ?: ""

        val isValidateNote = detail.isNotEmpty() && title.isNotEmpty()

        if (isValidateNote) {
            withContext(Dispatchers.IO) {
                _note.value?.title = title
                _note.value?.note = detail
                dataSource.updateNote(noteItem = _note.value)
            }
            _isSaveNote.postValue(true)
            Toast.makeText(context, "Edit is complete", Toast.LENGTH_SHORT).show()
        } else {
            showInvalidData()
        }
    }

    private fun showInvalidData() {
        Toast.makeText(context, "Please complete all information.", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}