package com.AS.eyeshield

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.AS.eyeshield.databinding.ActivityPlaceOrderBinding

class place_order : AppCompatActivity() {
    lateinit var binding: ActivityPlaceOrderBinding
    private val permissionRequest = 101
    lateinit var db:DBHelper


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.rtc,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.oh->{
                Toast.makeText(this,"clicked order history",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,ORDER_HISTORY::class.java))

            }
        }
        return true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityPlaceOrderBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        db=DBHelper(this)
        var cr=db.data
        var name:String
        if(cr.moveToFirst()){
            name=cr.getString(1).toString()
            binding.name.setText(name)
//            Toast.makeText(this," name: $name",Toast.LENGTH_LONG).show()
        }

        name=cr.getString(1).toString()


        var i=intent
        binding.address.setText(i.getStringExtra("address"))
        binding.phone.setText(i.getStringExtra("phone"))
        binding.price.setText(i.getStringExtra("net_price"))
        val msg="ONE ORDER RECIEVED FOR :"+"\n"+
                "PRODUCT NAME:"+i.getStringExtra("product_name").toString()+"\n"+
                "QUANTITY:"+i.getStringExtra("quantity").toString()+"\n"+
                "PRICE:"+i.getStringExtra("net_price").toString()+"\n"+
                "CUSTOMER NAME:$name"
                "CUSTOMER PHONE:"+i.getStringExtra("phone").toString()+"\n"+
                "DELIVERY ADDRESS :"+i.getStringExtra("address")

        
        binding.btnPo.setOnClickListener(View.OnClickListener {

            myMessage(9327654111,msg)

        })


        fun sendSMS() {
            val uri = Uri.parse("smsto:9409419422")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "Here goes your message...")
            startActivity(intent) }

    }
    fun sendMessage(view: View) {
//        val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
//        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
//            myMessage()
//        } else {
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS),
//                permissionRequest)
//        }
    }
    private fun myMessage(myNumber: Long, myMsg: String) {



        if (myNumber.equals(0) || myMsg == "") {
            Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_SHORT).show()
        } else {
            if (TextUtils.isDigitsOnly(myNumber.toString())) {
                val smsManager: SmsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(myNumber.toString(), null, myMsg, null, null)
                Toast.makeText(this, "PLACED ORDER", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,PRD_LIST::class.java))
            } else {
                Toast.makeText(this, "Please enter the correct number", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults:
    IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == permissionRequest) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                myMessage(9327654111,"mesg");
            } else {
                Toast.makeText(this, "You don't have required permission to send a message",
                    Toast.LENGTH_SHORT).show();
            }
        }
    }

}