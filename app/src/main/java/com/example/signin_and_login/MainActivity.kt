package com.example.signin_and_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.signin_and_login.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var auth : FirebaseAuth
    lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth= FirebaseAuth.getInstance()


        binding.btnLogin.setOnClickListener{
            val email = binding.etEmail.text.toString()
            val password = binding.etPw1.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent = Intent(this, MypageActivity::class.java) //로그인 성공하면 마이페이지로 넘어감
                        startActivity(intent)
                    } else{
                        Toast.makeText(this, "패스워드가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "모든 항목을 입력하세요", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnRegister.setOnClickListener {

            val intent1 = Intent(this, SignupActivity::class.java) //다음 화면으로 이동하기 위한 인텐트 객체 생성.
            startActivity(intent1)
        }
    }
}

