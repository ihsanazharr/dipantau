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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

/**
 * ViewModel untuk autentikasi dan manajemen user
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase,
//    private val registerUseCase: RegisterUseCase,
) : ViewModel() {

    // LiveData untuk hasil login
    private val _loginResult = MutableLiveData<Resource<AuthResponse>>()
    val loginResult: LiveData<Resource<AuthResponse>> = _loginResult

    // LiveData untuk hasil register
    private val _registerResult = MutableLiveData<Resource<AuthResponse>>()
    val registerResult: LiveData<Resource<AuthResponse>> = _registerResult

    // LiveData untuk profil user
    private val _userProfile = MutableLiveData<Resource<User>>()
    val getUserProfile: LiveData<Resource<User>> = _userProfile


    // LiveData untuk hasil update profil
    private val _updateProfileResult = MutableLiveData<Resource<User>>()
    val updateProfileResult: LiveData<Resource<User>> = _updateProfileResult

    // LiveData untuk hasil update foto profil
    private val _updateProfilePictureResult = MutableLiveData<Resource<User>>()
    val updateProfilePictureResult: LiveData<Resource<User>> = _updateProfilePictureResult

    // LiveData untuk hasil lupa password
    private val _forgotPasswordResult = MutableLiveData<Resource<String>>()
    val forgotPasswordResult: LiveData<Resource<String>> = _forgotPasswordResult

    // LiveData untuk hasil reset password
    private val _resetPasswordResult = MutableLiveData<Resource<String>>()
    val resetPasswordResult: LiveData<Resource<String>> = _resetPasswordResult

    /**
     * Fungsi untuk login
     */
    fun login(email: String, password: String) {
        _loginResult.value = Resource.Loading
        viewModelScope.launch {
            val result = loginUseCase(email, password)
            _loginResult.value = result

            // Jika login berhasil, ambil profil user
            if (result is Resource.Success) {
                getUserProfile()
            }
        }
    }

    /**
     * Fungsi untuk register
     */
//    fun register(email: String, password: String, fullName: String?, phoneNumber: String?) {
//        _registerResult.value = Resource.Loading
//        viewModelScope.launch {
//            val result = registerUseCase(email, password, fullName, phoneNumber)
//            _registerResult.value = result
//
//            // Jika register berhasil, ambil profil user
//            if (result is Resource.Success<*>) {
//                getUserProfile()
//            }
//        }
//    }

    /**
     * Fungsi untuk mengambil profil user
     */
    fun getUserProfile() {
        _userProfile.value = Resource.Loading
        viewModelScope.launch {
            val result = getUserProfileUseCase()
            _userProfile.value = result
        }
    }

//    /**
//     * Fungsi untuk update profil user
//     */
//    fun updateUserProfile(fullName: String?, phoneNumber: String?, username: String?) {
//        _updateProfileResult.value = Resource.Loading
//        viewModelScope.launch {
//            val result = updateUserProfileUseCase(fullName, phoneNumber, username)
//            _updateProfileResult.value = result
//        }
//    }

    /**
     * Fungsi untuk update foto profil
     */
//    fun updateProfilePicture(imageFile: File) {
//        _updateProfilePictureResult.value = Resource.Loading
//        viewModelScope.launch {
//            val result = updateProfilePictureUseCase(imageFile)
//            _updateProfilePictureResult.value = result
//        }
//    }

    /**
     * Fungsi untuk lupa password
     */
//    fun forgotPassword(email: String) {
//        _forgotPasswordResult.value = Resource.Loading
//        viewModelScope.launch {
//            val result = forgotPasswordUseCase(email)
//            _forgotPasswordResult.value = result
//        }
//    }

    /**
     * Fungsi untuk reset password
     */
//    fun resetPassword(token: String, password: String) {
//        _resetPasswordResult.value = Resource.Loading
//        viewModelScope.launch {
//            val result = resetPasswordUseCase(token, password)
//            _resetPasswordResult.value = result
//        }
//    }

    /**
     * Fungsi untuk logout
     */
//    fun logout() {
//        logoutUseCase()
//    }
}