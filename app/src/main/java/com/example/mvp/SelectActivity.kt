package com.example.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SelectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)

        val selectInformation = findViewById<TextView>(R.id.selectInformation)
        val button = findViewById<Button>(R.id.selectButton)

        val person = intent.getParcelableExtra<Person>(KEY_SELECT) ?: return
        val name = person.name
        val age = person.age

        selectInformation.text = "Name: $name \nAge: $age"
        button.setOnClickListener {
            finish()
        }

    }
}