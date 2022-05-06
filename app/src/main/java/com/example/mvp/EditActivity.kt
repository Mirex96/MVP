package com.example.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText


class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val name = findViewById<EditText>(R.id.editTextName)
        val age = findViewById<EditText>(R.id.editTextAge)
        val button = findViewById<Button>(R.id.editButton)


        val person = intent.getParcelableExtra<Person>(EDIT_KEY) ?: return
        name.setText(person.name)
        age.setText(person.age)

        button.setOnClickListener {
            val editPerson = Person(
                person.id,
                name.text.toString(),
                age.text.toString()
            )
            intent.putExtra(EDIT_KEY, editPerson)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}