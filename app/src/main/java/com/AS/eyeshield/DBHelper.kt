package com.AS.eyeshield

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class DBHelper(context: Context?) :
    SQLiteOpenHelper(context, "UserData", null, 1) {
    override fun onCreate(DB: SQLiteDatabase) {
        DB.execSQL("create Table UserDetails(userID TEXT primary key,name TEXT,password PASSWORD,number NUMBER)")
        DB.execSQL("create table products (prd_id int primary key,prd_name text,prd_brand text,prd_price int,prd_image int)")
        DB.execSQL("create table summary (add_no int primary key,address text,phone text,prdname text,qty text,price text)")
        loadGlassDetails(DB)

    }

    override fun onUpgrade(DB: SQLiteDatabase, i: Int, i1: Int) {
        DB.execSQL("drop Table if exists UserDetails")
        DB.execSQL("drop Table if exists products")
        DB.execSQL("drop Table if exists summary")
    }
    @SuppressLint("Recycle")
    fun getMaxId(): Int
    {
        var DB = this.writableDatabase
        var Query = "Select Coalesce(max(add_no), 0) from summary"
        var Cursor = DB.rawQuery(Query, null)

        Cursor.moveToFirst()

        var Id = Cursor.getInt(0)
        return  (Id + 1)
    }

    fun insetUserData(name: String?, number: String?, email: String?, password: String?): Boolean {
        val DB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("userID", email)
        contentValues.put("name", name)
        contentValues.put("password", password)
        contentValues.put("number", number)
        val result = DB.insert("UserDetails", null, contentValues)
        return if (result == -1L) {
            false
        } else {
            true
        }
    }

    fun insertadd(insert:summary) {
        try {
            var Query = "Insert into summary values (${getMaxId()}, '${insert.add}', '${insert.phn}', '${insert.prd}', '${insert.qt}','${insert.pr}')"
            var DB = this.writableDatabase
            DB.execSQL(Query)
        }
        catch (exp:Exception){
            Log.i("Error: ", exp.message.toString())
        }
    }


    val data: Cursor
        get() {
            val DB = this.writableDatabase
            return DB.rawQuery("Select * from Userdetails ", null)
        }
    val dataglasses: Cursor
        get() {
            val DB = this.writableDatabase
            return DB.rawQuery("Select * from products ", null)
        }

    fun getglassescnt(): Cursor? {
        val DB = this.writableDatabase
        return DB.rawQuery("Select Count(*) from products ", null)
    }
    val getorders: Cursor
        get() {
            val DB = this.writableDatabase
            return DB.rawQuery("Select * from summary ", null)
        }



    fun loadGlassDetails(DB: SQLiteDatabase) {
        val glassid = arrayOf<String>(
            "1", "2", "3", "4", "5", "6","7","8","9","10","11","12","13","14","15","16",
            "17","18","19","20","21"
        )
        val glassname = arrayOf<String>(
            "ES101", "ES102", "ES103", "ES104", "ES105", "ES106","ES107","ES108","ES109",
            "ES110","ES111","ES112","ES113","ES114","ES115","ES116","ES117","ES118","ES119","ES120","ES121",
        )
        val glassbrand = arrayOf<String>(
            "EYESHEILD", "EYESHEILD", "EYESHEILD", "EYESHEILD", "EYESHEILD", "EYESHEILD", "EYESHEILD", "EYESHEILD", "EYESHELD",
            "EYESHEILD", "EYESHEILD", "EYESHEILD", "EYESHEILD", "EYESHEILD", "EYESHEILD", "EYESHEILD", "EYESHEILD",
            "EYESHEILD", "EYESHEILD", "EYESHEILD", "EYESHEILD"


        )
        val glassprice = arrayOf<String>(
            "629", "399", "649", "999", "289", "829","499","349","789","559","449","199","749","649",
            "249","349","849","699","1049","499","649"
        )

        val glassimage = arrayOf<Int>(
            R.drawable.es9, R.drawable.es8, R.drawable.es5, R.drawable.es7, R.drawable.es10, R.drawable.es11,
            R.drawable.es12, R.drawable.es13, R.drawable.es14, R.drawable.es15, R.drawable.es16, R.drawable.es17,
            R.drawable.es2, R.drawable.es1, R.drawable.es21, R.drawable.es20, R.drawable.es19, R.drawable.es18,
            R.drawable.es3, R.drawable.es4, R.drawable.es6
        )
        // var nm: String? = R.drawable.p1

        for (n in 0..20){

            insertglasses(DB,glassid[n],glassname[n],glassbrand[n],glassprice[n],glassimage[n])

        }

    }













    fun insertglasses(DB: SQLiteDatabase,pid: String?,pname: String?, pprice: String?, pbrand: String?, pimg: Int?  ): Boolean {

        val contentValues = ContentValues()
        contentValues.put("prd_id", pid)
        contentValues.put("prd_name", pname)
        contentValues.put("prd_brand", pbrand)
        contentValues.put("prd_price", pprice)
        contentValues.put("prd_image", pimg)
        val result = DB.insert("products", null, contentValues)
        return if (result == -1L) {
            false
        } else {
            true
        }



    }



}