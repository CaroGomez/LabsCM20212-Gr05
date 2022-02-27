package co.edu.udea.compumovil.gr05_20212.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.inputmethod.InputBinding
import android.widget.*
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import kotlinx.android.synthetic.main.activity_contact_data.*


class ContactDataActivity : AppCompatActivity() {
    private lateinit var txtCellphone: EditText
    private lateinit var txtEmail: EditText
    private lateinit var actv_Country: MaterialAutoCompleteTextView
    private lateinit var buttonSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_contact_data)


        val actionBar = supportActionBar
        actionBar!!.title = "Contact Data"
        actionBar.setDisplayHomeAsUpEnabled(true)



        val countries = resources.getStringArray(R.array.countries_array);
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, countries)
        actv_country.setAdapter(adapter)

        val cities = resources.getStringArray(R.array.cities_array);
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_list_item_1, cities)
        actv_City.setAdapter(adapter2)

        txtCellphone=findViewById(R.id.txtCellphone)
        txtEmail=findViewById(R.id.txtEmail)
        actv_Country=findViewById(R.id.actv_country)
        buttonSend=findViewById(R.id.buttonSend)

        buttonSend.setOnClickListener {
            val phone = txtCellphone.text.toString().trim()
            val email = txtEmail.text.toString().trim()
            val country = actv_Country.text.toString().trim()
            val address = txtAddress.text.toString().trim()
            val city = actv_City.text.toString().trim()

            if (phone.isEmpty() || email.isEmpty() || !(Patterns.EMAIL_ADDRESS.matcher(email).matches()) || !resources.getStringArray(R.array.countries_array).contains(country) || country.isEmpty()){
                if (phone.isEmpty()){
                    txtCellphone.error= "Phone required"
                }
                if (email.isEmpty()){
                    txtEmail.error= "Email required"
                }
                else if(!(Patterns.EMAIL_ADDRESS.matcher(email).matches())){
                    txtEmail.error= "Invalid Email"
                }
                if (!resources.getStringArray(R.array.countries_array).contains(country) || country.isEmpty()){
                    actv_Country.error= "Invalid Country"
                }
                Toast.makeText(this, "Validation Unsuccessful", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            else{
                Toast.makeText(this, "Validation Complete", Toast.LENGTH_LONG).show()
                Log.i("Información de Contacto", "")
                Log.i("Teléfono", phone)
                if (address.isEmpty()){
                    Log.i("Dirección", "No ingresada")
                }
                else {
                    Log.i("Dirección", address)
                }
                Log.i("Email", email)
                Log.i("Pais", country)
                if (city.isEmpty()){
                    Log.i("Ciudad", "No ingresada")
                }
                else {
                    Log.i("Ciudad", city)
                }


                return@setOnClickListener
            }

        }
    }

}