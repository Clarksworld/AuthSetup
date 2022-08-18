package com.exchange.userauth.launcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.exchange.userauth.MainActivity
import com.exchange.userauth.R
import com.exchange.userauth.data.UserPreferences
import com.exchange.userauth.ui.home.HomeActivity
import com.exchange.userauth.ui.startNewActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val userPreferences = UserPreferences(this)

        userPreferences.authToken.asLiveData().observe(this, Observer {

            val activity = if (it == null) MainActivity::class.java else HomeActivity::class.java
            startNewActivity(activity)

        })
    }
}