package com.teamtwothree.kartasvalokapp

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.teamtwothree.kartasvalokapp.db.KSDao
import com.teamtwothree.kartasvalokapp.db.KSDatabase
import com.teamtwothree.kartasvalokapp.di.networkModule
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class AppDelegate : Application() {

    override fun onCreate() {
        super.onCreate()

        val kodein: Kodein = Kodein {
            import(networkModule)
            bind<KSDao>() with singleton {
                Room.databaseBuilder(this@AppDelegate, KSDatabase::class.java, "ksdb")
                    .fallbackToDestructiveMigration()
                    .build().getKSDao()
            }
        }
    }

}