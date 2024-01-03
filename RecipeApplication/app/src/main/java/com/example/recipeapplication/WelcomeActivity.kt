package com.example.recipeapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.recipeapplication.ExploreActivity // Replace with your actual ExploreActivity

class WelcomeActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 2000 // 2 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome) // Create a layout file for your Welcome Activity

        Handler(Looper.getMainLooper()).postDelayed({
            // Start your main activity after the splash time out
            val intent = Intent(this, ExploreActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT)
    }
}
