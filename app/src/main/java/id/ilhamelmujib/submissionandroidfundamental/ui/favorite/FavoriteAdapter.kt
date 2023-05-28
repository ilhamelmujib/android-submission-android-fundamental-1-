package id.ilhamelmujib.submissionandroidfundamental.ui.favorite

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.ilhamelmujib.submissionandroidfundamental.data.local.entity.UserEntity
import id.ilhamelmujib.submissionandroidfundamental.databinding.ItemFavoriteBinding

class FavoriteAdapter(private val list: List<UserEntity>, private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding =
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val user = list[position]
        val context = holder.binding.root.context
        holder.binding.run {
            Glide
                .with(context)
                .load(user.avatarURL)
                .centerCrop()
                .placeholder(ColorDrawable(Color.GRAY))
                .into(rivAvatar)
            tvUsername.text = user.login
            tvScore.text = "Score: ${user.score}"
            root.setOnClickListener {
                onClickListener.onItemClick(user)
            }
            btnDelete.setOnClickListener {
                onClickListener.onDeleteClick(user.id)
                notifyItemRemoved(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class FavoriteViewHolder(val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnClickListener{
        fun onItemClick(user: UserEntity)
        fun onDeleteClick(id: Long)
    }
}