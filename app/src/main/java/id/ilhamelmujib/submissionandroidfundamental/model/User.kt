package id.ilhamelmujib.submissionandroidfundamental.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName

@Parcelize
data class Result(
    @field:SerializedName("totalCount") val totalCount: Long,

    @field:SerializedName("incomplete_results") val incompleteResults: Boolean,

    @field:SerializedName("items") val items: List<User>
) : Parcelable

@Parcelize
data class User(
    @field:SerializedName("login") val login: String,

    @field:SerializedName("id") val id: Long,

    @field:SerializedName("node_id") val nodeID: String,

    @field:SerializedName("avatar_url") val avatarURL: String,

    @field:SerializedName("gravatar_id") val gravatarID: String,

    @field:SerializedName("url") val url: String,

    @field:SerializedName("html_url") val htmlURL: String,

    @field:SerializedName("followers_url") val followersURL: String,

    @field:SerializedName("following_url") val followingURL: String,

    @field:SerializedName("gists_url") val gistsURL: String,

    @field:SerializedName("starred_url") val starredURL: String,

    @field:SerializedName("subscriptions_url") val subscriptionsURL: String,

    @field:SerializedName("organizations_url") val organizationsURL: String,

    @field:SerializedName("repos_url") val reposURL: String,

    @field:SerializedName("events_url") val eventsURL: String,

    @field:SerializedName("received_events_url") val receivedEventsURL: String,

    @field:SerializedName("type") val type: String,

    @field:SerializedName("site_admin") val siteAdmin: Boolean,

    @field:SerializedName("score") val score: Long
) : Parcelable


data class Detail(
    @field:SerializedName("login") val login: String,

    @field:SerializedName("id") val id: Long,

    @field:SerializedName("node_id") val nodeID: String,

    @field:SerializedName("avatar_url") val avatarURL: String,

    @field:SerializedName("gravatar_id") val gravatarID: String,

    @field:SerializedName("url") val url: String,

    @field:SerializedName("html_url") val htmlURL: String,

    @field:SerializedName("followers_url") val followersURL: String,

    @field:SerializedName("following_url") val followingURL: String,

    @field:SerializedName("gists_url") val gistsURL: String,

    @field:SerializedName("starred_url") val starredURL: String,

    @field:SerializedName("subscriptions_url") val subscriptionsURL: String,

    @field:SerializedName("organizations_url") val organizationsURL: String,

    @field:SerializedName("repos_url") val reposURL: String,

    @field:SerializedName("events_url") val eventsURL: String,

    @field:SerializedName("received_events_url") val receivedEventsURL: String,

    @field:SerializedName("type") val type: String,

    @field:SerializedName("site_admin") val siteAdmin: Boolean,

    @field:SerializedName("name") val name: String,

    @field:SerializedName("company") val company: String? = null,

    @field:SerializedName("blog") val blog: String,

    @field:SerializedName("location") val location: String,

    @field:SerializedName("email") val email: String? = null,

    @field:SerializedName("hireable") val hireable: String? = null,

    @field:SerializedName("bio") val bio: String,

    @field:SerializedName("twitter_username") val twitterUsername: String? = null,

    @field:SerializedName("public_repos") val publicRepos: Long,

    @field:SerializedName("public_gists") val publicGists: Long,

    @field:SerializedName("followers") val followers: Long,

    @field:SerializedName("following") val following: Long,

    @field:SerializedName("created_at") val createdAt: String,

    @field:SerializedName("updated_at") val updatedAt: String
)
