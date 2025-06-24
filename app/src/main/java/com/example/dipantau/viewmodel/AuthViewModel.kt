package com.example.dipantau.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dipantau.data.usecase.GetUserProfileUseCase
import com.example.dipantau.data.usecase.LoginUseCase
import com.example.dipantau.model.AuthResponse
import com.example.dipantau.model.Resource
import com.example.dipantau.model.User
import com.example.dipantau.utils.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _loginResult = MutableLiveData<Resource<AuthResponse>>()
    val loginResult: LiveData<Resource<AuthResponse>> = _loginResult

    private val _registerResult = MutableLiveData<Resource<AuthResponse>>()
    val registerResult: LiveData<Resource<AuthResponse>> = _registerResult

    private val _userProfile = MutableLiveData<Resource<User>>()
    val userProfile: LiveData<Resource<User>> = _userProfile

    private val _navigationEvent = MutableLiveData<String?>()
    val navigationEvent: LiveData<String?> = _navigationEvent

    private val _updateProfileResult = MutableLiveData<Resource<User>>()
    val updateProfileResult: LiveData<Resource<User>> = _updateProfileResult

    private val _updateProfilePictureResult = MutableLiveData<Resource<User>>()
    val updateProfilePictureResult: LiveData<Resource<User>> = _updateProfilePictureResult

    private val _forgotPasswordResult = MutableLiveData<Resource<String>>()
    val forgotPasswordResult: LiveData<Resource<String>> = _forgotPasswordResult

    private val _resetPasswordResult = MutableLiveData<Resource<String>>()
    val resetPasswordResult: LiveData<Resource<String>> = _resetPasswordResult

    fun login(email: String, password: String) {
        _loginResult.value = Resource.Loading
        viewModelScope.launch {
            val result = loginUseCase(email, password)
            _loginResult.value = result

            if (result is Resource.Success) {
                getUserProfile()
                // Navigate based on role
                navigateBasedOnRole(result.data.role)
            }
        }
    }

    fun getUserProfile() {
        _userProfile.value = Resource.Loading
        viewModelScope.launch {
            val result = getUserProfileUseCase()
            _userProfile.value = result

            // Navigate based on role if we're getting profile after login
            if (result is Resource.Success) {
                navigateBasedOnRole(result.data.role)
            }
        }
    }

    // UBAH: Jadikan function ini public
    fun navigateBasedOnRole(role: String) {
        _navigationEvent.value = when (role) {
            "super_admin" -> "super_admin"
            "admin" -> "admin"
            "member" -> "member"
            else -> "member" // Default to member
        }
    }

    fun checkUserRole(): String? {
        return when {
            sessionManager.isSuperAdmin() -> "super_admin"
            sessionManager.isAdmin() -> "admin"
            sessionManager.isLoggedIn() -> "member"
            else -> null
        }
    }

    fun getCurrentUserId(): Int {
        return sessionManager.getUserId()
    }

    fun isLoggedIn(): Boolean {
        return sessionManager.isLoggedIn()
    }

    fun logout() {
        sessionManager.logout()
        _navigationEvent.value = "logout"
    }

    fun clearNavigationEvent() {
        _navigationEvent.value = null
    }
}