package com.example.headwaytest.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.headwaytest.api.RepoItem

class RepoListAdapter(
    private val listener: (RepoItem) -> Unit
): PagedListAdapter<RepoItem, RecyclerView.ViewHolder>(RepoDiffCallback) {

    private var historyList = listOf<RepoHistoryItem>()

    companion object {

        val RepoDiffCallback = object : DiffUtil.ItemCallback<RepoItem>() {
            override fun areItemsTheSame(oldItem: RepoItem, newItem: RepoItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RepoItem, newItem: RepoItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        RepoViewHolder.create(listener, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as RepoViewHolder).bind(getItem(position)!!)

    override fun submitList(pagedList: PagedList<RepoItem>?) {
        super.submitList(setViewedRepo(pagedList))
    }

    fun setHistoryList(historyList: List<RepoHistoryItem>) {
        this.historyList = historyList
    }

    private fun setViewedRepo(pagedList: PagedList<RepoItem>?): PagedList<RepoItem>? {
        if (pagedList == null)
            return null

        for (i in 0 until pagedList.size) {
            val repo = pagedList[i]
            repo?.let{item ->
                val historyFiltered = historyList.filter { it.id == item.id }
                item.isViewed = (historyFiltered.isNotEmpty())
            }
        }

        return pagedList
    }
}