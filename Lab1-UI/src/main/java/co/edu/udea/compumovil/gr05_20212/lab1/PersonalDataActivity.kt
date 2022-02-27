package co.edu.udea.compumovil.gr05_20212.lab1

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class PersonalDataActivity : AppCompatActivity() {
    var cal: Calendar = Calendar.getInstance()
    var txtDate: TextView? = null
    var btnDate: Button? = null
    lateinit var locale: Locale

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)
        setTitle(R.string.personal_Information)

        loadNextButton()
        loadGradeSpinner()


        // get the references from layout file
        txtDate = findViewById(R.id.txtViewDate)
        btnDate = findViewById(R.id.btnDate)
        val lanspinner: Spinner = findViewById(R.id.languageSpinner)

        txtDate!!.text = "--/--/----"

        val list = ArrayList<String>()
        val selectLang = R.string.select_lang
        list.add(getString(selectLang))
        list.add("English")
        list.add("Español")
        list.add("Português")

        val adapter = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            list
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        lanspinner.adapter = adapter
        lanspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

                when (position) {
                    0 -> {
                    }
                    1 -> setLocale("en")
                    2 -> setLocale("es")
                    3 -> setLocale("pt")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


        // create an OnDateSetListener
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        // when you click on the button, show DatePickerDialog that is set with OnDateSetListener
        btnDate!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(
                    this@PersonalDataActivity,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

        })
    }

    private fun setLocale(localeName: String) {
        locale = Locale(localeName)
        val res = resources
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.locale = locale
        res.updateConfiguration(conf, dm)
        val refresh = Intent(
            this,
            PersonalDataActivity::class.java
        )
        startActivity(refresh)

    }

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        txtDate!!.text = sdf.format(cal.time)
    }


    private fun loadGradeSpinner() {
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


    @SuppressLint("CutPasteId")
    private fun loadNextButton(){

        val txtName = findViewById<TextView>(R.id.txtName)
        val txtLastName = findViewById<TextView>(R.id.txtLastName)
        val btnNext = findViewById<Button>(R.id.personalDataActivityBtn)
        val rbGender = findViewById<RadioGroup>(R.id.radioGroup)
        val dateTxt = findViewById<TextView>(R.id.txtViewDate)

        btnNext.setOnClickListener{
            val name =txtName.text.toString().trim()
            val lastName = txtLastName.text.toString().trim()
            val date = dateTxt.text.toString().trim()
                if(name.isEmpty() || lastName.isEmpty() || date == "--/--/----"){
                    if(name.isEmpty()){
                        txtName.error = "Requerido"
                    }
                    if(lastName.isEmpty()){
                        txtLastName.error = "Requerido"
                    }
                    if (date == "--/--/----"){
                        dateTxt.error = "Requerido"
                    }
                    return@setOnClickListener
                }
                else{
                    val genderSelected = rbGender.checkedRadioButtonId
                    val birthday = findViewById<TextView>(R.id.txtViewDate)
                    val grade = findViewById<Spinner>(R.id.gradeSpinner)
                    val PersonalDataActivityIntent = Intent(this, ContactDataActivity::class.java)
                    val rbGender: String

                    if (genderSelected==-1) {
                        rbGender="No ingresado"
                    }
                    else{
                        rbGender = findViewById<RadioButton>(genderSelected).text.toString()
                    }
                    PersonalDataActivityIntent.putExtra("Nombre", name)
                    PersonalDataActivityIntent.putExtra("Apellido", lastName)
                    PersonalDataActivityIntent.putExtra("Genero", rbGender)
                    PersonalDataActivityIntent.putExtra("Fecha de Nacimiento", birthday.text.toString())
                    PersonalDataActivityIntent.putExtra("Escolaridad", grade.selectedItem.toString())
                    startActivity(PersonalDataActivityIntent)
                }
        }
    }

}