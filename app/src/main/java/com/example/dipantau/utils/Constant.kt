package com.example.dipantau.utils

object Constants {
    // API Base URL
    const val BASE_URL = "http://192.168.137.1:5000/api/"

    // SharedPreferences
    const val PREF_NAME = "HimpunanAppPrefs"

    // Debug mode
    const val DEBUG = true

    // Status Codes
    const val STATUS_ACTIVE = "active"
    const val STATUS_INACTIVE = "inactive"
    const val STATUS_PENDING = "pending"
    const val STATUS_SUSPENDED = "suspended"

    // Activity Status
    const val ACTIVITY_SCHEDULED = "dijadwalkan"
    const val ACTIVITY_ONGOING = "berlangsung"
    const val ACTIVITY_COMPLETED = "selesai"
    const val ACTIVITY_CANCELLED = "dibatalkan"

    // Attendance Status
    const val ATTENDANCE_PRESENT = "hadir"
    const val ATTENDANCE_ABSENT = "tidak_hadir"
    const val ATTENDANCE_LATE = "terlambat"
    const val ATTENDANCE_EXCUSED = "izin"

    // Membership Types
    const val MEMBERSHIP_ACTIVE = "aktif"
    const val MEMBERSHIP_ALUMNI = "alumni"

    // Attendance Modes
    const val MODE_ONLINE = "online"
    const val MODE_OFFLINE = "offline"
    const val MODE_HYBRID = "hybrid"

    // User Roles
    const val ROLE_SUPER_ADMIN = "super_admin"
    const val ROLE_ADMIN = "admin"
    const val ROLE_MEMBER = "member"



    // Priority Levels
    const val PRIORITY_LOW = "rendah"
    const val PRIORITY_MEDIUM = "sedang"
    const val PRIORITY_HIGH = "tinggi"
    const val PRIORITY_URGENT = "urgent"

    // Notification Types
    const val NOTIFICATION_REMINDER = "reminder"
    const val NOTIFICATION_ANNOUNCEMENT = "announcement"
    const val NOTIFICATION_INVITATION = "invitation"
}