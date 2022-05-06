package com.example.mvp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val addName = findViewById<EditText>(R.id.addName)
        val addAge = findViewById<EditText>(R.id.addAge)
        val addButton = findViewById<Button>(R.id.addButton)


        addButton.setOnClickListener {
            val intent = Intent()
            val addPerson = Person(
                IdGenerator.generatorId(),
                addName.text.toString(),
                addAge.text.toString()
            )
            intent.putExtra(KEY_ADD, addPerson)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}