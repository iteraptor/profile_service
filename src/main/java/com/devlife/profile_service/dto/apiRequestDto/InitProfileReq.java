package com.devlife.profile_service.dto.apiRequestDto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InitProfileReq {
    @NotNull(message = "must not be null")
    private Long externalId;
    private String firstName;
    private String lastName;
    private String middleName;
    @NotBlank(message = "must be fill")
    private String nickname;
    @NotEmpty
    private List<ContactInfo> contactInfoList;

    @Data
    @AllArgsConstructor
    public static class ContactInfo {
        private Integer contactType;
        private String contactValue;
    }
}
