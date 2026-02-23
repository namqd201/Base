package com.tmdt.base.shared.response;

import com.tmdt.base.application.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CountStatusInfo extends BaseDto {
    private String name;
    private Long total;
    private String color = "";
}

