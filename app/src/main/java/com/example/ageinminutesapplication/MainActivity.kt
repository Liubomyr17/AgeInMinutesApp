package com.example.ageinminutesapplication

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnDatePicker)?.setOnClickListener {view ->
            clickDatePicker(view)
        }
    }
    fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this, "The chosen year is $selectedYear, the month is $selectedMonth, and the day is $selectedDayOfMonth.", Toast.LENGTH_LONG).show()
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                findViewById<TextView>(R.id.tvSelectedDate)?.text = selectedDate
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                val selectedDateInMinutes = theDate!!.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes= currentDate!!.time / 60000
                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                findViewById<TextView>(R.id.tvSelectedDateInMinutes)?.text = differenceInMinutes.toString()
            },
                    year,
                    month,
                    day)
        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
        }
    }




