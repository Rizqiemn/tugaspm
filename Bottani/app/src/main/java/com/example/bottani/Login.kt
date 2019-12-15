package com.example.bottani

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    lateinit var ref: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val signup = findViewById<TextView>(R.id.signup)
        val user=findViewById<EditText>(R.id.it_user)
        val pass=findViewById<EditText>(R.id.it_pass)
        val btn=findViewById<Button>(R.id.button)
        ref = FirebaseDatabase.getInstance().getReference("USERS")
        auth = FirebaseAuth.getInstance()

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
            }else{
                    auth.signInWithEmailAndPassword(
                        it_user.text.toString(),
                        it_pass.text.toString()
                    )
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this,"Login Berhasil",Toast.LENGTH_LONG).show()
                                startActivity(Intent(this,MainActivity::class.java))
                            } else {
                                Toast.makeText(this,"Login Gagal",Toast.LENGTH_LONG).show()

                            }
                        }
            }
        }
        signup.setOnClickListener {
            val intent = Intent(this@Login, Signup::class.java)
            startActivity(intent)
        }
    }

    private fun good() {/*
        val uname = it_user.text.toString()
        val pass = it_pass.text.toString()
        val user = Users1(uname,pass)
        val userId = ref.key?.equals(user).toString()
        ref.child("Users").child(userId).setValue(user)
            .addOnSuccessListener {
                Toast.makeText(this,"Login Berhasil!",Toast.LENGTH_LONG).show()
                val intent = Intent(this@Login, MainActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Login Gagal",Toast.LENGTH_LONG).show()
            }*/

    }
}
