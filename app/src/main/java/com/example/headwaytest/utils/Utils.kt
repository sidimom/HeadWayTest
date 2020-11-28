package com.example.headwaytest.utils

import com.example.headwaytest.realm.RealmManager
import com.example.headwaytest.ui.adapter.RepoHistoryItem

class Utils {
    companion object{
        fun getRepoHistoryList(): List<RepoHistoryItem> {
            val realm = RealmManager.create()
            val repoListRealm = realm.getRepositories()
            realm.closeRealm()

            val repoList: MutableList<RepoHistoryItem> = mutableListOf()
            for (item in repoListRealm) {
                repoList.add(RepoHistoryItem(item))
            }

            return repoList
        }
    }
}