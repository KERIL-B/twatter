package k.bessonov.twatter.services

import k.bessonov.twatter.dao.LikesDao
import org.springframework.stereotype.Service

@Service
class LikesService(private val dao: LikesDao,
                   private val currentUserService: CurrentUserService) {

    fun like(messageId: String) {
        val myId = currentUserService.getId()
        dao.like(myId, messageId)
    }

    fun unlike(messageId: String) {
        val myId = currentUserService.getId()
        dao.unlike(myId, messageId)
    }

    fun whoLiked(messageId: String) = dao.whoLiked(messageId)

}