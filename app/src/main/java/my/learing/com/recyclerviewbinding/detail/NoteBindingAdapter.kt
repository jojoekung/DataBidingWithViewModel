package my.learing.com.recyclerviewbinding.detail

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import my.learing.com.recyclerviewbinding.R
import my.learing.com.recyclerviewbinding.database.NoteItem


@BindingAdapter(value = ["app:setNoteTitle"])
fun setNoteTitle(textView: TextView, note: LiveData<NoteItem>) {
    val title = textView.context.getString(R.string.title)
    textView.text = String.format("%s: %s", title, note.value?.title)
}

@BindingAdapter(value = ["app:setNoteDetail"])
fun setNoteDetail(textView: TextView, note: LiveData<NoteItem>) {
    textView.text = note.value?.note
}