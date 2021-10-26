package com.ananta.githubusersapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.ananta.githubusersapp.databinding.ActivityUserDetailBinding
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class UserDetailActivity : AppCompatActivity() {

    private lateinit var activityDetailBinding: ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailBinding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(activityDetailBinding.root)


        supportActionBar?.elevation = 0f


        val detailViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailViewModel::class.java)

        detailViewModel.isLoading.observe(this, {
            showLoading(it)
        })

        detailViewModel.detail.observe(this, { detail ->
            if (detail != null) {
                setUserDetail(detail)
            }
        })

        val username = intent.getStringExtra(EXTRA_USERNAME)
        detailViewModel.findUserDetail(username.toString())

        supportActionBar?.title = username

        val sectionsPagerAdapter = SectionsPagerAdapter(
            this@UserDetailActivity,
            username.toString()
        )
        val viewPager: ViewPager2 = activityDetailBinding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = activityDetailBinding.tabLayout
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

    }


    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            activityDetailBinding.progressBarDetail.visibility = View.VISIBLE
        } else {
            activityDetailBinding.progressBarDetail.visibility = View.GONE
        }
    }

    private fun setUserDetail(detail: UserResponse) {
        activityDetailBinding.tvName.text = detail.name
        activityDetailBinding.tvCompany.text = detail.company
        activityDetailBinding.tvLocation.text = detail.location
        activityDetailBinding.tvUsername.text = detail.login
        Glide.with(this)
            .load(detail.avatarUrl)
            .into(activityDetailBinding.profilePicture)
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2,
        )

        const val EXTRA_USERNAME = "username"
    }

}