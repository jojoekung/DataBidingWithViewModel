package my.learing.com.recyclerviewbinding.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import my.learing.com.recyclerviewbinding.database.NoteDao

class HomeViewModelFactory(private val dataSource: NoteDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(dataSource = dataSource) as T
        }
        throw IllegalAccessException("Unknown view model class")
    }
}