package org.mindwork.ragollamaserver.service

import dev.langchain4j.model.chat.ChatLanguageModel
import dev.langchain4j.model.embedding.EmbeddingModel
import dev.langchain4j.store.embedding.EmbeddingStore
import org.springframework.stereotype.Service


@Service
class RagService(
    private val embeddingModel: EmbeddingModel,
    private val chatModel: ChatLanguageModel,
    private val embeddingStore: EmbeddingStore<String>,
) {

    fun index(docs: List<String>) {
        // Добавляем каждый текст с его эмбеддингом в in-memory store
        docs.forEach { doc ->
            val embedding = embeddingModel.embed(doc).content()
            embeddingStore.add(doc, embedding)
        }
    }

    fun ask(query: String): String {
        val queryEmbedding = embeddingModel.embed(query).content()
        // ВАЖНО: Метод findRelevant(...) deprecated в LangChain4j 0.36.2 и будет заменён в следующих версиях!
        val relevant = embeddingStore.findRelevant(queryEmbedding, 5, 0.7) // minScore — Double!
        val context = relevant.joinToString("\n") { it.embeddingId() }
        val prompt = if (context.isNotBlank())
            "Контекст:\n$context\n\nВопрос: $query"
        else query
        return chatModel.generate(prompt)
    }
}