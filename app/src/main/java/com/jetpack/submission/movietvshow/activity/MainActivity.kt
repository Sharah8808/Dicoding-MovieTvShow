package com.jetpack.submission.movietvshow.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jetpack.submission.movietvshow.R
import com.jetpack.submission.movietvshow.adapter.SectionPageAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sectionPageAdapter = SectionPageAdapter(this,supportFragmentManager)
        viewPager.adapter = sectionPageAdapter
        mainTab.setupWithViewPager(viewPager)
    }
}