package k.bessonov.twatter.dao

import k.bessonov.twatter.domain.User
import org.springframework.dao.DuplicateKeyException
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UsersDao(private val jdbc: NamedParameterJdbcTemplate) {

    fun getDetails(login: String, needPass: Boolean = false): User? {
        val sql = "SELECT * FROM users WHERE login = :login"
        return jdbc.queryForObject(sql, mapOf("login" to login))
        { rs, _ ->
            User(rs.getString("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("login"),
                    rs.getString("avatar")).apply {
                password = if (needPass) rs.getString("password") else null
            }
        }
    }

    fun getId(login: String): String? {
        val sql = "SELECT id FROM users WHERE login = :login"
        return jdbc.queryForObject(sql, mapOf("login" to login), String::class.java)
    }

    fun register(user: User) {
        val sql = """INSERT INTO users (id, first_name, last_name, login, password, avatar)
                     VALUES (:id, :first_name, :last_name, :login, :password, :avatar)"""
        try {
            jdbc.update(sql, mapOf("id" to UUID.randomUUID(),
                    "first_name" to user.firstName,
                    "last_name" to user.lastName,
                    "login" to user.login,
                    "password" to user.password,
                    "avatar" to user.avatar))
        } catch (e: DuplicateKeyException) {
            throw IllegalStateException("User with this login exists")
        }
    }
}