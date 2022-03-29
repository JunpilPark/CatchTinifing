package com.play.catchtinifing.domain.repository

import com.play.catchtinifing.domain.user.model.User

interface UserRepository {
    suspend fun getUser(): User?
    suspend fun insertUser(user: User)
    suspend fun removeAll()
}