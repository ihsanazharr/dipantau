package com.example.dipantau.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dipantau.data.repositories.AdminRepository
import com.example.dipantau.model.Admin
import com.example.dipantau.model.AdminDetail
import com.example.dipantau.model.AdminPasswordRequest
import com.example.dipantau.model.AdminProfileUpdateRequest
import com.example.dipantau.model.AdminRequest
import com.example.dipantau.model.PagedResponse
import com.example.dipantau.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val adminRepository: AdminRepository
) : ViewModel() {

    private val _adminList = MutableStateFlow<Resource<PagedResponse<Admin>>>(Resource.Loading)
    val adminList: StateFlow<Resource<PagedResponse<Admin>>> = _adminList

    private val _adminDetail = MutableStateFlow<Resource<AdminDetail>>(Resource.Loading)
    val adminDetail: StateFlow<Resource<AdminDetail>> = _adminDetail

    private val _createAdminResult = MutableStateFlow<Resource<Admin>?>(null)
    val createAdminResult: StateFlow<Resource<Admin>?> = _createAdminResult

    private val _updateAdminResult = MutableStateFlow<Resource<Admin>?>(null)
    val updateAdminResult: StateFlow<Resource<Admin>?> = _updateAdminResult

    private val _deleteAdminResult = MutableStateFlow<Resource<Unit>?>(null)
    val deleteAdminResult: StateFlow<Resource<Unit>?> = _deleteAdminResult

    private val _resetPasswordResult = MutableStateFlow<Resource<Unit>?>(null)
    val resetPasswordResult: StateFlow<Resource<Unit>?> = _resetPasswordResult

    private val _activateResult = MutableStateFlow<Resource<Unit>?>(null)
    val activateResult: StateFlow<Resource<Unit>?> = _activateResult

    private val _deactivateResult = MutableStateFlow<Resource<Unit>?>(null)
    val deactivateResult: StateFlow<Resource<Unit>?> = _deactivateResult

    private val _updateProfileImageResult = MutableStateFlow<Resource<Admin>?>(null)
    val updateProfileImageResult: StateFlow<Resource<Admin>?> = _updateProfileImageResult

    private val _myProfileResult = MutableStateFlow<Resource<AdminDetail>?>(null)
    val myProfileResult: StateFlow<Resource<AdminDetail>?> = _myProfileResult

    fun getAllAdmins(
        page: Int = 1,
        limit: Int = 10,
        search: String = "",
        himpunanId: Int? = null
    ) {
        viewModelScope.launch {
            _adminList.value = Resource.Loading
            try {
                val result = adminRepository.getAllAdmins(page, limit, search, himpunanId)
                _adminList.value = result
            } catch (e: Exception) {
                _adminList.value = Resource.Error(e.message ?: "Terjadi kesalahan saat mengambil data admin")
            }
        }
    }

    fun getAdminById(id: Int) {
        viewModelScope.launch {
            _adminDetail.value = Resource.Loading
            try {
                val result = adminRepository.getAdminById(id)
                _adminDetail.value = result
            } catch (e: Exception) {
                _adminDetail.value = Resource.Error(e.message ?: "Terjadi kesalahan saat mengambil detail admin")
            }
        }
    }

    fun createAdmin(
        email: String,
        fullName: String?,
        phoneNumber: String?,
        role: String,
        himpunanId: Int? = null,
        username: String? = null
    ) {
        viewModelScope.launch {
            _createAdminResult.value = Resource.Loading
            try {
                val request = AdminRequest(
                    email = email,
                    fullName = fullName,
                    phoneNumber = phoneNumber,
                    role = role,
                    himpunanId = himpunanId,
                    username = username
                )
                val result = adminRepository.createAdmin(request)
                _createAdminResult.value = result

                // Refresh daftar admin jika berhasil
                if (result is Resource.Success) {
                    getAllAdmins()
                }
            } catch (e: Exception) {
                _createAdminResult.value = Resource.Error(e.message ?: "Gagal membuat admin")
            }
        }
    }

    fun updateAdmin(
        id: Int,
        fullName: String? = null,
        phoneNumber: String? = null,
        username: String? = null,
        role: String? = null,
        himpunanId: Int? = null,
        isActive: Boolean? = null
    ) {
        viewModelScope.launch {
            _updateAdminResult.value = Resource.Loading
            try {
                val request = AdminProfileUpdateRequest(
                    fullName = fullName,
                    phoneNumber = phoneNumber,
                    username = username,
                    role = role,
                    himpunanId = himpunanId,
                    isActive = isActive
                )
                val result = adminRepository.updateAdmin(id, request)
                _updateAdminResult.value = result

                // Refresh daftar admin jika berhasil
                if (result is Resource.Success) {
                    getAllAdmins()
                }
            } catch (e: Exception) {
                _updateAdminResult.value = Resource.Error(e.message ?: "Gagal memperbarui admin")
            }
        }
    }

    fun deleteAdmin(id: Int) {
        viewModelScope.launch {
            _deleteAdminResult.value = Resource.Loading
            try {
                val result = adminRepository.deleteAdmin(id)
                _deleteAdminResult.value = result

                // Refresh daftar admin jika berhasil
                if (result is Resource.Success) {
                    getAllAdmins()
                }
            } catch (e: Exception) {
                _deleteAdminResult.value = Resource.Error(e.message ?: "Gagal menghapus admin")
            }
        }
    }

    fun resetAdminPassword(id: Int, password: String) {
        viewModelScope.launch {
            _resetPasswordResult.value = Resource.Loading
            try {
                val request = AdminPasswordRequest(password)
                val result = adminRepository.resetAdminPassword(id, request)
                _resetPasswordResult.value = result
            } catch (e: Exception) {
                _resetPasswordResult.value = Resource.Error(e.message ?: "Gagal mereset password")
            }
        }
    }

    fun activateAdmin(id: Int) {
        viewModelScope.launch {
            _activateResult.value = Resource.Loading
            try {
                val result = adminRepository.activateAdmin(id)
                _activateResult.value = result

                // Refresh daftar admin jika berhasil
                if (result is Resource.Success) {
                    getAllAdmins()
                }
            } catch (e: Exception) {
                _activateResult.value = Resource.Error(e.message ?: "Gagal mengaktifkan admin")
            }
        }
    }

    fun deactivateAdmin(id: Int) {
        viewModelScope.launch {
            _deactivateResult.value = Resource.Loading
            try {
                val result = adminRepository.deactivateAdmin(id)
                _deactivateResult.value = result

                // Refresh daftar admin jika berhasil
                if (result is Resource.Success) {
                    getAllAdmins()
                }
            } catch (e: Exception) {
                _deactivateResult.value = Resource.Error(e.message ?: "Gagal menonaktifkan admin")
            }
        }
    }

    fun updateAdminProfileImage(id: Int, profileImage: MultipartBody.Part) {
        viewModelScope.launch {
            _updateProfileImageResult.value = Resource.Loading
            try {
                val result = adminRepository.updateAdminProfileImage(id, profileImage)
                _updateProfileImageResult.value = result
            } catch (e: Exception) {
                _updateProfileImageResult.value = Resource.Error(e.message ?: "Gagal mengupload gambar profil")
            }
        }
    }

    fun getMyAdminProfile() {
        viewModelScope.launch {
            _myProfileResult.value = Resource.Loading
            try {
                val result = adminRepository.getMyAdminProfile()
                _myProfileResult.value = result
            } catch (e: Exception) {
                _myProfileResult.value = Resource.Error(e.message ?: "Gagal mengambil profil")
            }
        }
    }

    // Reset functions untuk menghapus state setelah digunakan
    fun resetCreateResult() {
        _createAdminResult.value = null
    }

    fun resetUpdateResult() {
        _updateAdminResult.value = null
    }

    fun resetDeleteResult() {
        _deleteAdminResult.value = null
    }

    fun resetResetPasswordResult() {
        _resetPasswordResult.value = null
    }

    fun resetActivateResult() {
        _activateResult.value = null
    }

    fun resetDeactivateResult() {
        _deactivateResult.value = null
    }

    fun resetUpdateProfileImageResult() {
        _updateProfileImageResult.value = null
    }

    fun resetMyProfileResult() {
        _myProfileResult.value = null
    }
}