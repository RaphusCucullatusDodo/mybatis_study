package com.raphuscucullatus.Utils;

/**
 * ParameterMapping对象实体
 * 用于封装SQL解析结果
 * @author raphus cucullatus
 * @version 2021/9/30 4:16
 * @since JDK11
 */
public class ParameterMapping {
    //SQL语句中#{}或${}中的参数名
    private String content;
    public ParameterMapping(String content) {
        this.content=content;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
