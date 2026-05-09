package com.example.factly.data.repositories

import com.example.factly.data.models.User

object UserRepository {

    private val users = mutableListOf<User>()
    private var loggedInUser: User? = null

    fun register(username: String, email: String, password: String): Boolean {
        val exists = users.any { it.username == username || it.email == email }
        if (exists) return false
        users.add(User(username, email, password))
        return true
    }

    fun login(identifier: String, password: String): Boolean {
        val user = users.find {
            (it.username == identifier || it.email == identifier) && it.password == password
        }
        if (user != null) loggedInUser = user
        return user != null
    }

    fun getLoggedInUser(): User? = loggedInUser

    fun logout() {
        loggedInUser = null
    }
}