package k.bessonov.twatter.dao

import k.bessonov.twatter.domain.Message
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class MessagesDao(private val jdbc: NamedParameterJdbcTemplate) {

    fun get(user: String?, me: String): List<Message> {
        val sql = """SELECT
                     m.*,
                     u.login,
                     COUNT(l.*)       AS likes,
                     COUNT(myl.*) = 1 AS liked
                     FROM messages m
                     LEFT JOIN likes l ON m.id = l.message_id
                     LEFT JOIN likes myl ON m.id = myl.message_id AND myl.user_id = :my_user_id :: UUID
                     LEFT JOIN users u ON m.user_id = u.id
                     WHERE ${if (user == null) "m.user_id IN (SELECT user_id FROM followers WHERE follower_id = :my_user_id::UUID)" else "m.user_id = :user_id :: UUID"}
                     GROUP BY m.id, u.login
                     ORDER BY m.time_key DESC"""
        return jdbc.query(sql, mapOf("my_user_id" to me, "user_id" to user))
        { rs, _ ->
            Message(rs.getString("id"),
                    rs.getString("text"),
                    rs.getTimestamp("time_key").toLocalDateTime(),
                    rs.getString("login"),
                    rs.getInt("likes"),
                    rs.getBoolean("liked"))
        }
    }

    fun post(text: String, userId: String) {
        val sql = """INSERT INTO messages (id, text, user_id)
                     VALUES (:id, :text, :user_id::UUID)"""
        jdbc.update(sql, mapOf("id" to UUID.randomUUID(), "user_id" to userId, "text" to text))
    }
}