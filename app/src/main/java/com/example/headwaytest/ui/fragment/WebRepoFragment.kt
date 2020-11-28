package com.example.headwaytest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.headwaytest.databinding.FragmentWebRepoBinding

class WebRepoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWebRepoBinding.inflate(
            inflater,
            container,
            false
        )

        val url = WebRepoFragmentArgs.fromBundle(requireArguments()).url
        binding.webView.loadUrl(url)

        return binding.root
    }
}