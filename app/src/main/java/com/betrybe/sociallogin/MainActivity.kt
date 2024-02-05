package com.betrybe.sociallogin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private val emailTextInput: TextInputLayout by lazy { findViewById(R.id.email_text_input_layout) }
    private val passwordTextInput: TextInputLayout by lazy { findViewById(R.id.password_text_input_layout) }
    private val loginButon: Button by lazy { findViewById(R.id.login_button) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailTextInput.editText?.addTextChangedListener {
            validateFields()
        }
        passwordTextInput.editText?.addTextChangedListener {
            validateFields()
        }

        loginButon.setOnClickListener {
            validateEmail()
        }
    }

    private fun validateFields() {
        val email = emailTextInput.editText?.text.toString()
        val password = passwordTextInput.editText?.text.toString()
        loginButon.isEnabled = email.isNotEmpty() && password.isNotEmpty()
    }

    private fun validateEmail() {
        val email = emailTextInput.editText?.text.toString()
        val isValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        emailTextInput.error = if (isValid) "" else "Email inválido"
    }
}
