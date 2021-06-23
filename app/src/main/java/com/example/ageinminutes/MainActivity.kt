package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener { view->
            clickDatePicker(view)
        }
    }


    fun clickDatePicker(view: View){
        val myCalander = Calendar.getInstance()
        val year = myCalander.get(Calendar.YEAR)
        val month = myCalander.get(Calendar.MONTH)
        val day = myCalander.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{
                view, selectedyear , selectedmonth , selecteddayofmonth ->

                val selecteddate = "$selecteddayofmonth/${selectedmonth+1}/$selectedyear"

                val tvselecteddate = findViewById<TextView>(R.id.tvSelectedDate)
                tvselecteddate.setText(selecteddate)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val thedate = sdf.parse(selecteddate)

                val selecteddateinminutes = thedate!!.time / 60000

                val currentdate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentdateinminutes = currentdate!!.time / 60000

                val differenceinminutes = currentdateinminutes - selecteddateinminutes

                val tvselecteddateinminutes = findViewById<TextView>(R.id.tvSelectedDateInMinutes)

                tvselecteddateinminutes.setText(differenceinminutes.toString())
        },year
        ,month
        ,day)

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()

    }

}