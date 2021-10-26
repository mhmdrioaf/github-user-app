package com.ananta.githubusersapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ananta.githubusersapp.databinding.FragmentFollowerBinding

class FollowerFragment : Fragment() {

    private lateinit var binding: FragmentFollowerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val layoutManager = LinearLayoutManager(context)
        binding.rvFollowers.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(context, layoutManager.orientation)
        binding.rvFollowers.addItemDecoration(itemDecoration)

        binding.rvFollowers.setHasFixedSize(true)

        val detailViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailViewModel::class.java)


        detailViewModel.findUserFollowers(arguments?.get(ARG_USER_DATA).toString())

        detailViewModel.isLoading.observe(viewLifecycleOwner, {
            showLoading(it)
        })

        detailViewModel.followers.observe(viewLifecycleOwner, { followers ->
            if (followers != null) {
                setUserFollowers(followers)
            }
        })

        detailViewModel.isEmpty.observe(viewLifecycleOwner, { isEmpty ->
            setEmptyFollowers(isEmpty)
        })

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUserFollowers(result: List<PersonItem>) {
        val adapter = PersonAdapter(result)
        binding.rvFollowers.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if(isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setEmptyFollowers(isEmpty: Boolean) {
        if(isEmpty) {
            binding.tvEmptyFollowers.visibility = View.VISIBLE
        } else {
            binding.tvEmptyFollowers.visibility = View.INVISIBLE
        }
    }

    companion object {
        private const val ARG_USER_DATA = "user_data"

        @JvmStatic
        fun newInstance(getUserData: String) =
            FollowerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USER_DATA, getUserData)
                }
            }
    }
}