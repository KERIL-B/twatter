package k.bessonov.twatter.domain

import java.time.LocalDateTime

data class Message(
        val id: String,
        val text: String,
        val timeKey: LocalDateTime,
        val userId: String,
        val user: String,
        val likes: Int,
        val liked: Boolean
)