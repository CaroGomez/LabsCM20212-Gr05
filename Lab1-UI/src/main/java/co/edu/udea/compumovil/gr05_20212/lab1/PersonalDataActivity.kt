package co.edu.udea.compumovil.gr05_20212.lab1

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.text.SimpleDateFormat
import java.util.*

class PersonalDataActivity : AppCompatActivity() {
    var cal = Calendar.getInstance()
    var txtDate: TextView?=null
    var btnDate : Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)
        val actionBar = supportActionBar

        loadNextButton();
        loadGradeSpinner();
        actionBar!!.title = "Personal Data"
       /* actionBar.setDisplayHomeAsUpEnabled(true)*/

       // get the references from layout file
         txtDate = findViewById<TextView>(R.id.txtViewDate)
         btnDate = findViewById<Button>(R.id.btnDate)

        txtDate!!.text = "--/--/----"

        // create an OnDateSetListener
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        // when you click on the button, show DatePickerDialog that is set with OnDateSetListener
        btnDate!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@PersonalDataActivity,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }

        })
    }
    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        txtDate!!.text = sdf.format(cal.getTime())
    }

    private fun loadNextButton(){
        val personalDataActv = findViewById<Button>(R.id.personalDataActivityBtn)
        personalDataActv.setOnClickListener{
            val PersonalDataActivityIntent = Intent(this,ContactDataActivity::class.java)
            startActivity(PersonalDataActivityIntent)
        }
    }

    private fun loadGradeSpinner(){
        val spinner: Spinner = findViewById(R.id.gradeSpinner)

        ArrayAdapter.createFromResource(
            this,
            R.array.grades_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinner.adapter = adapter
        }
    }
}