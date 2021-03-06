package co.edu.udea.compumovil.gr05_20212.lab1

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
        setTitle(R.string.contact_Information)
        val countries = resources.getStringArray(R.array.countries_array)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, countries)
        actv_country.setAdapter(adapter)
        val cities = resources.getStringArray(R.array.cities_array)
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
                Log.i("Informaci??n Personal", "")
                val objetoIntent: android.content.Intent=intent
                val Nombre=objetoIntent.getStringExtra("Nombre")
                val Apellido=objetoIntent.getStringExtra("Apellido")
                val Genero=objetoIntent.getStringExtra("Genero")
                val Nacimiento=objetoIntent.getStringExtra("Fecha de Nacimiento")
                val Escolaridad=objetoIntent.getStringExtra("Escolaridad")
                Log.i("Nombre", "$Nombre")
                Log.i("Apellido", "$Apellido")
                Log.i("Genero", "$Genero")
                Log.i("Fecha de nacimiento", "$Nacimiento")
                Log.i("Escolaridad", "$Escolaridad")
                Log.i("Informaci??n de Contacto", "")
                Log.i("Tel??fono", phone)
                if (address.isEmpty()){
                    Log.i("Direcci??n", "No ingresada")
                }
                else {
                    Log.i("Direcci??n", address)
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