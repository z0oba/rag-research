package org.mindwork.ragollamaserver.config

import dev.langchain4j.model.chat.ChatLanguageModel
import dev.langchain4j.model.embedding.EmbeddingModel
import dev.langchain4j.model.ollama.OllamaChatModel
import dev.langchain4j.model.ollama.OllamaEmbeddingModel
import dev.langchain4j.store.embedding.EmbeddingStore
import dev.langchain4j.store.embedding.chroma.ChromaEmbeddingStore
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary


@Configuration
class RagConfig {
    @Bean
    fun embeddingModel(): EmbeddingModel = OllamaEmbeddingModel.builder()
        .baseUrl("http://localhost:11434")
        .modelName("nomic-embed-text") // модель эмбеддингов
        .build()

    @Bean

    fun chatLanguageModel(): ChatLanguageModel = OllamaChatModel.builder()
        .baseUrl("http://localhost:11434")
        .modelName("llama3") // модель LLM
        .build()


    @Bean
    @ConditionalOnProperty(name = ["rag.store"], havingValue = "chroma")
    fun embeddingChromaStore(): EmbeddingStore<String> =
        ChromaEmbeddingStore.builder()
            .baseUrl("http://localhost:8000")
            .collectionName("my_collection")
            .build() as EmbeddingStore<String>

    @Bean
    @ConditionalOnProperty(name = ["rag.store"], havingValue = "memory")
    fun embeddingInMemoryStore(): EmbeddingStore<String> = InMemoryEmbeddingStore()

}