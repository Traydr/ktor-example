package dev.traydr.vu.domain.service

import dev.traydr.vu.domain.Token
import dev.traydr.vu.domain.User
import dev.traydr.vu.domain.exceptions.UserNotFoundException
import dev.traydr.vu.domain.repository.TokenRepository
import dev.traydr.vu.domain.repository.UserRepository
import java.security.SecureRandom
import java.time.LocalDateTime
import java.util.*

class TokenService(
    private val tokenRepository: TokenRepository,
    private val userRepository: UserRepository
) {
    fun createToken(): String {
        val base64Encoder: Base64.Encoder? = Base64.getEncoder()
        val secureRandom: SecureRandom = SecureRandom()
        var randomBytes: ByteArray = ByteArray(30)
        var stringToken: String

        do {
            // Not best practice, need to be in separate file, because secure random no resets on each
            // call to createToken()
            secureRandom.nextBytes(randomBytes)
            stringToken = base64Encoder!!.encodeToString(randomBytes)

        } while (tokenRepository.findTokenByToken(stringToken) != null)

        return stringToken
    }

    fun getTokenForEmail(email: String): Token {
        val user: User? = userRepository.findByEmail(email)
        if (user == null) {
            throw UserNotFoundException("Not able to find $email")
        }

        var token: Token? = tokenRepository.findTokenByEmail(email)
        if (token == null) {
            val tokenTemp: Token = Token(
                uid = user.id,
                value = createToken(),
                expiryDate = LocalDateTime.now().plusDays(7)
            )

            token = tokenRepository.findTokenById(tokenRepository.create(tokenTemp))
        }

        return token!!
    }

    fun refreshToken(token: Token) {

    }

    fun getTokenById(id: Long): Token? {
        return tokenRepository.findTokenById(id)
    }
}