package k.bessonov.twatter.endpoints

import k.bessonov.twatter.services.MessagesService
import k.bessonov.twatter.utils.responseFrom
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/messages")
class MessagesEndpoint(private val service: MessagesService) {

    @GetMapping
    fun get(@RequestParam(required = false) user: String?) = responseFrom { service.get(user) }

    @GetMapping("/mine")
    fun getMine() = responseFrom { service.getMine() }

    @PostMapping
    fun post(@RequestBody text: String) = responseFrom { service.post(text) }
}