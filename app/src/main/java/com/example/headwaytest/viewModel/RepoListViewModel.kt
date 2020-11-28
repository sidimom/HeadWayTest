package com.example.headwaytest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.headwaytest.api.NetworkService
import com.example.headwaytest.api.RepoItem
import com.example.headwaytest.model.RepoDataSource
import com.example.headwaytest.model.RepoDataSourceFactory
import com.example.headwaytest.realm.RealmManager
import com.example.headwaytest.ui.adapter.RepoHistoryItem
import com.example.headwaytest.utils.Const
import com.example.headwaytest.utils.State
import com.example.headwaytest.utils.Utils
import io.reactivex.disposables.CompositeDisposable

class RepoListViewModel: ViewModel() {

    private val networkService = NetworkService.getInstance()
    var repoList: LiveData<PagedList<RepoItem>>
    val historyList: MutableLiveData<List<RepoHistoryItem>> = MutableLiveData()
    private val compositeDisposable = CompositeDisposable()
    private var repoDataSourceFactory: RepoDataSourceFactory

    init {
        repoDataSourceFactory = RepoDataSourceFactory(compositeDisposable, networkService)
        val config = PagedList.Config.Builder()
            .setPageSize(Const.PAGE_SIZE)
            .setInitialLoadSizeHint(Const.PAGE_SIZE * 2)
            .setEnablePlaceholders(false)
            .build()
        repoList = LivePagedListBuilder(repoDataSourceFactory, config).build()
        historyList.postValue(Utils.getRepoHistoryList())
    }

    fun getState(): LiveData<State> = Transformations.switchMap(
        repoDataSourceFactory.repoDataSourceLiveData,
        RepoDataSource::state
    )

    fun updateHistory(repoItem: RepoItem) {
        val realm = RealmManager.create()
        realm.updateRepositories(repoItem)
        realm.closeRealm()

        historyList.postValue(Utils.getRepoHistoryList())
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun searchRepo(repoSearch: String) {
        repoDataSourceFactory.search(repoSearch)
        repoList.value?.dataSource?.invalidate()
    }
}