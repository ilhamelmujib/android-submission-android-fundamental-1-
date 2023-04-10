package id.ilhamelmujib.submissionandroidfundamental.ui.detail

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import id.ilhamelmujib.submissionandroidfundamental.MainActivity
import id.ilhamelmujib.submissionandroidfundamental.databinding.FragmentDetailBinding
import id.ilhamelmujib.submissionandroidfundamental.ui.adapter.PagerAdapter
import id.ilhamelmujib.submissionandroidfundamental.ui.detail.follow.FollowFragment

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    val args: DetailFragmentArgs by navArgs()
    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.detailUser(args.user.login)
        binding.run {
            viewModel.isLoading.observe(viewLifecycleOwner) {
                if (it) {
                    progressBar.visibility = View.VISIBLE
                    rivAvatar.visibility = View.GONE
                    tvName.visibility = View.GONE
                    tvUsername.visibility = View.GONE
                } else {
                    progressBar.visibility = View.GONE
                    rivAvatar.visibility = View.VISIBLE
                    tvName.visibility = View.VISIBLE
                    tvUsername.visibility = View.VISIBLE
                }
            }
            viewModel.detail.observe(viewLifecycleOwner) {
                Glide
                    .with(requireContext())
                    .load(it.avatarURL)
                    .centerCrop()
                    .placeholder(ColorDrawable(Color.GRAY))
                    .into(rivAvatar)
                tvName.text = it.name
                tvUsername.text = "@${it.login}"

                val titles = listOf("Followers (${it.followers})", "Following (${it.following})")
                val pagerAdapter =
                    PagerAdapter(
                        (requireActivity() as MainActivity),
                        listOf(
                            FollowFragment().newInstance(args.user.login, "followers"),
                            FollowFragment().newInstance(args.user.login, "following")
                        )
                    )
                viewPager.adapter = pagerAdapter
                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                    tab.text = titles[position]
                }.attach()
            }
        }
    }

    override fun onDestroy() {
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(false)
        super.onDestroy()
    }

}