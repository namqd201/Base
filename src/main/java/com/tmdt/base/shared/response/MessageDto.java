package com.tmdt.base.shared.response;

import com.tmdt.base.application.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageDto extends BaseDto {
    private String message;
}
