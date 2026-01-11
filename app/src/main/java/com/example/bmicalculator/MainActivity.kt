package com.example.bmicalculator

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var spf : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor
    private lateinit var inBerat : EditText
    private lateinit var inTinggi : EditText
    private lateinit var tombolSimpan : Button
    private lateinit var tombolHitungBMI: Button
    private lateinit var tIndex : TextView
    private lateinit var tHasil : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spf = getSharedPreferences("dataKu", MODE_PRIVATE)
        editor = spf.edit()

        // CRITICAL: ID harus match dengan XML!
        tombolSimpan = findViewById(R.id.btnSimpan)
        tombolHitungBMI = findViewById(R.id.btnHitungBMI)
        inBerat = findViewById(R.id.etBerat)
        inTinggi = findViewById(R.id.etTinggi)
        tIndex = findViewById(R.id.tvIndexM)
        tHasil = findViewById(R.id.tvResultM)

        tombolSimpan.setOnClickListener {
            val tinggi = inTinggi.text.toString().toInt()
            val berat = inBerat.text.toString().toInt()

            editor.apply{
                putInt("k_berat", berat)
                putInt("k_tinggi", tinggi)
                commit()
            }
        }

        tombolHitungBMI.setOnClickListener {
            val Ite = Intent(this, CalculateBMI::class.java)
            startActivity(Ite)
        }
    }

    override fun onResume() {
        super.onResume()
        val beratSP = spf.getInt("k_berat", 0)
        val tinggiSP = spf.getInt("k_tinggi", 0)
        val hasilSP = spf.getString("k_result",null)
        val indexSP = spf.getFloat("k_index",0f)

        inBerat.setText(beratSP.toString())
        inTinggi.setText(tinggiSP.toString())
        tHasil.text = hasilSP
        tIndex.text = indexSP.toString()
    }
}