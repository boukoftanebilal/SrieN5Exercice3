package com.example.srien5exercice3

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.security.AccessControlContext

class DataBaseHelper (context :Context): SQLiteOpenHelper(context,DB_NAME, null, 1){
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("CREATE TABLE $TB_NAME (ID INTEGER)")
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("DROP TABLE IF EXISTS" + TB_NAME)
    }


    fun insertData(nom : String, date: String, type: String){
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_2, nom)
        contentValues.put(COL_3, date)
        contentValues.put(COL_4, type)
        db.insert(TB_NAME, null, contentValues)
    }

    val allData : Cursor
    get() {
        val db = this.writableDatabase
        val res = db.rawQuery("SELECT * FROM" + TB_NAME, null)
        return  res
    }

    fun updateData(id: String, nom : String, date: String, type: String): Boolean{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_1, id)
        contentValues.put(COL_2, nom)
        contentValues.put(COL_3, date)
        contentValues.put(COL_4, type)
        db.update(TB_NAME, contentValues, "ID = ?", arrayOf(id))
        return true
    }

    fun deleteData(id: String): Int{
        val db = this.writableDatabase
        return  db.delete(TB_NAME, "ID = ?", arrayOf(id))
    }



    companion object {
        val DB_NAME = "EXO3.db"
        val TB_NAME = "TB"
        val COL_1 = "ID"
        val COL_2 = "NAME"
        val COL_3 = "DATE"
        val COL_4 = "TYPE"
    }
}