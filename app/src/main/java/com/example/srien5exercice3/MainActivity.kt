package com.example.srien5exercice3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var listview: ListView
    internal var helper= DataBaseHelper(this)
    var list = mutableListOf<Intervention>()
    lateinit var btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button.setOnClickListener {
            var intent = Intent(this, InsertActivity::class.java)
            startActivity(intent)
        }




        listview = findViewById(R.id.listview1)
        btn= findViewById(R.id.button3)

        viewAll()

        val adapter = DbAdapter(this, R.layout.intervention, list)
        listview.adapter = adapter
        btn.setOnClickListener {
            viewAll()
            adapter.notifyDataSetChanged()
        }
    }

    private fun viewAll(){
        list.clear()
        val res = helper.allData
        if (res.count == 0){
            Toast.makeText(this, "no records", Toast.LENGTH_LONG).show()
        }
        while (res.moveToNext()){
            list.add(
                Intervention(
                res.getString(0),res.getString(1), res.getString(3), res.getString(4)
            )
            )
        }
    }

}
