package k.bessonov.twatter.services

import k.bessonov.twatter.dao.MessagesDao
import k.bessonov.twatter.domain.Message
import org.springframework.stereotype.Service

@Service
class MessagesService(private val currentUserService: CurrentUserService,
                      private val dao: MessagesDao) {

    fun get(userId: String?) = dao.get(userId, currentUserService.getId())

    fun getMine(): List<Message> {
        val me = currentUserService.getId()
        return dao.get(me, me)
    }

    fun post(text: String) {
        val myId = currentUserService.getId()
        dao.post(text, myId)
    }
}