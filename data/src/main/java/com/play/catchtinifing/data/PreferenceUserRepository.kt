package com.play.catchtinifing.data

import android.content.Context
import com.play.catchtinifing.domain.repository.UserRepository
import com.play.catchtinifing.domain.user.model.User
import kotlin.random.Random

class PreferenceUserRepository(context: Context) : UserRepository {
    companion object {
        private const val SHARED_PREFERENCES_NAME = "com.play.catchtinifin"
        private const val KEY_NAME = "user"
    }

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    override suspend fun getUser(): User? {
        val name = sharedPreferences.getString(KEY_NAME, null)
        return createUser(name)
    }

    override suspend fun insertUser(user: User) {
        sharedPreferences.edit()
            .also { editor ->
                editor.putString(KEY_NAME, "${user.id}_${user.name}")
            }
            .apply()
    }

    override suspend fun removeAll() {
        sharedPreferences.edit()
            .also { it.clear() }
            .apply()
    }

    private fun createUser(preferencesValue: String?): User? {
        return if (preferencesValue == null) {
            null
        } else {
            val userProperty = preferencesValue.split("_")
            try {
                User(userProperty[0].toInt(), userProperty[1])
            } catch (e: NumberFormatException) {
                User(Random(System.currentTimeMillis()).nextInt(9999), userProperty[1])
            }
        }
    }
}