package id.ilhamelmujib.submissionandroidfundamental.ui.detail.follow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import id.ilhamelmujib.submissionandroidfundamental.data.Result
import id.ilhamelmujib.submissionandroidfundamental.databinding.FragmentFollowBinding

class FollowFragment : Fragment() {
    private lateinit var binding: FragmentFollowBinding
    private val viewModel: FollowViewModel by viewModels<FollowViewModel>() {
        FollowViewModelFactory.getInstance(requireContext())
    }

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

        viewModel.followUser(username, category).observe(viewLifecycleOwner){result->
            if (result != null){
                binding.run {
                    when (result) {
                        is Result.Loading -> progressBar.visibility = View.VISIBLE
                        is Result.Error -> {
                            progressBar.visibility = View.GONE
                            Toast.makeText(
                                context, "Terjadi kesalahan" + result.error, Toast.LENGTH_SHORT
                            ).show()
                        }
                        is Result.Success -> {
                            progressBar.visibility = View.GONE
                            val mAdapter = FollowAdapter(result.data)
                            rvFollow.run {
                                layoutManager = LinearLayoutManager(requireContext())
                                adapter = mAdapter
                            }
                        }

                    }
                }
            }
        }

    }

}