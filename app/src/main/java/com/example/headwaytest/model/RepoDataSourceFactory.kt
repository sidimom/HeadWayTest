package com.example.headwaytest.model

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.headwaytest.api.NetworkService
import com.example.headwaytest.api.RepoItem
import io.reactivex.disposables.CompositeDisposable

class RepoDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val networkService: NetworkService
): DataSource.Factory<Int, RepoItem>() {

    val repoDataSourceLiveData = MutableLiveData<RepoDataSource>()
    var repoSearch: String = ""

    override fun create(): DataSource<Int, RepoItem> {
        val repoDataSource = RepoDataSource(networkService, compositeDisposable, repoSearch)
        repoDataSourceLiveData.postValue(repoDataSource)
        return repoDataSource
    }

    fun search(repoSearch: String) {
        this.repoSearch = repoSearch
    }

}