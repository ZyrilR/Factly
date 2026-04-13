    package com.example.factly

    import android.app.Activity
    import android.content.Intent
    import android.os.Bundle
    import android.widget.Button
    import android.widget.CheckBox
    import android.widget.EditText
    import android.widget.ImageView
    import android.widget.TextView
    import android.widget.Toast
    import androidx.activity.enableEdgeToEdge
    import androidx.appcompat.app.AppCompatActivity
    import androidx.core.view.ViewCompat
    import androidx.core.view.WindowInsetsCompat

    class RegisterActivity : Activity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_register)

            val usernameField = findViewById<EditText>(R.id.editTextUsername)
            val emailField = findViewById<EditText>(R.id.editTextEmail)
            val passwordField = findViewById<EditText>(R.id.editTextPassword)
            val confirmPasswordField = findViewById<EditText>(R.id.editTextConfirmPassword)
            val showPasswordCheckBox = findViewById<CheckBox>(R.id.checkBoxShowPassword)

            val getStartedButton = findViewById<Button>(R.id.buttonGetStarted)

            val password = intent.getStringExtra("password")
            passwordField.setText(password)

            showPasswordCheckBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    passwordField.inputType = android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    confirmPasswordField.inputType = android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                } else {
                    passwordField.inputType = android.text.InputType.TYPE_CLASS_TEXT or
                            android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
                    confirmPasswordField.inputType = android.text.InputType.TYPE_CLASS_TEXT or
                            android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
                }
                passwordField.setSelection(passwordField.text.length)
            }

            getStartedButton.setOnClickListener {
                val username = usernameField.text.toString()
                val email = emailField.text.toString()
                val password = passwordField.text.toString()
                val confirmPassword = confirmPasswordField.text.toString()

                // Check if any field is empty
                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                } else if (password != confirmPassword) {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()

                    passwordField.text.clear()
                    confirmPasswordField.text.clear()
                } else {
                    val intent = Intent(this, LoginActivity::class.java)

                    intent.putExtra("username", username)
                    intent.putExtra("email", email)
                    intent.putExtra("password", password)

                    startActivity(intent)
                }
            }
        }

    }