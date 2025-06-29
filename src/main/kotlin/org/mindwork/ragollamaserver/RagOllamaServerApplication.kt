package org.mindwork.ragollamaserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RagOllamaServerApplication

fun main(args: Array<String>) {
    runApplication<RagOllamaServerApplication>(*args)
}
