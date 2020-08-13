package my.learing.com.recyclerviewbinding.home

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import my.learing.com.recyclerviewbinding.database.NoteItem

@BindingAdapter(value = ["app:setVisibleNoData"])
fun setVisibleNoData(view: View, note: LiveData<List<NoteItem>>) {
    view.visibility = if (note.value.isNullOrEmpty()) {
        View.VISIBLE
    } else {
        View.GONE
    }
}