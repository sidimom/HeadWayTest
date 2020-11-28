package com.example.headwaytest.ui.adapter

import com.example.headwaytest.realm.model.RepoHistoryRealm

data class RepoHistoryItem(val id: Int,
                           val name: String,
                           val owner: String,
                           val date: Long) {
    constructor(itemRealm: RepoHistoryRealm): this(
        itemRealm.id,
        itemRealm.name,
        itemRealm.owner,
        itemRealm.date
    )
}