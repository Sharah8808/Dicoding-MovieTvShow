package com.jetpack.submission.movietvshow.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.jetpack.submission.movietvshow.R
import com.jetpack.submission.movietvshow.fragments.MovieFragment
import com.jetpack.submission.movietvshow.fragments.TvShowFragment

class SectionPageAdapter(private val mContext: Context, fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    private val tabTitle = intArrayOf(
        R.string.movie,
        R.string.tv,
    )

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = MovieFragment()
            1 -> fragment = TvShowFragment()
        }

        return  fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence?{
        return mContext.resources.getString(tabTitle[position])
    }

}