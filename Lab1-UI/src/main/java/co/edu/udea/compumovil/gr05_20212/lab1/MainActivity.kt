package co.edu.udea.compumovil.gr05_20212.lab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val personalDataActv = findViewById<Button>(R.id.personalDataActivityBtn)
        personalDataActv.setOnClickListener{
            val PersonalDataActivityIntent = Intent(this,ContactDataActivity::class.java)
            startActivity(PersonalDataActivityIntent)
        }
    }
}