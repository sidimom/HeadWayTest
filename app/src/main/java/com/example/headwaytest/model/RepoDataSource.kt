package com.example.headwaytest.model

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.headwaytest.api.NetworkService
import com.example.headwaytest.api.RepoItem
import com.example.headwaytest.utils.State
import io.reactivex.disposables.CompositeDisposable

class RepoDataSource(
    private val networkService: NetworkService,
    private val compositeDisposable: CompositeDisposable,
    private val query: String = ""
): PageKeyedDataSource<Int, RepoItem>() {

    var state: MutableLiveData<State> = MutableLiveData()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, RepoItem>
    ) {
        if (query.isEmpty())
            return

        updateState(State.LOADING)
        compositeDisposable.add(
            networkService.api.getRepositories(query,1, params.requestedLoadSize)
                .subscribe(
                    {
                        updateState(State.DONE)
                        callback.onResult(it.items, null, 2)
                    },
                    {
                        updateState(State.ERROR)
                    }
                ))
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, RepoItem>
    ) {}

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, RepoItem>
    ) {
        if (query.isEmpty())
            return

        updateState(State.LOADING)
        compositeDisposable.add(
            networkService.api.getRepositories(query, params.key + 1, params.requestedLoadSize)
                .subscribe(
                    {
                        updateState(State.DONE)
                        callback.onResult(it.items,
                            params.key + 1
                        )
                    },
                    {
                        updateState(State.ERROR)
                    }
                )
        )
    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }
}