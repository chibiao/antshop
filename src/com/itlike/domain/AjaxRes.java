package com.itlike.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * 用于返回ajax请求返回的回调数据
 */
public class AjaxRes {
    private String msg;
    private boolean success;
}
