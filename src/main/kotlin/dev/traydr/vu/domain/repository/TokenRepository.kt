package dev.traydr.vu.domain.repository

import dev.traydr.vu.domain.Token
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.javatime.datetime

internal object Tokens : LongIdTable() {
    val user = reference("user", Users)
    val value: Column<String> = varchar("value", 255)
    val expiryDate = datetime("expiryDate")

    fun toDomain(row: ResultRow): Token {
        return Token(
            id = row[Tokens.id].value,
            uid = row[Users.id].value,
            value = row[value],
            expiryDate = row[expiryDate]
        )
    }
}