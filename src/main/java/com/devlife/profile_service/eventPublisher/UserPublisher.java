package com.devlife.profile_service.eventPublisher;

import com.devlife.profile_service.dto.UserDto;
import com.devlife.profile_service.enums.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPublisher {
    public static final String USER_BINDING = "user-out-0";
    public static final String EVENT = "event";
    private final StreamBridge streamBridge;

    public void sendMessage(UserDto userDto, EventType event) {
        Message<UserDto> message = MessageBuilder.withPayload(userDto).setHeader(EVENT, event.name()).build();
        streamBridge.send(USER_BINDING, message);
    }
}
