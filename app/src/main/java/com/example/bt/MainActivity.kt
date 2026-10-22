package com.example.bt

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.bt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRealtimeValidation()

        // Validate initial state so it matches the slide screenshot on launch
        validateAllFields()

        binding.btnRegister.setOnClickListener {
            val errors = validateAllFields()
            if (errors.isEmpty()) {
                // Thành công: Chuyển sang SuccessActivity
                val intent = Intent(this, SuccessActivity::class.java).apply {
                    putExtra("EXTRA_NAME", binding.etName.text.toString().trim())
                    putExtra("EXTRA_EMAIL", binding.etEmail.text.toString().trim())
                    putExtra("EXTRA_PHONE", binding.etPhone.text.toString().trim())
                }
                startActivity(intent)
            } else {
                // Nhập sai/Chưa hợp lệ: Chuyển sang ErrorActivity
                val intent = Intent(this, ErrorActivity::class.java).apply {
                    putStringArrayListExtra("EXTRA_ERRORS", ArrayList(errors))
                }
                startActivity(intent)
            }
        }
    }

    private fun setupRealtimeValidation() {
        binding.etName.doOnTextChanged { text, _, _, _ ->
            validateName(text.toString())
        }

        binding.etEmail.doOnTextChanged { text, _, _, _ ->
            validateEmail(text.toString())
        }

        binding.etPassword.doOnTextChanged { text, _, _, _ ->
            validatePassword(text.toString())
        }

        binding.etPhone.doOnTextChanged { text, _, _, _ ->
            validatePhone(text.toString())
        }
    }

    private fun validateName(input: String): String? {
        val name = input.trim()
        val error = when {
            name.isBlank() -> "Lỗi: Họ và tên không được để trống"
            else -> null
        }
        binding.tilName.error = error
        return error
    }

    private fun validateEmail(input: String): String? {
        val email = input.trim()
        val error = when {
            email.isBlank() -> "Lỗi: Email không được để trống"
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Lỗi: Email không đúng định dạng"
            else -> null
        }
        binding.tilEmail.error = error
        return error
    }

    private fun validatePassword(input: String): String? {
        val password = input.trim()
        val error = when {
            password.isBlank() -> "Lỗi: Mật khẩu không được để trống"
            password.length < 6 -> "Lỗi: Mật khẩu phải từ 6 ký tự trở lên"
            else -> null
        }
        binding.tilPassword.error = error
        return error
    }

    private fun validatePhone(input: String): String? {
        val phone = input.trim()
        val error = when {
            phone.isBlank() -> "Lỗi: Số điện thoại không được để trống"
            !phone.matches(Regex("^0[0-9]{9}$")) -> "Lỗi: Số điện thoại không hợp lệ (cần 10 chữ số)"
            else -> null
        }
        binding.tilPhone.error = error
        return error
    }

    private fun validateAllFields(): List<String> {
        val errors = mutableListOf<String>()

        validateName(binding.etName.text.toString())?.let { errors.add(it) }
        validateEmail(binding.etEmail.text.toString())?.let { errors.add(it) }
        validatePassword(binding.etPassword.text.toString())?.let { errors.add(it) }
        validatePhone(binding.etPhone.text.toString())?.let { errors.add(it) }

        return errors
    }
}