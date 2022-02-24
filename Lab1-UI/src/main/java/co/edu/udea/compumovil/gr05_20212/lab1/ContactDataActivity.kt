package co.edu.udea.compumovil.gr05_20212.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import kotlinx.android.synthetic.main.activity_contact_data.*

class ContactDataActivity : AppCompatActivity() {
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
    }

}