package my.learing.com.recyclerviewbinding.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import my.learing.com.recyclerviewbinding.database.NoteDao
import my.learing.com.recyclerviewbinding.database.NoteItem

class HomeViewModel(
    dataSource: NoteDao
) : ViewModel() {

    private val database = dataSource

    private var _note = database.getAllNote()

    val mNote: LiveData<List<NoteItem>>
        get() = _note

    private var _navigateToAddNote = MutableLiveData<Boolean?>()

    val isNavigateToAddNote: LiveData<Boolean?>
        get() = _navigateToAddNote

    private var _navigateToNoteDetail = MutableLiveData<Boolean?>()

    //reset _navigateToAddNote value
    fun doneNavigateToAddNote() {
        _navigateToAddNote.value = null
    }

    fun gotoAddNote() {
        _navigateToAddNote.value = true
    }
}