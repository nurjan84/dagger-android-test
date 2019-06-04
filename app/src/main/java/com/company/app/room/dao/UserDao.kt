package com.company.app.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.company.app.room.entities.User
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface UserDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(o: User)

    @Query("SELECT * FROM user WHERE userId = :id")
    fun getUserMaybe(id: Int): Maybe<User>

    @Query("SELECT * FROM user WHERE userId =:id")
    fun getUserSingle(id:Int): Single<User>

    @Query("SELECT * FROM user WHERE userId =:id")
    fun getUserFlowable(id:Int): Flowable<User>


    @Query("UPDATE user SET token = NULL WHERE userId = :id")
    fun deleteUserToken(id: Int)

    @Query("DELETE FROM user")
    fun deleteAll()
}