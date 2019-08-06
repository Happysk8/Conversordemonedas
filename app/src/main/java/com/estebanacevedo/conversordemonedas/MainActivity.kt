package com.estebanacevedo.conversordemonedas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var Flag: String = ""
    private var Flag2: String = ""
    lateinit var currency: String
    lateinit var currency2: String
    private var InCoins: Float  = 1.0F
    private var OutCoins: Float  = 1.0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currencies = arrayOf("Dolar", "Peso", "Euro")
        val adapter2 = ArrayAdapter(
            this, // Context
            android.R.layout.simple_spinner_item, // Layout
            currencies // Array
        )
        val adapter = ArrayAdapter(
            this, // Context
            android.R.layout.simple_spinner_item, // Layout
            currencies // Array
        )

        //setContentView(android.R.layout.simple_dropdown_item_1line)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        origenspinner.adapter = adapter

        origenspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Display the selected item text on text view
                //text_view.text = "Ciudad ${parent.getItemAtPosition(position).toString()}"
                currency = parent.getItemAtPosition(position).toString()
                Flag = when {
                    currency == "Peso" -> flagorigen.background =
                        ActivityCompat.getDrawable(this@MainActivity, R.drawable.colombia)
                    currency == "Euro" -> flagorigen.background =
                        ActivityCompat.getDrawable(this@MainActivity, R.drawable.euro)
                    currency == "Dolar" -> flagorigen.background =
                        ActivityCompat.getDrawable(this@MainActivity, R.drawable.usa)
                    else -> flagorigen.background =
                        ActivityCompat.getDrawable(this@MainActivity, R.drawable.ic_launcher_foreground)
                }.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }

        //val currencies = arrayOf("Dolar","Peso","Euro")


        //setContentView(android.R.layout.simple_dropdown_item_1line)
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        destinospinner.adapter = adapter2

        destinospinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Display the selected item text on text view
                //text_view.text = "Ciudad ${parent.getItemAtPosition(position).toString()}"
                currency2 = parent.getItemAtPosition(position).toString()
                Flag2 = when {
                    currency2 == "Peso" -> flagdestino.background =
                        ActivityCompat.getDrawable(this@MainActivity, R.drawable.colombia)
                    currency2 == "Euro" -> flagdestino.background =
                        ActivityCompat.getDrawable(this@MainActivity, R.drawable.euro)
                    currency2 == "Dolar" -> flagdestino.background =
                        ActivityCompat.getDrawable(this@MainActivity, R.drawable.usa)
                    else -> flagorigen.background =
                        ActivityCompat.getDrawable(this@MainActivity, R.drawable.ic_launcher_foreground)
                }.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
        btconvert.setOnClickListener {
            InCoins = edin.text.toString().toFloat()
            OutCoins = when {

                currency == "Peso" && currency2 == "Dolar" -> InCoins / 3473
                currency == "Peso" && currency2 == "Euro" -> InCoins / 3770
                currency == "Peso" && currency2 == "Peso" -> InCoins
                currency == "Dolar" && currency2 == "Euro" -> InCoins / 1.12F
                currency == "Dolar" && currency2 == "Peso" -> InCoins * 3473
                currency == "Euro" && currency2 == "Dolar" -> InCoins * 1.12F
                currency == "Euro" && currency2 == "Peso" -> InCoins * 3770
                currency == "Euro" && currency2 == "Euro" -> InCoins
                currency == "Dolar" && currency2 == "Dolar" -> InCoins
                else -> 0.0F
            }
            txout.text = OutCoins.toString()
        }
    }


}

