package id.ilhamelmujib.submissionandroidfundamental.ui.detail.follow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import id.ilhamelmujib.submissionandroidfundamental.databinding.FragmentFollowBinding
import id.ilhamelmujib.submissionandroidfundamental.databinding.FragmentUserBinding
import id.ilhamelmujib.submissionandroidfundamental.ui.adapter.UserAdapter
import id.ilhamelmujib.submissionandroidfundamental.ui.user.UserViewModel

class FollowFragment : Fragment() {
    private lateinit var binding: FragmentFollowBinding
    private val viewModel by viewModels<FollowViewModel>()

    fun newInstance(username: String, category: String): Fragment {
        val args = Bundle().apply {
            putString(EXTRA_USERNAME, username)
            putString(EXTRA_CATEGORY, category)
        }
        val fragment = FollowFragment()
        fragment.arguments = args
        return fragment
    }

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_CATEGORY = "extra_category"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val username = arguments?.getString(EXTRA_USERNAME) ?: ""
        val category = arguments?.getString(EXTRA_CATEGORY) ?: ""

        viewModel.followUser(username, category)
        viewModel.user.observe(viewLifecycleOwner) {
            val mAdapter = UserAdapter(it, false)
            binding.rvUser.run {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = mAdapter
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

}