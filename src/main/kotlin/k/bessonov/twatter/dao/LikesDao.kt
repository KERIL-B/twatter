package k.bessonov.twatter.dao

import k.bessonov.twatter.domain.User
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class LikesDao(private val jdbc: NamedParameterJdbcTemplate) {

    fun like(userId: String, messageId: String) {
        val sql = "INSERT INTO likes (message_id, user_id) VALUES (:message_id::UUID, :user_id::UUID)"
        jdbc.update(sql, mapOf("message_id" to messageId, "user_id" to userId))
    }

    fun unlike(userId: String, messageId: String) {
        val sql = "DELETE FROM likes WHERE message_id = :message_id::UUID AND user_id = :user_id::UUID"
        jdbc.update(sql, mapOf("message_id" to messageId, "user_id" to userId))
    }

    fun whoLiked(messageId: String): List<User> {
        val sql = """SELECT u.* FROM likes l
                     LEFT JOIN users u ON l.user_id = u.id
                     WHERE l.message_id = :message_id::UUID"""
        return jdbc.query(sql, mapOf("message_id" to messageId)) { rs, _ ->
            User(rs.getString("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("login"),
                    rs.getString("avatar"))
        }
    }
}