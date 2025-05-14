package com.example.dipantau.utils


import android.content.Context
import android.content.SharedPreferences
import com.example.dipantau.model.AuthResponse
import com.example.dipantau.model.User
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Kelas untuk mengelola session user (token, data user, dll)
 */
@Singleton
class SessionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private var prefs: SharedPreferences =
        context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = prefs.edit()
    private val gson = Gson()

    companion object {
        // Keys for SharedPreferences
        private const val KEY_TOKEN = "user_token"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USER = "user_data"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_USER_ROLE = "user_role"
        private const val KEY_USER_EMAIL = "user_email"

    }

    /**
     * Menyimpan data auth response dari API login/register
     */
    fun saveAuthResponse(response: AuthResponse) {
        editor.putString(KEY_TOKEN, response.token)
        editor.putInt(KEY_USER_ID, response.id)
        editor.putString(KEY_USER_ROLE, response.role)
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.apply()
    }

    /**
     * Menyimpan token autentikasi
     */
    fun saveToken(token: String) {
        editor.putString(KEY_TOKEN, token)
        editor.apply()
    }

    /**
     * Mengambil token autentikasi
     */
    fun getToken(): String? {
        return prefs.getString(KEY_TOKEN, null)
    }

    /**
     * Menyimpan data user
     */
    fun saveUser(user: User) {
        val userJson = gson.toJson(user)
        editor.putString(KEY_USER, userJson)
        editor.putInt(KEY_USER_ID, user.id)
        editor.putString(KEY_USER_ROLE, user.role)
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.apply()
    }

    /**
     * Mengambil data user
     */
    fun getUser(): User? {
        val userJson = prefs.getString(KEY_USER, null) ?: return null
        return try {
            gson.fromJson(userJson, User::class.java)
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Cek apakah user telah login
     */
    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    /**
     * Logout user
     */
    fun logout() {
        editor.clear()
        editor.apply()
    }

    /**
     * Mengambil ID user
     */
    fun getUserId(): Int {
        return prefs.getInt(KEY_USER_ID, -1)
    }

    /**
     * Mengambil role user
     */
    fun getUserRole(): String {
        return prefs.getString(KEY_USER_ROLE, "") ?: ""
    }

    /**
     * Cek apakah user adalah admin
     */
    fun isAdmin(): Boolean {
        val role = getUserRole()
        return role == Constants.ROLE_ADMIN || role == Constants.ROLE_SUPER_ADMIN
    }

    /**
     * Cek apakah user adalah super admin
     */
    fun isSuperAdmin(): Boolean {
        return getUserRole() == Constants.ROLE_SUPER_ADMIN
    }
}