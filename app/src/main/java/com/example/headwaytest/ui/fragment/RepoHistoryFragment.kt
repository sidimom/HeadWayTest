package com.example.headwaytest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.headwaytest.databinding.FragmentRepoHistoryBinding
import com.example.headwaytest.ui.adapter.RepoHistoryAdapter
import com.example.headwaytest.viewModel.RepoHistoryViewModel

class RepoHistoryFragment : Fragment() {

    private var bindingNull: FragmentRepoHistoryBinding? = null
    private val binding
        get() = bindingNull!!

    private val viewModel: RepoHistoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingNull = FragmentRepoHistoryBinding.inflate(
            inflater,
            container,
            false
        )

        val adapter = RepoHistoryAdapter()
        binding.rvRepoHistory.adapter = adapter
        viewModel.repoHistoryList.observe(viewLifecycleOwner, {
            adapter.setData(it)
        })

        return binding.root
    }

    override fun onDestroyView() {
        bindingNull = null
        super.onDestroyView()
    }
}