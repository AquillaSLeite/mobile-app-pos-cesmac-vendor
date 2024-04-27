package com.example.mobileappposcesmacvendor.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobileappposcesmacvendor.R
import com.example.mobileappposcesmacvendor.core.domain.User
import com.example.mobileappposcesmacvendor.core.service.UserService

class HomeActivity : AppCompatActivity() {

    private lateinit var user: User

    private lateinit var usernameTextView: TextView
    private lateinit var balanceTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        user = UserService.retrieveCurrentUser()!!

        usernameTextView = findViewById(R.id.homeTextUsername)
        balanceTextView = findViewById(R.id.homeTextBalance)
    }

    override fun onStart() {
        super.onStart()
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()

        usernameTextView.text = "${user.name}!"
        balanceTextView.text = "R$ ${user.balanceToString()}"
    }
}
