package com.example.dipantau.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dipantau.data.repositories.MembershipRepository
import com.example.dipantau.model.User
import com.example.dipantau.model.PagedResponse
import com.example.dipantau.model.Resource
import com.example.dipantau.model.JoinHimpunanRequestNew
import com.example.dipantau.model.UpdateScoreRequest
import com.example.dipantau.model.UpdateMembershipStatusRequestNew
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MembershipViewModel @Inject constructor(
    private val membershipRepository: MembershipRepository
) : ViewModel() {

    private val _joinResult = MutableStateFlow<Resource<User>?>(null)
    val joinResult: StateFlow<Resource<User>?> = _joinResult

    private val _leaveResult = MutableStateFlow<Resource<Unit>?>(null)
    val leaveResult: StateFlow<Resource<Unit>?> = _leaveResult

    private val _membership = MutableStateFlow<Resource<User>>(Resource.Loading)
    val membership: StateFlow<Resource<User>> = _membership

    private val _himpunanUsers = MutableStateFlow<Resource<PagedResponse<User>>>(Resource.Loading)
    val himpunanUsers: StateFlow<Resource<PagedResponse<User>>> = _himpunanUsers

    private val _updateStatusResult = MutableStateFlow<Resource<User>?>(null)
    val updateStatusResult: StateFlow<Resource<User>?> = _updateStatusResult

    private val _removeResult = MutableStateFlow<Resource<Unit>?>(null)
    val removeResult: StateFlow<Resource<Unit>?> = _removeResult

    private val _updateScoreResult = MutableStateFlow<Resource<User>?>(null)
    val updateScoreResult: StateFlow<Resource<User>?> = _updateScoreResult

    fun joinHimpunan(himpunanId: Int) {
        viewModelScope.launch {
            _joinResult.value = Resource.Loading
            try {
                val request = JoinHimpunanRequestNew(himpunanId)
                val result = membershipRepository.joinHimpunan(request)
                _joinResult.value = result

                // Refresh membership if successful
                if (result is Resource.Success) {
                    getMyMembership()
                }
            } catch (e: Exception) {
                _joinResult.value = Resource.Error(e.message ?: "Gagal bergabung dengan himpunan")
            }
        }
    }

    fun leaveHimpunan() {
        viewModelScope.launch {
            _leaveResult.value = Resource.Loading
            try {
                val result = membershipRepository.leaveHimpunan()
                _leaveResult.value = result

                // Refresh membership if successful
                if (result is Resource.Success) {
                    getMyMembership()
                }
            } catch (e: Exception) {
                _leaveResult.value = Resource.Error(e.message ?: "Gagal keluar dari himpunan")
            }
        }
    }

    fun getMyMembership() {
        viewModelScope.launch {
            _membership.value = Resource.Loading
            try {
                val result = membershipRepository.getMyMembership()
                _membership.value = result
            } catch (e: Exception) {
                _membership.value = Resource.Error(e.message ?: "Gagal mengambil data keanggotaan")
            }
        }
    }

    fun getHimpunanUsers(
        himpunanId: Int,
        page: Int = 1,
        limit: Int = 10,
        search: String? = null
    ) {
        viewModelScope.launch {
            _himpunanUsers.value = Resource.Loading
            try {
                val result = membershipRepository.getHimpunanUsers(himpunanId, page, limit, search)
                _himpunanUsers.value = result
            } catch (e: Exception) {
                _himpunanUsers.value = Resource.Error(e.message ?: "Gagal mengambil data anggota himpunan")
            }
        }
    }

    fun updateMembershipStatus(userId: Int, status: String) {
        viewModelScope.launch {
            _updateStatusResult.value = Resource.Loading
            try {
                val request = UpdateMembershipStatusRequestNew(status)
                val result = membershipRepository.updateMembershipStatus(userId, request)
                _updateStatusResult.value = result

                // Refresh himpunan users if successful
                if (result is Resource.Success) {
                    // Note: Need himpunanId from context to refresh
                }
            } catch (e: Exception) {
                _updateStatusResult.value = Resource.Error(e.message ?: "Gagal memperbarui status anggota")
            }
        }
    }

    fun removeMember(userId: Int) {
        viewModelScope.launch {
            _removeResult.value = Resource.Loading
            try {
                val result = membershipRepository.removeMember(userId)
                _removeResult.value = result
            } catch (e: Exception) {
                _removeResult.value = Resource.Error(e.message ?: "Gagal menghapus anggota")
            }
        }
    }

    fun updateUserScore(userId: Int, score: Int) {
        viewModelScope.launch {
            _updateScoreResult.value = Resource.Loading
            try {
                val request = UpdateScoreRequest(score)
                val result = membershipRepository.updateUserScore(userId, request)
                _updateScoreResult.value = result
            } catch (e: Exception) {
                _updateScoreResult.value = Resource.Error(e.message ?: "Gagal memperbarui skor user")
            }
        }
    }

    // Reset functions
    fun resetJoinResult() {
        _joinResult.value = null
    }

    fun resetLeaveResult() {
        _leaveResult.value = null
    }

    fun resetUpdateStatusResult() {
        _updateStatusResult.value = null
    }

    fun resetRemoveResult() {
        _removeResult.value = null
    }

    fun resetUpdateScoreResult() {
        _updateScoreResult.value = null
    }
}