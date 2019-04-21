package k.bessonov.twatter.endpoints

import k.bessonov.twatter.services.UsersService
import k.bessonov.twatter.utils.responseFrom
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UsersEndpoint(private val service: UsersService) {

    @GetMapping
    fun getDetails() = responseFrom { service.getDetails() }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String) = responseFrom { service.getDetails(id) }

    @GetMapping("/search")
    fun search(@RequestParam search: String) = responseFrom { service.search(search) }
}