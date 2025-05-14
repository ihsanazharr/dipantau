package com.example.dipantau.data.repositories

import com.example.dipantau.data.remote.ApiService
import com.example.dipantau.model.*
import com.example.dipantau.utils.Constants
import com.example.dipantau.utils.SessionManager
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val sessionManager: SessionManager
) : BaseRepository(), AuthRepository {

    override suspend fun login(email: String, password: String): Resource<AuthResponse> {
        val loginRequest = LoginRequest(email, password)
        val result: Resource<AuthResponse> = safeApiCall { apiService.loginUser(loginRequest) }

        if (result is Resource.Success) {
            sessionManager.saveAuthResponse(result.data)
        }

        return result
    }

    override suspend fun register(
        email: String,
        password: String,
        fullName: String?,
        phoneNumber: String?
    ): Resource<AuthResponse> {
        val registerRequest = RegisterRequest(email, password, fullName, phoneNumber)
        val result: Resource<AuthResponse> = safeApiCall { apiService.registerUser(registerRequest) }

        if (result is Resource.Success) {
            sessionManager.saveAuthResponse(result.data)
        }

        return result
    }

    override suspend fun getUserProfile(): Resource<User> {
        return safeApiCall { apiService.getUserProfile() }
    }

    override suspend fun updateUserProfile(
        fullName: String?,
        phoneNumber: String?,
        username: String?
    ): Resource<User> {
        val updateRequest = UpdateProfileRequest(fullName, phoneNumber, username)
        val result: Resource<User> = safeApiCall { apiService.updateUserProfile(updateRequest) }

        if (result is Resource.Success) {
            sessionManager.saveUser(result.data)
        }

        return result
    }

    override suspend fun updateProfilePicture(imageFile: File): Resource<User> {
        val requestFile = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("profilePicture", imageFile.name, requestFile)

        val result: Resource<User> = safeApiCall { apiService.updateProfilePicture(body) }

        if (result is Resource.Success) {
            sessionManager.saveUser(result.data)
        }

        return result
    }

    override suspend fun forgotPassword(email: String): Resource<String> {
        TODO("Not yet implemented")
    }

    override suspend fun resetPassword(token: String, password: String): Resource<String> {
        TODO("Not yet implemented")
    }

//    override suspend fun forgotPassword(email: String): Resource<String> {
//        val forgotPasswordRequest = ForgotPasswordRequest(email)
//        return safeApiCall { apiService.forgotPassword(forgotPasswordRequest) }
//    }

//    override suspend fun resetPassword(token: String, password: String): Resource<String> {
//        val resetPasswordRequest = ResetPasswordRequest(token, password)
//        return safeApiCall { apiService.resetPassword(resetPasswordRequest) }
//    }

    override fun logout() {
        sessionManager.logout()
    }

    override fun isLoggedIn(): Boolean {
        return sessionManager.isLoggedIn()
    }

    override fun isAdmin(): Boolean {
        return sessionManager.isAdmin()
    }

    override fun isSuperAdmin(): Boolean {
        return sessionManager.isSuperAdmin()
    }

    override fun getToken(): String? {
        return sessionManager.getToken()
    }

    override fun getUserId(): Int {
        return sessionManager.getUserId()
    }
}
