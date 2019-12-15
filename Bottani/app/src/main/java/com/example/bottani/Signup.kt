package com.example.bottani

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signup.*


class Signup: AppCompatActivity() {
    lateinit var ref: DatabaseReference
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val nama = findViewById<EditText>(R.id.name)
        val user = findViewById<EditText>(R.id.it_user)
        val pass = findViewById<EditText>(R.id.it_pass)
        ref = FirebaseDatabase.getInstance().getReference("USERS")
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
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
            }/*else{
                savedata()
                val intent = Intent(this@Signup, Login::class.java)
                startActivity(intent)

            }*/
            auth.createUserWithEmailAndPassword(it_user.text.toString(), it_pass.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this,Login::class.java))
                        finish()
                    } else {
                        Toast.makeText(baseContext, "Sign Up failed. Try again after some time.",Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            Toast.makeText(this, "U Signed In successfully", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, Login::class.java))
        } else {
            Toast.makeText(this, "U Didnt signed in", Toast.LENGTH_LONG).show()
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

