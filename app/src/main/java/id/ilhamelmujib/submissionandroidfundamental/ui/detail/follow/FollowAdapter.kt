package id.ilhamelmujib.submissionandroidfundamental.ui.detail.follow

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.ilhamelmujib.submissionandroidfundamental.data.local.entity.UserEntity
import id.ilhamelmujib.submissionandroidfundamental.databinding.ItemFollowBinding

class FollowAdapter(private val list: List<UserEntity>) :
    RecyclerView.Adapter<FollowAdapter.FollowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowViewHolder {
        val binding = ItemFollowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FollowViewHolder, position: Int) {
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
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class FollowViewHolder(val binding: ItemFollowBinding) : RecyclerView.ViewHolder(binding.root)
}