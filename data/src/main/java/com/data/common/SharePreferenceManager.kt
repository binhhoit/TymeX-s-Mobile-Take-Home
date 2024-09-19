package com.data.common

import android.content.Context
import android.content.SharedPreferences
import com.data.network.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Locale
import kotlin.Exception

class SharePreferenceManager constructor(app: Context) {

    companion object {
        private const val SHARED_PREF_NAME = "shared_pref"
        private const val USER_TOKEN = "user_token"
        private const val LOCALE_KEY = "locale_key"
        private const val FIRST_OPEN_APP = "first_open_app"
        private const val USERS_KEY = "users_key"

        // For Singleton instantiation
        @Volatile
        private var instance: SharePreferenceManager? = null

        fun getInstance(context: Context): SharePreferenceManager {
            return instance ?: synchronized(this) {
                instance ?: SharePreferenceManager(context).also { instance = it }
            }
        }
    }

    private val sharedPreferences by lazy(LazyThreadSafetyMode.NONE) {
        app.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

    var userToken: String
        get() = sharedPreferences.getString(USER_TOKEN, "")!!
        set(value) = sharedPreferences.put { putString(USER_TOKEN, value) }

    fun clearData() {
        sharedPreferences.edit().remove(USER_TOKEN).apply()
    }

    private inline fun SharedPreferences.put(body: SharedPreferences.Editor.() -> Unit) {
        val editor = this.edit()
        editor.body()
        editor.apply()
    }

    var localeCurrent: Locale
        get() = try {
            Gson().fromJson(sharedPreferences.getString(LOCALE_KEY,"en"), Locale::class.java)
        } catch (e: Exception) {
            Locale.ENGLISH
        }
        set(value) = sharedPreferences.put { putString(LOCALE_KEY, Gson().toJson(value)) }

    var users: List<User>?
        get() = try {
            val value = sharedPreferences.getString(USERS_KEY, "")
            val itemType = object : TypeToken<List<User>>() {}.type
            Gson().fromJson(value, itemType)
        } catch (e: Exception) {
            listOf()
        }
        set(value) {
            sharedPreferences.put {
                putString(USERS_KEY, Gson().toJson(value));
            }
        }
}
