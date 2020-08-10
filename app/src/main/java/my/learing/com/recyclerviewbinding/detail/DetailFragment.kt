package my.learing.com.recyclerviewbinding.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import my.learing.com.recyclerviewbinding.R
import my.learing.com.recyclerviewbinding.database.NoteDatabase
import my.learing.com.recyclerviewbinding.databinding.DetailFragmentBinding

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: DetailFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)

        val dataSource = NoteDatabase.getInstance(requireContext()).noteDao
        arguments?.getLong("noteId")?.let { noteId ->
            val factory = NoteDetailViewModelFactory(dataSource, requireContext(), noteId)
            val noteDetailViewModel =
                ViewModelProvider(this, factory).get(NoteDetailViewModel::class.java)
            binding.noteViewModel = noteDetailViewModel

            noteDetailViewModel.isBackToHome.observe(viewLifecycleOwner, Observer { isBack ->
                if (isBack) {
                    requireActivity().onBackPressed()
                }
            })

            noteDetailViewModel.isNavigateToEditNote.observe(
                viewLifecycleOwner,
                Observer { isEdit ->
                    isEdit?.let {
                        val id = bundleOf("noteId" to noteId)
                        this.findNavController()
                            .navigate(R.id.action_detailFragment_to_addFragment, id)
                        noteDetailViewModel.doneNavigateToEditNote()
                    }
                })
        }
        binding.lifecycleOwner = this

        return binding.root
    }
}