package com.example.mobileappposcesmacvendor.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobileappposcesmacvendor.R
import com.example.mobileappposcesmacvendor.core.service.UserService
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    private lateinit var registerButton: Button
    private lateinit var loginButton: Button

    private lateinit var inputEmail: TextInputEditText
    private lateinit var inputPassword: TextInputEditText

    private val editableFactory: Editable.Factory = Editable.Factory.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        registerButton = findViewById(R.id.loginButtonRegister)
        loginButton = findViewById(R.id.loginButtonLogin)

        inputEmail = findViewById(R.id.loginInputEmail)
        inputPassword = findViewById(R.id.loginInputPassword)
    }

    override fun onStart() {
        super.onStart()

        registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        loginButton.setOnClickListener {
            val user = UserService.login(
                email = "aquilla11@hotmail.com", //inputEmail.text.toString().lowercase(),
                password = "123456" //inputPassword.text.toString().lowercase()
            )

            if (user != null) {
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                Toast.makeText(this, "Email or Password is invalid", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        UserService.retrieveCurrentUser()?.let {
            inputEmail.text = editableFactory.newEditable(it.email)
            inputPassword.text = editableFactory.newEditable(it.password)
        }
    }
}