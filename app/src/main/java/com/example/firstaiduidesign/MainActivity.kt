package com.example.firstaiduidesign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firstaiduidesign.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardCovidInitialFirstAid.setOnClickListener {
            val toFirstAidInfo: Intent = Intent(this, FirstAidInformationActivity::class.java)
            startActivity(toFirstAidInfo)
        }


    }
}