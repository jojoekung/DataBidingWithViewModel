package my.learing.com.recyclerviewbinding.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import my.learing.com.recyclerviewbinding.database.NoteItem
import my.learing.com.recyclerviewbinding.databinding.NoteItemLayoutBinding

class NoteAdapter(private val onClickItemListener: OnClickItemListener) :
    ListAdapter<NoteItem, NoteAdapter.NoteHolder>(NoteCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NoteItemLayoutBinding.inflate(inflater, parent, false)
        return NoteHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
            val item = getItem(position)
            holder.bind(item, onClickItemListener = onClickItemListener)
    }

    class NoteHolder(private val noteItemLayoutBinding: NoteItemLayoutBinding) :
        RecyclerView.ViewHolder(noteItemLayoutBinding.root) {

        fun bind(item: NoteItem, onClickItemListener: OnClickItemListener) {
            noteItemLayoutBinding.note = item
            noteItemLayoutBinding.listener = onClickItemListener
            noteItemLayoutBinding.executePendingBindings()
        }
    }

    class NoteCallBack : DiffUtil.ItemCallback<NoteItem>() {
        override fun areItemsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
            return oldItem == newItem
        }
    }
}

class OnClickItemListener(val onClickListener: (noteId: Long) -> Unit) {
    fun onClick(noteItem: NoteItem) = onClickListener(noteItem.id)
}