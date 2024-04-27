package com.example.mobileappposcesmacvendor.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobileappposcesmacvendor.R
import com.example.mobileappposcesmacvendor.core.domain.User
import com.example.mobileappposcesmacvendor.core.service.UserService
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {

    private lateinit var createAccountButton: Button
    private lateinit var cancelButton: Button

    private lateinit var inputEmail: TextInputEditText
    private lateinit var inputName: TextInputEditText
    private lateinit var inputPassword: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        createAccountButton = findViewById(R.id.registerButtonCreateAccount)
        cancelButton = findViewById(R.id.registerButtonCancel)

        inputEmail = findViewById(R.id.registerInputEmail)
        inputName = findViewById(R.id.registerInputName)
        inputPassword = findViewById(R.id.registerInputPassword)
    }

    override fun onStart() {
        super.onStart()

        createAccountButton.setOnClickListener {
            val user = User(
                email = inputEmail.text.toString().lowercase(),
                name = inputName.text.toString().lowercase(),
                password = inputPassword.text.toString().lowercase()
            )

            if (UserService.findUserByEmail(user.email)) {
                Toast.makeText(this, "email already registered", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            UserService.register(user)

            startActivity(Intent(this, LoginActivity::class.java))
        }

        cancelButton.setOnClickListener { finish() }
    }
}