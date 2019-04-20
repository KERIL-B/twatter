package k.bessonov.twatter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TwatterApplication

fun main(args: Array<String>) {
    runApplication<TwatterApplication>(*args)
}
