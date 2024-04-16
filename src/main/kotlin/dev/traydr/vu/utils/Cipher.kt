package dev.traydr.vu.utils

import at.favre.lib.crypto.bcrypt.BCrypt

object Cipher {
    // TODO: Actually encrypt data
    fun encrypt(data: String): String = BCrypt.withDefaults().hashToString(12, data.toCharArray())

    fun validate(password: String, hashed: String): Boolean = BCrypt.verifyer().verify(password.toCharArray(), hashed).verified;
}
