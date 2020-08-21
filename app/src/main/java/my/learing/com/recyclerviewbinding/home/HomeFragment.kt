package my.learing.com.recyclerviewbinding.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import my.learing.com.recyclerviewbinding.MyApplication
import my.learing.com.recyclerviewbinding.R
import my.learing.com.recyclerviewbinding.database.NoteDao
import my.learing.com.recyclerviewbinding.database.NoteDatabase
import my.learing.com.recyclerviewbinding.databinding.HomeFragmentBinding
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: HomeFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)

        val homeViewModel =
            ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel::class.java)

        binding.homeViewModel = homeViewModel

        binding.lifecycleOwner = this

        homeViewModel.isNavigateToAddNote.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController()
                    .navigate(R.id.action_homeFragment_to_addFragment)
                homeViewModel.doneNavigateToAddNote()
            }
        })

        val adapter = NoteAdapter(OnClickItemListener {
            val bundle = bundleOf("noteId" to it)
            this.findNavController()
                .navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        })
        binding.rcvNote.adapter = adapter

        homeViewModel.mNote.observe(viewLifecycleOwner, Observer { note ->
            note?.let {
                adapter.submitList(it)
            }
        })
        binding.rcvNote.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        return binding.root
    }

    override fun onAttach(context: Context) {

        (requireActivity().application as MyApplication).appComponent.inject(this)
        super.onAttach(context)
    }
}