package my.learing.com.recyclerviewbinding.add

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import my.learing.com.recyclerviewbinding.R
import my.learing.com.recyclerviewbinding.database.NoteDatabase
import my.learing.com.recyclerviewbinding.databinding.AddNoteFragmentBinding

class AddNoteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: AddNoteFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.add_note_fragment, container, false)

        val dataSource = NoteDatabase.getInstance(requireContext()).noteDao

        //case edit note
        val noteId = arguments?.getLong("noteId")
        val factory =
            AddNoteFactory(dataSource = dataSource, context = requireContext(), noteId = noteId)
        val addNoteViewModel =
            ViewModelProvider(this, factory).get(AddNoteViewModel::class.java)

        binding.addNoteViewModel = addNoteViewModel
        binding.lifecycleOwner = this

        addNoteViewModel.isSaveNote.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    val imm =
                        requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(binding.root.windowToken, 0)

                    this.findNavController()
                        .navigate(AddNoteFragmentDirections.actionAddFragmentToHomeFragment())
                }
            }
        })

        addNoteViewModel.note.observe(viewLifecycleOwner, Observer {
            it?.let {
                addNoteViewModel.title.postValue(it.title)
                addNoteViewModel.noteDetail.postValue(it.note)
                addNoteViewModel.isEditNote = true
            }
        })

        return binding.root
    }
}