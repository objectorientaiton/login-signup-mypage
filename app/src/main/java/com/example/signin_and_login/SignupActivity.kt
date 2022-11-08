package com.example.signin_and_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.signin_and_login.databinding.ActivityMypageBinding
import com.example.signin_and_login.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
     lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener{
            val email = binding.etEmail.text.toString()
            val password = binding.etPw1.text.toString()
            val confirmPW = binding.etPw2.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty() && confirmPW.isNotEmpty()){
                if(password == (confirmPW)){
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                        if(it.isSuccessful){
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)

                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else{
                    Toast.makeText(this, "패스워드가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this, "모든 항목을 입력하세요", Toast.LENGTH_SHORT).show()
            }
        }
    }
}