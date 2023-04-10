package id.ilhamelmujib.submissionandroidfundamental.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import id.ilhamelmujib.submissionandroidfundamental.databinding.FragmentUserBinding
import id.ilhamelmujib.submissionandroidfundamental.ui.adapter.UserAdapter

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private val viewModel by viewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        initObserver()
    }

    private fun initView(){
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

    private fun initObserver(){
        viewModel.searchUser("a")
        viewModel.user.observe(viewLifecycleOwner) {
            val mAdapter = UserAdapter(it)
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