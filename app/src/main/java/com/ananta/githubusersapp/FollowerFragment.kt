package com.ananta.githubusersapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FollowerFragment : Fragment() {

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        private const val ARG_USER_DATA = "user_data"

        @JvmStatic
        fun newInstance(index: Int, getUserData: String) =
            FollowerFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, index)
                    putString(ARG_USER_DATA, getUserData)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_follower, container, false)

        val layoutManager = LinearLayoutManager(context)
        val rvFollowers = view.findViewById<RecyclerView>(R.id.rvFollowers)
        rvFollowers.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(context, layoutManager.orientation)
        rvFollowers.addItemDecoration(itemDecoration)

        rvFollowers.setHasFixedSize(true)

        val mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)


        mainViewModel.findUserFollowers(arguments?.get(ARG_USER_DATA).toString())

        mainViewModel.followers.observe(viewLifecycleOwner, { followers ->
            setUserFollowers(followers)
        })

        return view
    }

    private fun setUserFollowers(result: List<PersonItem>) {
        val adapter = PersonAdapter(result)
        val rvFollowers = view?.findViewById<RecyclerView>(R.id.rvFollowers)
        rvFollowers?.adapter = adapter
    }
}