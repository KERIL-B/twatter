package k.bessonov.twatter.utils

import k.bessonov.twatter.domain.Negative
import k.bessonov.twatter.domain.Positive
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.lang.Exception

fun responseFrom(method: () -> Any?) = try {
    val result = method.invoke()
    if (result == Unit) ResponseEntity(Positive(), HttpStatus.OK) else result
} catch (e: Exception) {
    when (e) {
        is IllegalStateException -> ResponseEntity(Negative(e.message), HttpStatus.INTERNAL_SERVER_ERROR)
        else -> ResponseEntity(Negative("Unknown error"), HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
