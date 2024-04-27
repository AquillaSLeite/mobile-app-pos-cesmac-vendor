package com.example.mobileappposcesmacvendor.core.service

import com.example.mobileappposcesmacvendor.core.domain.User

object UserService {

    fun login(email: String, password: String): User? =
        UsersObject.users
            .firstOrNull { it.email == email && it.password == password }
            ?.also { UsersObject.currentUser = it }

    fun register(user: User): User {
        val name = user.name
            .split(' ')
            .joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }

        val newUser = user.copy(name = name)

        UsersObject.users.add(newUser)
        UsersObject.currentUser = newUser

        return newUser
    }

    fun findUserByEmail(email: String): Boolean = UsersObject.users.any { it.email == email }

    fun retrieveCurrentUser() = UsersObject.currentUser
}

private object UsersObject {
    val users: MutableList<User> =
        mutableListOf(User("aquilla11@hotmail.com", "Aquilla Silva Leite", "123456", 492149))
    var currentUser: User? = null
}
