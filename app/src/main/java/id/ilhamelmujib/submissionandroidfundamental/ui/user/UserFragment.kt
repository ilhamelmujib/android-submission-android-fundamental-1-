package id.ilhamelmujib.submissionandroidfundamental.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import id.ilhamelmujib.submissionandroidfundamental.databinding.FragmentUserBinding
import id.ilhamelmujib.submissionandroidfundamental.data.Result
import id.ilhamelmujib.submissionandroidfundamental.data.local.entity.UserEntity

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private val viewModel: UserViewModel by viewModels {
        UserViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        initObserver()
    }

    private fun initView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                binding.searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchUser(newText)
                return false
            }
        })
    }

    private fun initObserver() {
        viewModel.searchUser("a").observe(viewLifecycleOwner) { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Result.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(
                            context, "Terjadi kesalahan" + result.error, Toast.LENGTH_SHORT
                        ).show()
                    }
                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val mAdapter =
                            UserAdapter(result.data, object : UserAdapter.OnClickListener {
                                override fun onItemClick(user: UserEntity) {
                                    val action = UserFragmentDirections.actionUserFragmentToDetailFragment(user)
                                    findNavController().navigate(action)
                                }

                                override fun onFavoriteClick(user: UserEntity) {
                                    viewModel.setFavorite(user)
                                }
                            })
                        binding.rvUser.run {
                            layoutManager = LinearLayoutManager(requireContext())
                            adapter = mAdapter
                        }
                    }
                }
            }
        }
    }
}