package id.ilhamelmujib.submissionandroidfundamental.ui.user

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.ilhamelmujib.submissionandroidfundamental.R
import id.ilhamelmujib.submissionandroidfundamental.data.local.entity.UserEntity
import id.ilhamelmujib.submissionandroidfundamental.databinding.ItemUserBinding

class UserAdapter(
    private val list: List<UserEntity>, private val onClickListener: OnClickListener
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = list[position]
        val context = holder.binding.root.context
        holder.binding.run {
            Glide.with(context).load(user.avatarURL).centerCrop()
                .placeholder(ColorDrawable(Color.GRAY)).into(rivAvatar)
            tvUsername.text = user.login
            tvScore.text = "Score: ${user.score}"
            root.setOnClickListener {
                onClickListener.onItemClick(user)
            }

            btnFavorite.run {
                setImageResource(if (user.isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border)
                setOnClickListener {
                    onClickListener.onFavoriteClick(user)
                    user.isFavorite = !user.isFavorite
                    notifyItemChanged(position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnClickListener {
        fun onItemClick(user: UserEntity)
        fun onFavoriteClick(user: UserEntity)
    }
}


