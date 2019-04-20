package k.bessonov.twatter.endpoints

import k.bessonov.twatter.domain.User
import k.bessonov.twatter.services.UsersService
import k.bessonov.twatter.utils.responseFrom
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthEndpoint(private val service: UsersService) {

    @PostMapping
    fun register(@RequestBody user: User) = responseFrom { service.register(user) }
}