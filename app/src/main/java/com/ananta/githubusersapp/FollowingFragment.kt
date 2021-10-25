package com.ananta.githubusersapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
=======
import android.widget.ProgressBar
>>>>>>> master
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FollowingFragment : Fragment() {

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        private const val ARG_USER_DATA = "user_data"

        @JvmStatic
        fun newInstance(index: Int, getUserData: String) =
            FollowingFragment().apply {
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

        val view: View = inflater.inflate(R.layout.fragment_following, container, false)

        val layoutManager = LinearLayoutManager(context)
        val rvFollowing = view.findViewById<RecyclerView>(R.id.rvFollowing)
        rvFollowing.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(context, layoutManager.orientation)
        rvFollowing.addItemDecoration(itemDecoration)

        rvFollowing.setHasFixedSize(true)

        val mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        mainViewModel.findUserFollowing(arguments?.getString(ARG_USER_DATA).toString())

<<<<<<< HEAD
=======
        mainViewModel.isLoading.observe(viewLifecycleOwner, {
            showLoading(it)
        })

>>>>>>> master
        mainViewModel.following.observe(viewLifecycleOwner, { following ->
            setUserFollowing(following)
        })

        return view
    }

    private fun setUserFollowing(result: List<PersonItem>) {
        val adapter = PersonAdapter(result)
        val rvFollowing = view?.findViewById<RecyclerView>(R.id.rvFollowing)
        rvFollowing?.adapter = adapter
    }

<<<<<<< HEAD
=======
    private fun showLoading(isLoading: Boolean) {
        val progressBar = view?.findViewById<ProgressBar>(R.id.progressBar)
        if(isLoading) {
            progressBar?.visibility = View.VISIBLE
        } else {
            progressBar?.visibility = View.GONE
        }
    }

>>>>>>> master
}