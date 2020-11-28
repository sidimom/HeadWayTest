package com.example.headwaytest.realm.model

import com.example.headwaytest.api.RepoItem
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RepoHistoryRealm(var id: Int = 0,
                            var name: String = "",
                            var owner: String = "",
                            @PrimaryKey var date: Long = 0L
): RealmObject() {
    constructor(repoItem: RepoItem,
                date: Long) : this(
        repoItem.id,
        repoItem.name,
        repoItem.owner.login,
        date
    )
}