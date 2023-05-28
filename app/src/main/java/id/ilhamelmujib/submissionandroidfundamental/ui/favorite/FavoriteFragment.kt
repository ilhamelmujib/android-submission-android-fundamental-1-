package id.ilhamelmujib.submissionandroidfundamental.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import id.ilhamelmujib.submissionandroidfundamental.data.local.entity.UserEntity
import id.ilhamelmujib.submissionandroidfundamental.databinding.FragmentFavoriteBinding
import id.ilhamelmujib.submissionandroidfundamental.ui.MainActivity

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels {
        FavoriteViewModelFactory.getInstance(requireContext())
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
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getFavorite().observe(viewLifecycleOwner) {
            val mAdapter = FavoriteAdapter(it, object : FavoriteAdapter.OnClickListener {
                override fun onItemClick(user: UserEntity) {
                    val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(user)
                    findNavController().navigate(action)
                }
                override fun onDeleteClick(id: Long) {
                    viewModel.deleteFavorite(id)
                }
            })
            binding.rvFavorite.run {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = mAdapter
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