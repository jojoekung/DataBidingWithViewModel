package my.learing.com.recyclerviewbinding.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import my.learing.com.recyclerviewbinding.database.NoteDao
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(private val dataSource: NoteDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(dataSource = dataSource) as T
        }
        throw IllegalAccessException("Unknown view model class")
    }
}