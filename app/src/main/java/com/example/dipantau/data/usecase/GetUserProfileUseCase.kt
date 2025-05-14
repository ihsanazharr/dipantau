package com.example.dipantau.data.usecase

import com.example.dipantau.data.repositories.AuthRepository
import com.example.dipantau.model.Resource
import com.example.dipantau.model.User
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Resource<User> {
        return authRepository.getUserProfile()
    }
}
