package k.bessonov.twatter.services

import k.bessonov.twatter.dao.UsersDao
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service

fun getCurrentUsername() = (SecurityContextHolder.getContext().authentication.principal as? User)?.username
        ?: throw IllegalStateException("User does not exists")

@Service
class CurrentUserService(private val dao: UsersDao) {

    fun getId() = checkNotNull(dao.getId(getCurrentUsername()))
}