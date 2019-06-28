package com.itlike.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * 用于接收前端发送的页码和查询数据的条数
 * 还有模糊查询的关键字
 */
public class QueryVo {
    private int page;
    private int rows;
    private String keyword;
}
