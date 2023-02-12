package com.devlife.profile_service.config;

import com.devlife.profile_service.dto.asyncMessageModel.ProjectAsyncModel;
import com.devlife.profile_service.service.eventService.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;


@Configuration
@RequiredArgsConstructor
public class CloudStreamConfig {
    private final ConsumerService<ProjectAsyncModel> projectConsumerService;

    @Bean
    public Consumer<Message<ProjectAsyncModel>> projectConsumer() {
        return projectConsumerService::handle;
    }

}
