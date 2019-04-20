package k.bessonov.twatter.dao

import k.bessonov.twatter.domain.User
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class FollowersDao(private val jdbc: NamedParameterJdbcTemplate) {

    fun get(myId: String, mine: Boolean = false): List<User> {
        val sql = """SELECT u.* FROM followers f
                     LEFT JOIN users u ON f.${if (!mine) "user_id" else "follower_id"} = u.id
                     WHERE f.${if (mine) "user_id" else "follower_id"} = :my_id::UUID"""
        return jdbc.query(sql, mapOf("my_id" to myId)) { rs, _ ->
            User(rs.getString("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("login"),
                    rs.getString("avatar"))
        }
    }

    fun follow(userId: String, followerId: String) {
        val sql = "INSERT INTO followers (user_id, follower_id) VALUES (:user_id::UUID, :follower_id::UUID)"
        jdbc.update(sql, mapOf("user_id" to userId, "follower_id" to followerId))
    }

    fun unFollow(userId: String, followerId: String){
        val sql = "DELETE FROM followers WHERE user_id = :user_id::UUID AND follower_id = :follower_id::UUID"
        jdbc.update(sql, mapOf("user_id" to userId, "follower_id" to followerId))
    }
}