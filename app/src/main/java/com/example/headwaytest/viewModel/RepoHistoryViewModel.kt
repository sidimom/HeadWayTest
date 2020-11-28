package com.example.headwaytest.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.headwaytest.ui.adapter.RepoHistoryItem
import com.example.headwaytest.utils.Utils

class RepoHistoryViewModel: ViewModel() {

    var repoHistoryList: MutableLiveData<List<RepoHistoryItem>> = MutableLiveData()

    init {
        repoHistoryList.postValue(Utils.getRepoHistoryList())
    }
}