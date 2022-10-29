package com.AS.eyeshield

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.AS.eyeshield.databinding.ActivityPrdListBinding
import java.io.ByteArrayOutputStream

class PRD_LIST : AppCompatActivity() {

     lateinit var binding: ActivityPrdListBinding
    lateinit var myplantlist: ArrayList<GlassModel>
    lateinit var mdb:DBHelper
    //
    lateinit var db: SQLiteDatabase
    lateinit var c: Cursor
    lateinit var img1: ByteArray
    var name="ES765"
    var brand="EYESHIELD"
    var price="536"

    override fun onBackPressed() {
        finishAffinity()
    }

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
        binding=ActivityPrdListBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)


            mdb= DBHelper(this)

        myplantlist = ArrayList<GlassModel>()
        val cursorplant = mdb.dataglasses
        while (cursorplant.moveToNext()) {
            var pm: GlassModel= GlassModel()
            pm.plantname=cursorplant.getString(1)
            pm.plantprice=cursorplant.getString(2)
            pm.plantimg = cursorplant.getInt(4)
            myplantlist!!.add(pm)
        }

        val cursorplantcnt = mdb.getglassescnt()

        if (cursorplantcnt != null) {
            cursorplantcnt.moveToNext()
            Toast.makeText(this,cursorplantcnt.getString(0) , Toast.LENGTH_LONG)
        }

        val myplantlistadapter = LIST_ADAPTER(this, myplantlist)
        binding.recyclerView.adapter=myplantlistadapter

    }

    fun img_setter(): ByteArray {
        val b = BitmapFactory.decodeResource(resources, R.drawable.plus)
        /*create the object of ByteArrayoutputStream class. Now break the image into the byte parts by calling toByteArray() of ByteOutputStream class and store it in a array */
        val bos = ByteArrayOutputStream()
        b.compress(Bitmap.CompressFormat.PNG, 100, bos)
        val img = bos.toByteArray()
        return img
    }
    fun ins_get_prds(){
        mdb = DBHelper(this)

        val b = BitmapFactory.decodeResource(resources, R.drawable.logo)
        /*create the object of ByteArrayoutputStream class. Now break the image into the byte parts by calling toByteArray() of ByteOutputStream class and store it in a array */
        val bos = ByteArrayOutputStream()
        b.compress(Bitmap.CompressFormat.PNG, 100, bos)
        val img = bos.toByteArray()



        /*to write in a database call the getWritableDatabase method */
        db = mdb.writableDatabase
        val cv = ContentValues()
        cv.put("prd_image", img)
        cv.put("prd_name",name)
        cv.put("prd_id",5)
        cv.put("prd_price",name)

        db.insert("products", null, cv)
        val selectQuery = "SELECT * FROM products"
        c = db.rawQuery(selectQuery, null)
        if (c != null) {
            c!!.moveToFirst()
            do {
                img1 = c!!.getBlob(4)
                val gname = c!!.getString(1)
                val gbrand = c!!.getString(2)
            } while (c!!.moveToNext())
        }

        val b1 = BitmapFactory.decodeByteArray(img1, 0, img1.size)
//        binding.pImage.setImageBitmap(b1)
//        binding.pName!!.text = name
//        binding.pBrand!!.text = brand



    }
}