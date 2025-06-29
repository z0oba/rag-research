package org.mindwork.ragollamaserver.config

import dev.langchain4j.model.chat.ChatLanguageModel
import dev.langchain4j.model.embedding.EmbeddingModel
import dev.langchain4j.model.ollama.OllamaChatModel
import dev.langchain4j.model.ollama.OllamaEmbeddingModel
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

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

}