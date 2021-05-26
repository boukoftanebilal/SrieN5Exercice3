package com.example.srien5exercice3

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class DbAdapter(var  mCtx: Context, var resource:Int,var items: List<Intervention>): ArrayAdapter<Intervention>(mCtx, resource, items){


    internal var helper = DataBaseHelper(mCtx)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resource, null)

        val intervention : Intervention = items[position]


        val idtv : TextView = view.findViewById(R.id.textView2)
        val plombiertv : TextView = view.findViewById(R.id.textView3)
        val datetv : TextView = view.findViewById(R.id.textView4)
        val typetv : TextView = view.findViewById(R.id.textView5)

        val updatebt : Button = view.findViewById(R.id.button4)
        val deletebt : Button = view.findViewById(R.id.button5)

        idtv.text = intervention.id
        plombiertv.text = intervention.nom
        datetv.text = intervention.date
        typetv.text = intervention.type


        updatebt.setOnClickListener {

        }

        deletebt.setOnClickListener {
            deleteinterv(intervention)
        }

        return view
    }


    fun updateInfo(intervention : Intervention){

        val builder = AlertDialog.Builder(mCtx)
        builder.setTitle("Update Info")

        val inflater : LayoutInflater = LayoutInflater.from(mCtx)
        val view = inflater.inflate(R.layout.update, null)


        val idte : EditText= view.findViewById(R.id.textView)
        val plombierte : EditText = view.findViewById(R.id.textView6)
        val datete : EditText = view.findViewById(R.id.textView7)
        val typete : EditText = view.findViewById(R.id.textView8)


        idte.setText(intervention.id)
        plombierte.setText(intervention.nom)
        datete.setText(intervention.date)
        typete.setText(intervention.type)


        builder.setPositiveButton("Update", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, wich: Int) {

                val isUpdate = helper.updateData(intervention.id, plombierte.text.toString().trim(),
                    datete.text.toString().trim(),
                    typete.text.toString().trim()
                    )
                if (isUpdate)
                    Toast.makeText(mCtx,"done", Toast.LENGTH_LONG).show()
                    else
                    Toast.makeText(mCtx,"not done", Toast.LENGTH_LONG).show()

            }
        })

        builder.setNegativeButton("cancel", object: DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, wich : Int) {

            }
        })

        builder.setView(view)
        val alert = builder.create()
        alert.show()
    }

    fun deleteinterv(intervention: Intervention){
        helper.deleteData(intervention.id)
    }

}