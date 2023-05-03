package id.ilhamelmujib.submissionandroidfundamental.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.ilhamelmujib.submissionandroidfundamental.data.local.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUsers() : LiveData<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: UserEntity)

    @Query("DELETE FROM user WHERE id = :id")
    suspend fun deleteUser(id: Long)

    @Query("SELECT EXISTS(SELECT * FROM user WHERE id = :id)")
    suspend fun isUserExist(id: Long) : Boolean

}