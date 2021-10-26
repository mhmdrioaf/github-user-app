package com.ananta.githubusersapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ananta.githubusersapp.databinding.FragmentFollowingBinding

class FollowingFragment : Fragment() {

    private lateinit var binding: FragmentFollowingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val layoutManager = LinearLayoutManager(context)
        binding.rvFollowing.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(context, layoutManager.orientation)
        binding.rvFollowing.addItemDecoration(itemDecoration)

        binding.rvFollowing.setHasFixedSize(true)

        val detailViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailViewModel::class.java)

        detailViewModel.findUserFollowing(arguments?.getString(ARG_USER_DATA).toString())

        detailViewModel.isLoading.observe(viewLifecycleOwner, {
            showLoading(it)
        })

        detailViewModel.following.observe(viewLifecycleOwner, { following ->
            if (following != null) {
                setUserFollowing(following)
            }
        })

        detailViewModel.isEmpty.observe(viewLifecycleOwner, { isEmpty ->
            setEmptyFollowing(isEmpty)
        })

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUserFollowing(result: List<PersonItem>) {
        val adapter = PersonAdapter(result)
        binding.rvFollowing.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if(isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setEmptyFollowing(isEmpty: Boolean) {
        if(isEmpty) {
            binding.tvEmptyFollowing.visibility = View.VISIBLE
        } else {
            binding.tvEmptyFollowing.visibility = View.INVISIBLE
        }
    }

    companion object {
        private const val ARG_USER_DATA = "user_data"

        @JvmStatic
        fun newInstance(getUserData: String) =
            FollowingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USER_DATA, getUserData)
                }
            }
    }
}