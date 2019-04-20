package k.bessonov.twatter.endpoints

import k.bessonov.twatter.services.LikesService
import k.bessonov.twatter.utils.responseFrom
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/likes")
class LikesEndpoint(private val service: LikesService) {

    @PostMapping
    fun like(@RequestBody messageId: String) = responseFrom { service.like(messageId) }

    @DeleteMapping
    fun unlike(@RequestBody messageId: String) = responseFrom { service.unlike(messageId) }

    @GetMapping
    fun whoLiked(@RequestParam message: String) = responseFrom { service.whoLiked(message) }
}