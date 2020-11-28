package com.example.headwaytest.realm

import com.example.headwaytest.api.RepoItem
import com.example.headwaytest.realm.model.RepoHistoryRealm
import com.example.headwaytest.utils.Const
import io.realm.Realm
import io.realm.RealmResults
import java.util.*

class RealmManager(private val mRealm: Realm) {

    fun closeRealm() {
        if (!mRealm.isClosed) {
            mRealm.close()
        }
    }

    fun updateRepositories(item: RepoItem) {
        val date = Calendar.getInstance().time.time

        val repoHistoryList =
            mRealm.where(RepoHistoryRealm::class.java).sort("date").findAll()
        while (repoHistoryList.count() >= Const.HISTORY_SIZE){
            mRealm.executeTransaction{ repoHistoryList.deleteFirstFromRealm() }
        }

        mRealm.executeTransaction{realm ->
            realm.copyToRealm(RepoHistoryRealm(item, date))
        }
    }

    fun getRepositories(): List<RepoHistoryRealm> {
        val repositories: RealmResults<RepoHistoryRealm> =
            mRealm.where(RepoHistoryRealm::class.java).sort("date").findAll()
        return mRealm.copyFromRealm(repositories)
    }

    companion object{
        fun create(): RealmManager {
            return RealmManager(Realm.getDefaultInstance())
        }
    }

}