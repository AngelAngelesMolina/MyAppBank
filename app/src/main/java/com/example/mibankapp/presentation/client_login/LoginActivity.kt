package com.example.mibankapp.presentation.client_login

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mibankapp.R
import com.example.mibankapp.common.LoaderUtils
import com.example.mibankapp.common.Resource
import com.example.mibankapp.common.utils.navigateTo
import com.example.mibankapp.data.remote.dto.request.LoginRequest
import com.example.mibankapp.databinding.ActivityLoginBinding
import com.example.mibankapp.presentation.client_detail.HomeActivity
import com.example.mibankapp.presentation.client_login.viewmodel.LoginViewModel
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(mBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initObservers()
        initListeners()
    }

    private fun initObservers() {
        loginViewModel.userState.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    LoaderUtils.showLoader(supportFragmentManager, true)
                }

                is Resource.Success -> {
                    LoaderUtils.showLoader(supportFragmentManager, true)
                    //Toast.makeText(this, "Bienvenido ${resource.data.token}", Toast.LENGTH_SHORT).show()
                    loginViewModel.saveUserData("USER_TOKEN", resource.data.token) {
                        LoaderUtils.showLoader(supportFragmentManager, false)
                        navigateTo(HomeActivity::class.java, finishCurrent = true)
                    }
                }

                is Resource.Error -> {
                    LoaderUtils.showLoader(supportFragmentManager, false)
                    LoaderUtils.showAlert(
                        mContext =
                        this@LoginActivity,
                        title = "Opps!",
                        msg = resource.message
                    ) {}
                }
            }
        }
    }

    private fun initListeners() {
        mBinding.btnLogin.setOnClickListener {
            val requestLogin = LoginRequest(
//                email = mBinding.etEmail.text.toString().trim(),
//                password = mBinding.etPassword.text.toString().trim()
                password = "cityslicka",
                email = "eve.holt@reqres.in"
            )
            loginViewModel.launchLogin(requestLogin)

//            val requestLogin = LoginRequest(mBinding.etUser.text.toString().trim(),mBinding.etEmail.text.toString().trim(),mBinding.etPassword.text.toString().trim())
//            loginViewModel.sendLogin(mBinding.etUser.text.toString().trim(),mBinding.etEmail.text.toString().trim(),mBinding.etPassword.text.toString().trim())
        }

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validateFields()
            }
        }

        mBinding.etUser.addTextChangedListener(textWatcher)
        mBinding.etEmail.addTextChangedListener(textWatcher)
        mBinding.etPassword.addTextChangedListener(textWatcher)
    }

    private fun validateFields() {
        val user = mBinding.etUser.text.toString().trim()
        val email = mBinding.etEmail.text.toString().trim()
        val password = mBinding.etPassword.text.toString().trim()
        var isValid = true
        isValid = isValid and validateField(
            input = user,
            minLength = 5,
            til = mBinding.tilUser,
            invalidMessage = getString(R.string.invalid_user)
        )
        isValid = isValid and validateEmailField(
            input = email,
            til = mBinding.tilEmail,
            invalidMessage = getString(R.string.invalid_email)
        )
        isValid = isValid and validateField(
            input = password,
            minLength = 6,
            til = mBinding.tilPassword,
            invalidMessage = getString(R.string.invalid_password)
        )
        enabledLoginButton(isEnabled = isValid, opacity = if (isValid) 1f else 0.5f)
    }

    private fun validateField(
        input: String,
        minLength: Int,
        til: TextInputLayout,
        emptyMessage: String? = null,
        invalidMessage: String? = null
    ): Boolean {
        return when {
            input.isEmpty() -> {
                til.error = emptyMessage
                false
            }

            invalidMessage != null && input.length < minLength -> {
                til.error = invalidMessage
                false
            }

            else -> {
                til.error = null
                true
            }
        }
    }

    private fun validateEmailField(
        input: String,
        til: TextInputLayout,
        invalidMessage: String
    ): Boolean {
        return when {
            input.isEmpty() -> {
                false
            }

            !isValidEmail(input) -> {
                til.error = invalidMessage
                false
            }

            else -> {
                til.error = null
                true
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun enabledLoginButton(isEnabled: Boolean, opacity: Float) {
        mBinding.btnLogin.apply {
            this.isEnabled = isEnabled
            this.alpha = opacity
        }

    }

    private fun hideKeyboard() {
        try {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            inputMethodManager?.let {
                currentFocus?.windowToken?.let { token ->
                    it.hideSoftInputFromWindow(token, 0)
                }
            }
        } catch (e: Exception) {

        }
    }

}