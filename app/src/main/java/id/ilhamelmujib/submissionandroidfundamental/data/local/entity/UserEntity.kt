package id.ilhamelmujib.submissionandroidfundamental.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user")
class UserEntity (
    @field:ColumnInfo(name = "id")
    @field:PrimaryKey
    val id: Long,

    @field:ColumnInfo(name = "login")
    val login: String,

    @field:ColumnInfo(name = "avatar_url")
    val avatarURL: String,

    @field:ColumnInfo(name = "score")
    val score: Long,

    var isFavorite: Boolean = false,
) : Parcelable