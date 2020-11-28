package com.example.headwaytest

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        initRealm()
    }

    private fun initRealm() {
        Realm.init(this)
        Realm.setDefaultConfiguration(
            RealmConfiguration.Builder()
                .name("head-way.realm")
                .deleteRealmIfMigrationNeeded()
                .build()
        )
    }
}