package dev.traydr.vu.domain.service

import dev.traydr.vu.domain.User
import dev.traydr.vu.domain.repository.UserRepository
import dev.traydr.vu.utils.Cipher
import java.util.Base64

class UserService(private val userRepository: UserRepository) {
    private val base64Encoder = Base64.getEncoder()

    fun create(user: User): User {
        userRepository.findByEmail(user.email).apply {
            require(this == null) { "Email already registered!" }
        }
        userRepository.create(user.copy(password = user.password?.let { Cipher.encrypt(it) }))
        return user.copy() // token = generateJwtToken(user)
    }
}