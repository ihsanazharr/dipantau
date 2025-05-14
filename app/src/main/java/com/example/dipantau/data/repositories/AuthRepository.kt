package com.example.dipantau.data.repositories

import com.example.dipantau.model.AuthResponse
import com.example.dipantau.model.Resource
import com.example.dipantau.model.User
import java.io.File

/**
 * Interface AuthRepository untuk keperluan dependency injection dan testing
 */
interface AuthRepository {
    /**
     * Login user dengan email dan password
     */
    suspend fun login(email: String, password: String): Resource<AuthResponse>

    /**
     * Register user baru
     */
    suspend fun register(
        email: String,
        password: String,
        fullName: String?,
        phoneNumber: String?
    ): Resource<AuthResponse>

    /**
     * Mendapatkan profil user yang sedang login
     */
    suspend fun getUserProfile(): Resource<User>

    /**
     * Update profil user
     */
    suspend fun updateUserProfile(
        fullName: String?,
        phoneNumber: String?,
        username: String?
    ): Resource<User>

    /**
     * Update foto profil user
     */
    suspend fun updateProfilePicture(imageFile: File): Resource<User>

    /**
     * Mengirim request lupa password
     */
    suspend fun forgotPassword(email: String): Resource<String>

    /**
     * Reset password dengan token
     */
    suspend fun resetPassword(token: String, password: String): Resource<String>

    /**
     * Logout user
     */
    fun logout()

    /**
     * Cek apakah user sedang login
     */
    fun isLoggedIn(): Boolean

    /**
     * Cek apakah user adalah admin
     */
    fun isAdmin(): Boolean

    /**
     * Cek apakah user adalah super admin
     */
    fun isSuperAdmin(): Boolean

    /**
     * Mendapatkan token autentikasi
     */
    fun getToken(): String?

    /**
     * Mendapatkan ID user
     */
    fun getUserId(): Int
}