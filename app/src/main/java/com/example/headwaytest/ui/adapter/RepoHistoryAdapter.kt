package com.example.headwaytest.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RepoHistoryAdapter: RecyclerView.Adapter<RepoHistoryViewHolder>() {

    private var items: List<RepoHistoryItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoHistoryViewHolder =
        RepoHistoryViewHolder.create(parent)

    override fun onBindViewHolder(holder: RepoHistoryViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    fun setData(items: List<RepoHistoryItem>) {
        this.items = items
        notifyDataSetChanged()
    }
}