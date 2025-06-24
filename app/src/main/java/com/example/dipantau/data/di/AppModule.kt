package com.example.dipantau.data.di

import android.content.Context
import com.example.dipantau.data.remote.ApiService
import com.example.dipantau.data.remote.RetrofitConfig
import com.example.dipantau.data.repositories.AdminRepository
import com.example.dipantau.data.repositories.AdminRepositoryImpl
import com.example.dipantau.data.repositories.AuthRepository
import com.example.dipantau.data.repositories.AuthRepositoryImpl
import com.example.dipantau.data.repositories.HimpunanRepository
import com.example.dipantau.data.repositories.HimpunanRepositoryImpl
import com.example.dipantau.data.repositories.MembershipRepository
import com.example.dipantau.data.repositories.MembershipRepositoryImpl
import com.example.dipantau.data.repositories.TaskRepository
import com.example.dipantau.data.repositories.TaskRepositoryImpl
import com.example.dipantau.utils.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSessionManager(@ApplicationContext context: Context): SessionManager {
        return SessionManager(context)
    }

    @Provides
    @Singleton
    fun provideApiService(sessionManager: SessionManager): ApiService {
        return RetrofitConfig.getApiService(sessionManager)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        apiService: ApiService,
        sessionManager: SessionManager
    ): AuthRepository {
        return AuthRepositoryImpl(apiService, sessionManager)
    }

    @Provides
    @Singleton
    fun provideHimpunanRepository(
        apiService: ApiService
    ): HimpunanRepository {
        return HimpunanRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideAdminRepository(
        apiService: ApiService
    ): AdminRepository {
        return AdminRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideMembershipRepository(
        apiService: ApiService
    ): MembershipRepository {
        return MembershipRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideTaskRepository(
        apiService: ApiService
    ): TaskRepository {
        return TaskRepositoryImpl(apiService)
    }

//    @Provides
//    @Singleton
//    fun provideHimpunanRepository(apiService: ApiService): HimpunanRepository {
//        return HimpunanRepository(apiService)
//    }
//
//    @Provides
//    @Singleton
//    fun provideHimpunanMemberRepository(apiService: ApiService): HimpunanMemberRepository {
//        return HimpunanMemberRepository(apiService)
//    }
//
//    @Provides
//    @Singleton
//    fun provideActivityRepository(apiService: ApiService): ActivityRepository {
//        return ActivityRepository(apiService)
//    }
//
//    @Provides
//    @Singleton
//    fun provideAttendanceRepository(apiService: ApiService): AttendanceRepository {
//        return AttendanceRepository(apiService)
//    }
//
//    @Provides
//    @Singleton
//    fun provideNotificationRepository(apiService: ApiService): NotificationRepository {
//        return NotificationRepository(apiService)
//    }
}