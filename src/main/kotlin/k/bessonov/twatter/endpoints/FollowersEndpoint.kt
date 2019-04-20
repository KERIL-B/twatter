package k.bessonov.twatter.endpoints

import k.bessonov.twatter.services.FollowersService
import k.bessonov.twatter.utils.responseFrom
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/followers")
class FollowersEndpoint(private val service: FollowersService) {

    @GetMapping
    fun get() = responseFrom { service.get() }

    @GetMapping("/mine")
    fun getMine() = responseFrom { service.get(true) }

    @PostMapping
    fun follow(@RequestBody userId: String) = responseFrom { service.follow(userId) }

    @DeleteMapping
    fun unFollow(@RequestBody userId: String) = responseFrom { service.unfollow(userId) }
}