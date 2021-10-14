package com.raphuscucullatus.Utils;

/**
 * 标记处理器
 * @author Clinton Begin
 */
public interface TokenHandler {
  /**
   * 将SQL进行站位符?替代
   * @param content 参数名称
   * @return ?
   */
  String handleToken(String content);
}

