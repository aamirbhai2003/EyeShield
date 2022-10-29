package com.AS.eyeshield

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.AS.eyeshield.databinding.ActivityPrdDetailsBinding
import kotlin.properties.Delegates

class PRD_DETAILS : AppCompatActivity() {
    var np by Delegates.notNull<Int>()
    lateinit var binding: ActivityPrdDetailsBinding

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
        binding=ActivityPrdDetailsBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val i = intent
        val bundle=intent.extras
        val vi = bundle?.getInt("image")
        if (vi != null) {
            binding.imageViewInfo.setImageResource(vi)
        }
        binding.discription.setText(
                "  Weight: 14 gm 0\n" +
                "  Frame Material: Stainless Steel\n" +
                "  Temple Material: Stainless Steel\n" +
                "  Collection: SLEEK STEEL • Frame Size: Medium\n" +
                "  Frame Width: 137 mm\n" +
                "  Frame Dimensions: 53-17-150\n" )
        binding.productName.setText(i.getStringExtra("name"))
        binding.glassprice.setText((i.getStringExtra("price")))



       binding.subquantity.setOnClickListener(View.OnClickListener {
           try {
               var Qty = binding.quantity.text.toString().toInt()
               if (Qty > 1)
               {
                   Qty--
               }
               binding.quantity.setText(Qty.toString())

           }
           catch(exp: Exception)
           {
               Log.i("Error: ", exp.message.toString())
           }
           np=binding.quantity.text.toString().toInt() * binding.glassprice.text.toString().toInt()
//           Toast.makeText(this," net price :$np",Toast.LENGTH_LONG).show()
       })
        binding.addquantity.setOnClickListener(View.OnClickListener {
            try {
                var Qty = binding.quantity.text.toString().toInt()
                Qty++
                binding.quantity.setText(Qty.toString())
            }
            catch(exp: Exception)
            {
                Log.i("Error: ", exp.message.toString())
            }
            np=binding.quantity.text.toString().toInt() * binding.glassprice.text.toString().toInt()
//            Toast.makeText(this," net price :$np",Toast.LENGTH_LONG).show()
        })


        var n=binding.productName.text.toString()


        binding.btn.setOnClickListener(View.OnClickListener {
            np=binding.quantity.text.toString().toInt() * binding.glassprice.text.toString().toInt()
//            Toast.makeText(this," net price :$np",Toast.LENGTH_SHORT).show()

            var i =Intent(this,address::class.java)
            i.putExtra("quantity",binding.quantity.text.toString())
            i.putExtra("net_price",np.toString())
            i.putExtra("product_name",binding.productName.text.toString())
            startActivity(i)
        })



    }

}