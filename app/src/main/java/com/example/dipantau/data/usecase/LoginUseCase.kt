package com.example.dipantau.data.usecase

import com.example.dipantau.data.repositories.AuthRepository
import com.example.dipantau.model.AuthResponse
import com.example.dipantau.model.Resource
import javax.inject.Inject

/**
 * Use case untuk login
 */
class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    /**
     * Invoke operator memungkinkan kelas digunakan seperti fungsi
     */
    suspend operator fun invoke(email: String, password: String): Resource<AuthResponse> {
        // Validasi input
        if (email.isBlank()) {
            return Resource.Error("Email tidak boleh kosong")
        }

        if (password.isBlank()) {
            return Resource.Error("Password tidak boleh kosong")
        }

        // Validasi format email (sederhana)
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Resource.Error("Format email tidak valid")
        }

        // Panggil repository
        return authRepository.login(email, password)
    }
}