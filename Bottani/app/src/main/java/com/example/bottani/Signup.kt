package com.example.bottani

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signup.*


class Signup: AppCompatActivity() {
    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val nama = findViewById<EditText>(R.id.name)
        val user = findViewById<EditText>(R.id.it_user)
        val pass = findViewById<EditText>(R.id.it_pass)
        ref = FirebaseDatabase.getInstance().getReference("USERS")
        btn_signup.setOnClickListener {
            if (nama.text!!.isEmpty()) {
                Toast.makeText(this,"Kolom tidak boleh kosong", Toast.LENGTH_LONG).show()
                nama.requestFocus()
            } else if (user.text!!.isEmpty()){
                Toast.makeText(this,"Kolom tidak boleh kosong",Toast.LENGTH_LONG).show()
                user.requestFocus()
            }else if(pass.text!!.isEmpty()){
                Toast.makeText(this,"Kolom tidak boleh kosong",Toast.LENGTH_LONG).show()
                pass.requestFocus()
            }else if(pass.text.length <8){
                Toast.makeText(this,"Harap diisi minimal 8 karakter",Toast.LENGTH_LONG).show()
                pass.requestFocus()
            }else {
                savedata()
                val intent = Intent(this@Signup, Login::class.java)
                startActivity(intent)

            }
        }
    }
    private fun savedata() {
        val nama = name.text.toString()
        val uname = it_user.text.toString()
        val pass = it_pass.text.toString()
        val userId = ref.push().key.toString()
        val user = Users(uname,nama,pass)
        ref.child(userId).setValue(user).addOnCompleteListener {
            Toast.makeText(this, "Successs", Toast.LENGTH_SHORT).show()
        }
    }
}

