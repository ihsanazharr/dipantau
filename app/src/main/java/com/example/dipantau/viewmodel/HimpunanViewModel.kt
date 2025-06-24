package com.example.dipantau.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dipantau.data.repositories.HimpunanRepository
import com.example.dipantau.model.Himpunan
import com.example.dipantau.model.HimpunanRequest
import com.example.dipantau.model.HimpunanResponse
import com.example.dipantau.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HimpunanViewModel @Inject constructor(
    private val himpunanRepository: HimpunanRepository
) : ViewModel() {

    private val _himpunanList = MutableStateFlow<Resource<HimpunanResponse>>(Resource.Loading)
    val himpunanList: StateFlow<Resource<HimpunanResponse>> = _himpunanList

    private val _himpunanDetail = MutableStateFlow<Resource<Himpunan>>(Resource.Loading)
    val himpunanDetail: StateFlow<Resource<Himpunan>> = _himpunanDetail

    private val _createHimpunanResult = MutableStateFlow<Resource<Himpunan>?>(null)
    val createHimpunanResult: StateFlow<Resource<Himpunan>?> = _createHimpunanResult

    private val _updateHimpunanResult = MutableStateFlow<Resource<Himpunan>?>(null)
    val updateHimpunanResult: StateFlow<Resource<Himpunan>?> = _updateHimpunanResult

    private val _deleteHimpunanResult = MutableStateFlow<Resource<Unit>?>(null)
    val deleteHimpunanResult: StateFlow<Resource<Unit>?> = _deleteHimpunanResult

    // Untuk himpunan milik user (bukan list)
    private val _myHimpunan = MutableStateFlow<Resource<Himpunan>>(Resource.Loading)
    val myHimpunan: StateFlow<Resource<Himpunan>> = _myHimpunan

    fun getAllHimpunan(
        page: Int = 1,
        limit: Int = 10,
        search: String = "",
        status: String = "active"
    ) {
        viewModelScope.launch {
            _himpunanList.value = Resource.Loading
            try {
                val result = himpunanRepository.getAllHimpunan(page, limit, search, status)
                _himpunanList.value = result
            } catch (e: Exception) {
                _himpunanList.value = Resource.Error(e.message ?: "Terjadi kesalahan saat mengambil data himpunan")
            }
        }
    }

    fun getHimpunanById(id: Int) {
        viewModelScope.launch {
            _himpunanDetail.value = Resource.Loading
            try {
                val result = himpunanRepository.getHimpunanById(id)
                _himpunanDetail.value = result
            } catch (e: Exception) {
                _himpunanDetail.value = Resource.Error(e.message ?: "Terjadi kesalahan saat mengambil detail himpunan")
            }
        }
    }

    fun getMyHimpunan() {
        viewModelScope.launch {
            _myHimpunan.value = Resource.Loading
            try {
                val result = himpunanRepository.getMyHimpunan()
                _myHimpunan.value = result
            } catch (e: Exception) {
                _myHimpunan.value = Resource.Error(e.message ?: "Terjadi kesalahan saat mengambil himpunan saya")
            }
        }
    }

    fun createHimpunan(
        name: String,
        aka: String,
        description: String? = null,
        foundedDate: String? = null,
        contactEmail: String? = null,
        contactPhone: String? = null,
        address: String? = null
    ) {
        viewModelScope.launch {
            _createHimpunanResult.value = Resource.Loading
            try {
                val request = HimpunanRequest(
                    name = name,
                    aka = aka,
                    description = description,
                    foundedDate = foundedDate,
                    contactEmail = contactEmail,
                    contactPhone = contactPhone,
                    address = address
                )
                val result = himpunanRepository.createHimpunan(request)
                _createHimpunanResult.value = result

                // Refresh daftar himpunan jika berhasil
                if (result is Resource.Success) {
                    getAllHimpunan()
                }
            } catch (e: Exception) {
                _createHimpunanResult.value = Resource.Error(e.message ?: "Gagal membuat himpunan")
            }
        }
    }

    fun updateHimpunan(
        id: Int,
        name: String? = null,
        aka: String? = null,
        description: String? = null,
        foundedDate: String? = null,
        contactEmail: String? = null,
        contactPhone: String? = null,
        address: String? = null
    ) {
        viewModelScope.launch {
            _updateHimpunanResult.value = Resource.Loading
            try {
                val request = HimpunanRequest(
                    name = name ?: "", // Berikan nilai default jika null
                    aka = aka ?: "",
                    description = description,
                    foundedDate = foundedDate,
                    contactEmail = contactEmail,
                    contactPhone = contactPhone,
                    address = address
                )
                val result = himpunanRepository.updateHimpunan(id, request)
                _updateHimpunanResult.value = result

                // Refresh daftar himpunan jika berhasil
                if (result is Resource.Success) {
                    getAllHimpunan()
                }
            } catch (e: Exception) {
                _updateHimpunanResult.value = Resource.Error(e.message ?: "Gagal memperbarui himpunan")
            }
        }
    }

    fun deleteHimpunan(id: Int) {
        viewModelScope.launch {
            _deleteHimpunanResult.value = Resource.Loading
            try {
                val result = himpunanRepository.deleteHimpunan(id)
                _deleteHimpunanResult.value = result

                // Refresh daftar himpunan jika berhasil
                if (result is Resource.Success) {
                    getAllHimpunan()
                }
            } catch (e: Exception) {
                _deleteHimpunanResult.value = Resource.Error(e.message ?: "Terjadi kesalahan saat menghapus himpunan")
            }
        }
    }

    fun resetCreateResult() {
        _createHimpunanResult.value = null
    }

    fun resetUpdateResult() {
        _updateHimpunanResult.value = null
    }

    fun resetDeleteResult() {
        _deleteHimpunanResult.value = null
    }
}