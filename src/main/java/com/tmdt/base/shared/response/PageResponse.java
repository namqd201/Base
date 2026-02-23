package com.tmdt.base.shared.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PageResponse<T extends Serializable> implements Serializable {

    private List<T> data;
    private int page;
    private int totalPage;
    private long totalItems;
    private List<CountStatusInfo> count;

    public PageResponse(Page<T> page) {
        this.data = page.getContent();
        this.page = page.getNumber();
        this.totalPage = page.getTotalPages();
        this.totalItems = page.getTotalElements();
    }

    public PageResponse(List<T> data, int page, int totalPage, long totalItems) {
        this.data = data;
        this.page = page;
        this.totalPage = totalPage;
        this.totalItems = totalItems;
    }

    public PageResponse(List<T> data) {
        this.data = data;
    }
}
