package com.example.bt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bt.databinding.ActivitySuccessBinding

class SuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("EXTRA_NAME") ?: ""
        val email = intent.getStringExtra("EXTRA_EMAIL") ?: ""
        val phone = intent.getStringExtra("EXTRA_PHONE") ?: ""

        binding.tvName.text = getString(R.string.display_name, name)
        binding.tvEmail.text = getString(R.string.display_email, email)
        binding.tvPhone.text = getString(R.string.display_phone, phone)

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}