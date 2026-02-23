package com.tmdt.base.shared.response;

import com.tmdt.base.application.dto.BaseDto;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder

public class ErrorResponse extends BaseDto {
    private int code;
    private String message;
    private String reason;
}
