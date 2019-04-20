package k.bessonov.twatter.services

import k.bessonov.twatter.dao.UsersDao
import k.bessonov.twatter.domain.User
import org.springframework.stereotype.Service

@Service
class UsersService(private val dao: UsersDao) {

    fun getDetails(login: String? = null): User {
        val username = login ?: getCurrentUsername()
        return checkNotNull(dao.getDetails(username)) { "User not found" }
    }

    fun getDetailsWithPass(login: String) = checkNotNull(dao.getDetails(login, true)) { "User not found" }

    fun register(user: User) = dao.register(user)
}