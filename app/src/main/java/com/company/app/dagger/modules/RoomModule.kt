package com.company.app.dagger.modules

import android.content.Context
import androidx.room.Room
import com.company.app.dagger.scopes.AppScope
import com.company.app.room.dao.UserDao
import com.company.app.room.db.DataBase
import dagger.Module
import dagger.Provides

@Module
open class RoomModule {

    @AppScope
    @Provides
    open fun providesRoomDatabase(context: Context): DataBase {
        return Room.databaseBuilder(context.applicationContext,
            DataBase::class.java, "z-driver.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @AppScope
    @Provides
    open fun providesUserDao(db: DataBase): UserDao {
        return db.userDao()
    }

}