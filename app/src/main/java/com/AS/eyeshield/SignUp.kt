package com.AS.eyeshield

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class SignUp : AppCompatActivity() {
    var name: EditText? = null
    var number: EditText? = null
    var email: EditText? = null
    var pass: EditText? = null
    var login: TextView? = null
    var dbHelper: DBHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)
        val name = findViewById<EditText>(R.id.textName)
        val number = findViewById<EditText>(R.id.textNumber)
        val email = findViewById<EditText>(R.id.textEmail)
        val pass = findViewById<EditText>(R.id.textPass)
        val signUpAcc = findViewById<Button>(R.id.btnSignUpAcc)
        val et_cnpass=findViewById<EditText>(R.id.et_cnpass)
        dbHelper = DBHelper(this)




        signUpAcc.setOnClickListener {

            val name1 = name.getText().toString()
            val number1 = number.getText().toString()
            val email1 = email.getText().toString()
            val pass1 = pass.getText().toString()

            if (name!!.length() == 0) {
                name!!.error = "This field is required"
            }
            if (number!!.length() == 0) {
                number!!.error = "This field is required"
            }
             if (email!!.length() == 0) {
                email!!.error = "This field is required"
            }
            if (pass!!.length() == 0) {
                pass!!.error = "This field is required"
            }
            if ((!et_cnpass.text.equals(pass.text)) && (et_cnpass!!.length()==0)){
                et_cnpass!!.error="Password Missmatch"
            }
            else{
                val b = dbHelper!!.insetUserData(name1, number1, email1, pass1)

                if (b) {
                    Toast.makeText(this, "REGISTRATION SUCCESSFULL", Toast.LENGTH_SHORT).show()

                    startActivity(Intent(this,Login::class.java))
                }
                else {

                    Toast.makeText(this, "ERROR IN REGISTRATION", Toast.LENGTH_SHORT).show()
                    val alert=AlertDialog.Builder(this)
                    alert.setTitle(R.string.dialogTitle)
                    alert.setMessage(R.string.dialogMessage)
                    alert.setNeutralButton("OK"){
                            dialogInterface,which->
                        //alert dialog natthi
                    }
                    alert.show()
                }

            }

        }
        val login = findViewById<TextView>(R.id.loginAcc)
        login.setOnClickListener(View.OnClickListener {
            val i = Intent(this, Login::class.java)
            startActivity(i)
        })



    }
}