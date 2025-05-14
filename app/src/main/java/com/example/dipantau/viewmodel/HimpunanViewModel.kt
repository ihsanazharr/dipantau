package com.example.dipantau.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dipantau.data.repositories.HimpunanRepository
import com.example.dipantau.model.Himpunan
import com.example.dipantau.model.HimpunanDetail
import com.example.dipantau.model.PagedResponse
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

    private val _himpunanList = MutableStateFlow<Resource<PagedResponse<Himpunan>>>(Resource.Loading)
    val himpunanList: StateFlow<Resource<PagedResponse<Himpunan>>> = _himpunanList

    private val _himpunanDetail = MutableStateFlow<Resource<HimpunanDetail>>(Resource.Loading)
    val himpunanDetail: StateFlow<Resource<HimpunanDetail>> = _himpunanDetail

    private val _createHimpunanResult = MutableStateFlow<Resource<Himpunan>?>(null)
    val createHimpunanResult: StateFlow<Resource<Himpunan>?> = _createHimpunanResult

    private val _updateHimpunanResult = MutableStateFlow<Resource<Himpunan>?>(null)
    val updateHimpunanResult: StateFlow<Resource<Himpunan>?> = _updateHimpunanResult

    private val _deleteHimpunanResult = MutableStateFlow<Resource<Unit>?>(null)
    val deleteHimpunanResult: StateFlow<Resource<Unit>?> = _deleteHimpunanResult

    private val _myHimpunanList = MutableStateFlow<Resource<List<Himpunan>>>(Resource.Loading)
    val myHimpunanList: StateFlow<Resource<List<Himpunan>>> = _myHimpunanList

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

    fun getMyHimpunan() {
        viewModelScope.launch {
            _himpunanList.value = Resource.Loading
            try {
                val result = himpunanRepository.getMyHimpunan()
                _himpunanList.value = result
            } catch (e: Exception) {
                _himpunanList.value = Resource.Error(e.message ?: "Terjadi kesalahan saat mengambil himpunan saya")
            }
        }
    }

    // Fungsi untuk membuat himpunan baru (diperbarui untuk menerima semua parameter)
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
                val himpunan = Himpunan(
                    id = 0, // ID akan diisi oleh server
                    name = name,
                    aka = aka,
                    description = description,
                    logo = null,
                    foundedDate = foundedDate,
                    status = "active",
                    contactEmail = contactEmail,
                    contactPhone = contactPhone,
                    address = address,
                    adminId = 0, // Akan diisi oleh server
                    admin = null,
                    createdAt = "",
                    updatedAt = ""
                )
                val result = himpunanRepository.createHimpunan(himpunan)
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
                // Ambil data himpunan saat ini
                val currentState = _himpunanList.value
                val currentHimpunan = if (currentState is Resource.Success) {
                    currentState.data.data.find { it.id == id }
                } else null

                // Buat objek himpunan untuk update
                val himpunan = Himpunan(
                    id = id,
                    name = name ?: currentHimpunan?.name ?: "",
                    aka = aka ?: currentHimpunan?.aka ?: "",
                    description = description ?: currentHimpunan?.description,
                    logo = currentHimpunan?.logo,
                    foundedDate = foundedDate ?: currentHimpunan?.foundedDate,
                    status = currentHimpunan?.status ?: "active",
                    contactEmail = contactEmail ?: currentHimpunan?.contactEmail,
                    contactPhone = contactPhone ?: currentHimpunan?.contactPhone,
                    address = address ?: currentHimpunan?.address,
                    adminId = currentHimpunan?.adminId ?: 0,
                    admin = currentHimpunan?.admin,
                    createdAt = currentHimpunan?.createdAt ?: "",
                    updatedAt = currentHimpunan?.updatedAt ?: ""
                )

                val result = himpunanRepository.updateHimpunan(id, himpunan)
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