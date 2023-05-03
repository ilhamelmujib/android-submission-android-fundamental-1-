package id.ilhamelmujib.submissionandroidfundamental.ui.detail

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import id.ilhamelmujib.submissionandroidfundamental.data.Result
import id.ilhamelmujib.submissionandroidfundamental.ui.MainActivity
import id.ilhamelmujib.submissionandroidfundamental.databinding.FragmentDetailBinding
import id.ilhamelmujib.submissionandroidfundamental.utils.PagerAdapter
import id.ilhamelmujib.submissionandroidfundamental.ui.detail.follow.FollowFragment

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    val args: DetailFragmentArgs by navArgs()
    private val viewModel by viewModels<DetailViewModel>(){
        DetailViewModelFactory.getInstance(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
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
        viewModel.detailUser(args.user.login).observe(viewLifecycleOwner) { result ->
            if (result != null) {
                binding.run {
                    when (result) {
                        is Result.Loading -> {
                            progressBar.visibility = View.VISIBLE
                            rivAvatar.visibility = View.GONE
                            tvName.visibility = View.GONE
                            tvUsername.visibility = View.GONE
                        }
                        is Result.Error -> {
                            progressBar.visibility = View.GONE
                            rivAvatar.visibility = View.VISIBLE
                            tvName.visibility = View.VISIBLE
                            tvUsername.visibility = View.VISIBLE
                            Toast.makeText(
                                context, "Terjadi kesalahan" + result.error, Toast.LENGTH_SHORT
                            ).show()
                        }
                        is Result.Success -> {
                            progressBar.visibility = View.GONE
                            rivAvatar.visibility = View.VISIBLE
                            tvName.visibility = View.VISIBLE
                            tvUsername.visibility = View.VISIBLE

                            Glide
                                .with(requireContext())
                                .load(result.data.avatarURL)
                                .centerCrop()
                                .placeholder(ColorDrawable(Color.GRAY))
                                .into(rivAvatar)
                            tvName.text = result.data.name
                            tvUsername.text = "@${result.data.login}"

                            val titles = listOf(
                                "Followers (${result.data.followers})",
                                "Following (${result.data.following})"
                            )
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
            }
        }
    }

    override fun onDestroy() {
        (activity as MainActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(false)
        }
        super.onDestroy()
    }

}