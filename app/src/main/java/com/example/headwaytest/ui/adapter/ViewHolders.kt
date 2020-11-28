package com.example.headwaytest.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.headwaytest.R
import com.example.headwaytest.api.RepoItem
import com.example.headwaytest.databinding.ItemRvRepoHistoryBinding
import com.example.headwaytest.databinding.ItemRvRepositoriesBinding
import java.text.SimpleDateFormat
import java.util.*

class RepoViewHolder(private val context: Context,
                     private val binding: ItemRvRepositoriesBinding,
                     private val listener: (RepoItem) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: RepoItem) {
        binding.tvName.text = item.name
        if (!TextUtils.isEmpty(item.owner.login)) {
            val ownerText = "Owner: ${item.owner.login}"
            binding.tvOwner.text = ownerText
        }
        setTextViewColor(context, binding, item.isViewed)
        binding.root.setOnClickListener { listener.invoke(item) }
    }

    private fun setTextViewColor(context: Context, binding: ItemRvRepositoriesBinding, viewed: Boolean) {
        val colorId = if (viewed) R.color.color_gray else R.color.color_text_main
        binding.tvOwner.setTextColor(ContextCompat.getColor(context, colorId))
        binding.tvName.setTextColor(ContextCompat.getColor(context, colorId))
    }

    companion object {
        fun create(listener: (RepoItem) -> Unit, parent: ViewGroup): RepoViewHolder {
            val binding = ItemRvRepositoriesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return RepoViewHolder(parent.context, binding, listener)
        }
    }
}

class RepoHistoryViewHolder(private val binding: ItemRvRepoHistoryBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: RepoHistoryItem) {
        binding.tvName.text = item.name
        binding.tvOwner.text = item.owner
        binding.tvDate.text = getDate(item.date)
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDate(dateLong: Long): String {
        val date = Date(dateLong)
        val format = SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
        return format.format(date)
    }

    companion object {
        fun create(parent: ViewGroup): RepoHistoryViewHolder {
            val binding = ItemRvRepoHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return RepoHistoryViewHolder(binding)
        }
    }
}