package k.bessonov.twatter.services

import k.bessonov.twatter.dao.FollowersDao
import k.bessonov.twatter.domain.User
import org.springframework.stereotype.Service

@Service
class FollowersService(private val dao: FollowersDao,
                       private val currentUserService: CurrentUserService) {

    fun get(mine: Boolean = false): List<User> {
        val myId = currentUserService.getId()
        return dao.get(myId, mine)
    }

    fun follow(userId: String) {
        val myId = currentUserService.getId()
        dao.follow(userId, myId)
    }

    fun unfollow(userId:String){
        val myId = currentUserService.getId()
        dao.unFollow(userId, myId)
    }
}