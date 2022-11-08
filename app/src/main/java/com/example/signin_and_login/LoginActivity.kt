package com.example.signin_and_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.signin_and_login.databinding.ActivityLoginBinding
import com.example.signin_and_login.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth= FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener{
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener{
            val email = binding.etEmail.text.toString()
            val password = binding.etPw1.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else{
                    Toast.makeText(this, "패스워드가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "모든 항목을 입력하세요", Toast.LENGTH_SHORT).show()
        }
    }
}
}