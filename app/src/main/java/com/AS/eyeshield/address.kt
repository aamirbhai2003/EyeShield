package com.AS.eyeshield

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.IntegerRes
import androidx.appcompat.app.AlertDialog
import com.AS.eyeshield.databinding.ActivityAddressBinding
import com.AS.eyeshield.summary

class address : AppCompatActivity() {

    lateinit var db_obj:DBHelper
    lateinit var add_summary: summary
    lateinit var binding: ActivityAddressBinding


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

        binding= ActivityAddressBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val i =intent
        binding.etPrdname.setText(i.getStringExtra("product_name"))
        binding.etQty.setText(i.getStringExtra("quantity"))
        binding.etPrice.setText("₹"+i.getStringExtra("net_price"))
        binding.btn.setOnClickListener(View.OnClickListener {
            if (binding.etAddress.text.isEmpty()){
                binding.etAddress.error = "Address is Compulsory"
            }
            if (binding.etPhone.text.isEmpty()){
                binding.etPhone.error="contact is required"
            }
            else{

                try {
                    add_summary= summary(binding.etAddress.text.toString(),
                        binding.etPhone.text.toString(),
                        binding.etPrdname.text.toString(),
                        binding.etQty.text.toString(),
                        binding.etPrice.text.toString())


                    db_obj = DBHelper(this)

                    db_obj.insertadd(add_summary)
//                    Toast.makeText(this, "Address Successfully Saved", Toast.LENGTH_SHORT).show()
                    var intent = Intent(this, place_order::class.java)
                    intent.putExtra("address",binding.etAddress.text.toString())
                    intent.putExtra("quantity",binding.etQty.text.toString())
                    intent.putExtra("phone",binding.etPhone.text.toString())
                    intent.putExtra("net_price",binding.etPrice.text.toString())
                    intent.putExtra("product_name",binding.etPrdname.text.toString())
                    this.startActivity(intent)
                }
                catch (exp:Exception){
                    Toast.makeText(this,exp.message.toString(),Toast.LENGTH_LONG).show()
                }
            }





        })




    }
}