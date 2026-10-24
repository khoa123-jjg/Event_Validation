package com.example.event_validation

import android.R.id.message
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import com.example.event_validation.databinding.ActivityRegisterBinding
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()



        binding.btnRegister.setOnClickListener {
            if (validation()) {

                Snackbar.make(binding.root, "Đăng ký thành công!", Snackbar.LENGTH_LONG)
                    .setAction("Xem chi tiết") {
                        val intent = Intent(this, DetailActivity::class.java).apply {
                            putExtra("EXTRA_NAME", binding.tiedtName.text?.toString()?.trim().orEmpty())
                            putExtra("EXTRA_EMAIL", binding.tiedtemail.text?.toString()?.trim().orEmpty())
                            putExtra("EXTRA_PHONE", binding.tiedtNumberPhone.text?.toString()?.trim().orEmpty())
                            putExtra("EXTRA_MSV", binding.tiedtMSV.text?.toString()?.trim().orEmpty())
                            putExtra("EXTRA_CLASS", binding.tiedtClass.text?.toString()?.trim().orEmpty())
                        }
                        startActivity(intent)
                    }
                    .show()
            }
        }

        binding.tiedtName.doAfterTextChanged { binding.tilName.error = null }
        binding.tiedtemail.doAfterTextChanged { binding.tilemail.error = null }
        binding.tiedtMSV.doAfterTextChanged { binding.tilMSV.error = null }
        binding.tiedtClass.doAfterTextChanged { binding.tilClass.error = null }
        binding.tiedtNumberPhone.doAfterTextChanged { binding.tilNumberPhone.error = null }
    }
    private fun validation(): Boolean {
        var isValid = true

        val name = binding.tiedtName.text?.toString()?.trim().orEmpty()
        val email = binding.tiedtemail.text?.toString()?.trim().orEmpty()
        val msv = binding.tiedtMSV.text?.toString()?.trim().orEmpty()
        val className = binding.tiedtClass.text?.toString()?.trim().orEmpty()
        val phone = binding.tiedtNumberPhone.text?.toString()?.trim().orEmpty()

        if (name.isEmpty()) {
            binding.tilName.error = "Vui lòng nhập tên"
            binding.tiedtName.requestFocus()
            isValid = false
        } else {
            binding.tilName.error = null
        }

        if (email.isEmpty()) {
            binding.tilemail.error = "Vui lòng nhập email"
            binding.tiedtemail.requestFocus()
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilemail.error = "Email không hợp lệ"
            binding.tiedtemail.requestFocus()
            isValid = false
        }else {
            binding.tilemail.error = null
        }

        if (msv.isEmpty()) {
            binding.tilMSV.error = "Vui lòng nhập mã sinh viên"
            binding.tiedtMSV.requestFocus()
            isValid = false
        } else {
            binding.tilMSV.error = null
        }

        if (className.isEmpty()) {
            binding.tilClass.error = "Vui lòng nhập lớp"
            binding.tiedtClass.requestFocus()
            isValid = false
        } else {
            binding.tilClass.error = null
        }

        val phoneRegex = "^(03|05|07|08|09)\\d{8}$".toRegex()
        if (phone.isEmpty()) {
            binding.tilNumberPhone.error = "Vui lòng nhập số điện thoại"
            binding.tiedtNumberPhone.requestFocus()
            isValid = false
        }else if (!phone.matches(phoneRegex)) {
            binding.tilNumberPhone.error = "SĐT không hợp lệ"
            binding.tiedtNumberPhone.requestFocus()
            isValid = false}
        else {
            binding.tilNumberPhone.error = null
        }
        return isValid
    }
}