package my.learing.com.recyclerviewbinding.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import my.learing.com.recyclerviewbinding.database.NoteItem
import my.learing.com.recyclerviewbinding.databinding.NoteItemLayoutBinding

class NoteAdapters : ListAdapter<NoteItem, NoteAdapters.NoteHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {

        return NoteHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class NoteHolder private constructor(private val binding: NoteItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(noteItem: NoteItem) {
            binding.note = noteItem
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): NoteHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = NoteItemLayoutBinding.inflate(inflater, parent, false)
                return NoteHolder(binding)
            }
        }
    }

    class NoteDiffCallback : DiffUtil.ItemCallback<NoteItem>() {
        override fun areItemsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
            return oldItem == newItem
        }
    }
}