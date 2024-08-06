package com.project.myapplication

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var categorySpinner: Spinner
    private lateinit var showQuoteButton: Button
    private lateinit var quoteTextView: TextView

    private val quotes = mapOf(
        "Inspirational" to listOf(
            "The best way to get started is to quit talking and begin doing.",
            "The pessimist sees difficulty in every opportunity. The optimist sees opportunity in every difficulty.",
            "Don't let yesterday take up too much of today."
        ),
        "Life" to listOf(
            "Life is what happens when you're busy making other plans.",
            "Get busy living or get busy dying.",
            "You only live once, but if you do it right, once is enough."
        ),
        "Success" to listOf(
            "Success is not final; failure is not fatal: It is the courage to continue that counts.",
            "Success usually comes to those who are too busy to be looking for it.",
            "Don't be afraid to give up the good to go for the great."
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        categorySpinner = findViewById(R.id.categorySpinner)
        showQuoteButton = findViewById(R.id.showQuoteButton)
        quoteTextView = findViewById(R.id.quoteTextView)

        // Spinner setup
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.quote_categories,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = adapter

        // Spinner Listener
        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedCategory = parent.getItemAtPosition(position).toString()
                Toast.makeText(this@MainActivity, "Selected: $selectedCategory", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        // Button Listener
        showQuoteButton.setOnClickListener {
            val selectedCategory = categorySpinner.selectedItem.toString()
            val quoteList = quotes[selectedCategory]
            val randomQuote = quoteList?.random()
            quoteTextView.text = randomQuote
        }
    }
}
