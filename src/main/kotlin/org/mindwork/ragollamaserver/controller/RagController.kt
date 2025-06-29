package org.mindwork.ragollamaserver.controller

import org.mindwork.ragollamaserver.service.RagService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rag")
class RagController(private val ragService: RagService) {
    @PostMapping("/index")
    fun index(@RequestBody docs: List<String>) = ragService.index(docs)

    @GetMapping("/ask")
    fun ask(@RequestParam query: String): String = ragService.ask(query)
}