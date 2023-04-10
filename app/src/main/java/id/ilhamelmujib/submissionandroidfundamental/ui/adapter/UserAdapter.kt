package id.ilhamelmujib.submissionandroidfundamental.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.ilhamelmujib.submissionandroidfundamental.databinding.ItemUserBinding
import id.ilhamelmujib.submissionandroidfundamental.model.User
import id.ilhamelmujib.submissionandroidfundamental.ui.user.UserFragmentDirections

class UserAdapter(private val list: List<User>, private val isClickable: Boolean = true) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
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
                if (isClickable) {
                    val action = UserFragmentDirections.actionUserFragmentToDetailFragment(user)
                    root.findNavController().navigate(action)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)
}