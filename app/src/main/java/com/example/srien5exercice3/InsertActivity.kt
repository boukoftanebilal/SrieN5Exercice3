package com.example.srien5exercice3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_insert.*

class InsertActivity : AppCompatActivity() {

    var helper = DataBaseHelper(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

          button2.setOnClickListener {
              helper.insertData(editText2.text.toString(), editText3.text.toString(), editText4.text.toString())
              Toast.makeText(this, "Inséré", Toast.LENGTH_LONG).show()
          }
    }
}
