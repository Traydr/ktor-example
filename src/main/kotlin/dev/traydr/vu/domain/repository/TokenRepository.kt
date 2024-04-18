package dev.traydr.vu.domain.repository

import dev.traydr.vu.domain.Token
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.javatime.datetime
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

internal object Tokens : LongIdTable() {
    val userId = reference("userId", Users.id).uniqueIndex()
    val value: Column<String> = varchar("value", 255).uniqueIndex()
    val expiryDate = datetime("expiryDate")

    fun toDomain(row: ResultRow): Token {
        return Token(
            id = row[Tokens.id].value,
            uid = row[userId].value,
            value = row[value],
            expiryDate = row[expiryDate]
        )
    }
}

class TokenRepository {
    init {
        transaction {
            SchemaUtils.create(Tokens)
        }
    }

    fun findTokenByEmail(email: String): Token? {
        return transaction {
            (Tokens innerJoin Users)
                .select { Users.email eq email }
                .map { Tokens.toDomain(it) }
                .firstOrNull()
        }
    }
}