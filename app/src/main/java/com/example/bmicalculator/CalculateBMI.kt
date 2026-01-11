package com.example.bmicalculator

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculateBMI : AppCompatActivity() {
    private lateinit var spf : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor
    private lateinit var inWeight : EditText
    private lateinit var inHeight : EditText
    private lateinit var bKalkulasi : Button
    private lateinit var bmiIndex : TextView
    private lateinit var bmiResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate_bmi)

        spf = getSharedPreferences("dataKu", MODE_PRIVATE)
        editor = spf.edit()

        // CRITICAL: ID harus match dengan XML!
        inWeight = findViewById(R.id.etWeight)
        inHeight = findViewById(R.id.etHeight)
        bKalkulasi = findViewById(R.id.btnCalculate)
        bmiResult = findViewById(R.id.tvResult)
        bmiIndex = findViewById(R.id.tvIndex)

        val beratSP = spf.getInt("k_berat", 0)
        val tinggiSP = spf.getInt("k_tinggi", 0)

        inWeight.setText(beratSP.toString())
        inHeight.setText(tinggiSP.toString())

        bKalkulasi.setOnClickListener {
            val beratb = inWeight.text.toString()
            val tinggib = inHeight.text.toString()

            val bmi = beratb.toFloat()/((tinggib.toFloat()/100)*(tinggib.toFloat()/100))
            val bmiDigit = String.format("%.2f" ,bmi).toFloat()

            bmiIndex.text = bmiDigit.toString()

            if(bmiDigit >= 40.0){
                bmiResult.text = "Obese III"
            }else if(bmiDigit >= 35.0){
                bmiResult.text = "Obese II"
            }else if(bmiDigit >= 30.0){
                bmiResult.text = "Obese I"
            }else if(bmiDigit >= 25.0){
                bmiResult.text = "Overweight"
            }else if(bmiDigit >= 18.5){
                bmiResult.text = "Normal"
            }else if(bmiDigit >= 17.0){
                bmiResult.text = "Underweight (Mild thinness)"
            }else if(bmiDigit >= 16.0){
                bmiResult.text = "Underweight (Moderate thinness)"
            }else if(bmiDigit < 16.0){
                bmiResult.text = "Thinness"
            }

            val hasil = bmiResult.text.toString()

            editor.apply{
                putFloat("k_index", bmiDigit)
                putString("k_result", hasil)
                commit()
            }
        }
    }
}