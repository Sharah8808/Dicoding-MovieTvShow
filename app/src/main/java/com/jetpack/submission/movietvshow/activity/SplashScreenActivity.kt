package com.jetpack.submission.movietvshow.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.jetpack.submission.movietvshow.R

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var handler : Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()
        handler = Handler(mainLooper)
        handler.postDelayed({
            val intent  = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}