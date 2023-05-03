package id.ilhamelmujib.submissionandroidfundamental.data.local.room

import android.content.Context
import android.service.autofill.UserData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ilhamelmujib.submissionandroidfundamental.data.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var instance: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java,
                "user.db"
            ).build()
        }
    }

}