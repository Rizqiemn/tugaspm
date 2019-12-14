package com.example.bottani

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val signup = findViewById<TextView>(R.id.signup)
        val user=findViewById<EditText>(R.id.it_user)
        val pass=findViewById<EditText>(R.id.it_pass)
        val btn=findViewById<Button>(R.id.button)


        btn.setOnClickListener{
            if (user.text!!.isEmpty()){
                Toast.makeText(this,"Kolom tidak boleh kosong",Toast.LENGTH_LONG).show()
                user.requestFocus()
            }else if(pass.text!!.isEmpty()){
                Toast.makeText(this,"Kolom tidak boleh kosong",Toast.LENGTH_LONG).show()
                pass.requestFocus()
            }else if(pass.text.length <8){
                Toast.makeText(this,"Harap diisi minimal 8 karakter",Toast.LENGTH_LONG).show()
                pass.requestFocus()
            }else {
                Toast.makeText(this,"Login Berhasil!",Toast.LENGTH_LONG).show()
                //val intent = Intent(this@Login, MainActivity::class.java)
                //startActivity(intent)
            }
        }
        signup.setOnClickListener {
            val intent = Intent(this@Login, Signup::class.java)
            startActivity(intent)
        }
    }
}
