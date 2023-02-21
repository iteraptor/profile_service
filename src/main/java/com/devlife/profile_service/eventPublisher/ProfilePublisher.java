package com.devlife.profile_service.eventPublisher;

import com.devlife.profile_service.dto.ProfileDto;
import com.devlife.profile_service.enums.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfilePublisher {
    public static final String PROFILE_BINDING = "profile-out-0";
    public static final String EVENT = "event";
    private final StreamBridge streamBridge;

    public void sendMessage(ProfileDto profileDto, EventType event) {
        Message<ProfileDto> message = MessageBuilder.withPayload(profileDto).setHeader(EVENT, event.name()).build();
        streamBridge.send(PROFILE_BINDING, message);
    }
}
