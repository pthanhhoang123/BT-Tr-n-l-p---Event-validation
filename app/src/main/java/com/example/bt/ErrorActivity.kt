package com.example.bt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bt.databinding.ActivityErrorBinding

class ErrorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityErrorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityErrorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val errors = intent.getStringArrayListExtra("EXTRA_ERRORS") ?: arrayListOf()
        val errorText = if (errors.isEmpty()) {
            "• Đã có lỗi xảy ra trong quá trình xác thực."
        } else {
            errors.joinToString("\n") { "• $it" }
        }

        binding.tvErrorDetails.text = errorText

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}