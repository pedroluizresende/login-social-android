package com.betrybe.sociallogin

import android.content.Context
import android.os.Bundle
import android.os.Message
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.view.View.OnClickListener
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.core.widget.addTextChangedListener
import com.google.android.material.snackbar.Snackbar
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
            hideKeyboard()
            if (validateEmail() && validatePassword()) {
                showSnackBar("Login efetuado com sucesso")
            }
        }
    }

    private fun validateFields() {
        val email = emailTextInput.editText?.text.toString()
        val password = passwordTextInput.editText?.text.toString()
        loginButon.isEnabled = email.isNotEmpty() && password.isNotEmpty()
    }

    private fun validateEmail(): Boolean {
        val email = emailTextInput.editText?.text.toString()
        val isValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        emailTextInput.error = if (isValid) "" else "Email inválido"
        return isValid
    }

    private fun validatePassword(): Boolean {
        val password = passwordTextInput.editText?.text.toString()
        val isValid = password.length > 4
        passwordTextInput.error = if (isValid) "" else "Senha deve ter mais de 4 caracteres"
        return isValid
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(findViewById(R.id.main), message, Snackbar.LENGTH_SHORT).show()
    }

    private fun hideKeyboard() {
        val view: View? = this.currentFocus
        if (view != null) {
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
