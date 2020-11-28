package com.example.headwaytest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.headwaytest.R
import com.example.headwaytest.databinding.FragmentRepoListBinding
import com.example.headwaytest.ui.adapter.RepoListAdapter
import com.example.headwaytest.utils.State
import com.example.headwaytest.viewModel.RepoListViewModel

class RepoListFragment : Fragment() {

    private var bindingNull: FragmentRepoListBinding? = null
    private val binding
        get() = bindingNull!!

    private val viewModel: RepoListViewModel by viewModels()
    private lateinit var repoListAdapter: RepoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingNull = FragmentRepoListBinding.inflate(inflater, container, false)

        initRepoHistory()
        initAdapter()
        initState()
        initSearch()
        binding.fabHistory.setOnClickListener {
            NavHostFragment
                .findNavController(this)
                .navigate(
                    RepoListFragmentDirections
                        .actionRepoListFragmentToRepoHistoryFragment()
                )
        }

        return binding.root
    }

    private fun initRepoHistory() {
        viewModel.historyList.observe(viewLifecycleOwner, {
            repoListAdapter.setHistoryList(it)
        })
    }

    private fun initAdapter() {
        repoListAdapter = RepoListAdapter { repoItem ->
            viewModel.updateHistory(repoItem)
            NavHostFragment
                .findNavController(this)
                .navigate(
                    RepoListFragmentDirections
                        .actionRepoListFragmentToWebRepoFragment(repoItem.htmlUrl)
                )
        }
        binding.rvRepoList.layoutManager  = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvRepoList.adapter = repoListAdapter
        viewModel.repoList.observe(viewLifecycleOwner, {
            repoListAdapter.submitList(it)
        })
    }

    private fun initState() {
        viewModel.getState().observe(viewLifecycleOwner, { state ->
            binding.pbCircular.visibility = if (state == State.LOADING) View.VISIBLE else View.GONE
            if (state == State.ERROR) {
                Toast.makeText(context, R.string.error_connection, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun initSearch() {
        binding.svRepoSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                p0?.let { if (it.isNotEmpty()) viewModel.searchRepo(it)}
                return false
            }
        })
    }

    override fun onDestroyView() {
        bindingNull = null
        super.onDestroyView()
    }
}