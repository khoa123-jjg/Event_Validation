package com.example.event_validation

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.event_validation.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        binding.tvName.text = intent.getStringExtra("EXTRA_NAME")
        binding.tvEmail.text = intent.getStringExtra("EXTRA_EMAIL")
        binding.tvMSV.text = "MSV: ${intent.getStringExtra("EXTRA_MSV")} - Lớp: ${intent.getStringExtra("EXTRA_CLASS")}"
        binding.tvPhone.text = intent.getStringExtra("EXTRA_PHONE")

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}