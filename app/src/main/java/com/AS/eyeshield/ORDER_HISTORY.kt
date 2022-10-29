package com.AS.eyeshield

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.AS.eyeshield.databinding.ActivityOrderHistoryBinding

class ORDER_HISTORY : AppCompatActivity() {
    lateinit var mdb:DBHelper
    lateinit var binding:ActivityOrderHistoryBinding

    lateinit var order_list:ArrayList<OH_MODEL>
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityOrderHistoryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        mdb= DBHelper(this)
        try {



        order_list = ArrayList<OH_MODEL>()
        val cr_order = mdb.getorders
        while (cr_order.moveToNext()) {



            var oh:OH_MODEL= OH_MODEL()
            oh.oh_m_address=cr_order.getString(1)
            oh.oh_m_name=cr_order.getString(3)
            oh.oh_m_phone=cr_order.getString(2)
            oh.oh_m_quantity=cr_order.getString(4)
            oh.oh_m_price=cr_order.getString(5)
            order_list.add(oh)


        }

        val crorders = mdb.getorders

        if (crorders != null) {
            crorders.moveToNext()
            Toast.makeText(this,crorders.getString(0) , Toast.LENGTH_LONG)
        }

        val myorderlistadaper = OH_ADAPTER(this,order_list)
        binding.rv.adapter=myorderlistadaper
        }
        catch (e:Exception){
            Toast.makeText(this,e.message.toString(),Toast.LENGTH_LONG).show()
        }
    }
}