package com.raphuscucullatus.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 标记处理器实现 对sql进行解析替换?获取列名
 * @author Clinton Begin
 */
public class ParameterMappingTokenHandler implements TokenHandler {
	private List<ParameterMapping> parameterMappings = new ArrayList<ParameterMapping>();

	/**
	 * context是 #{id} #{username} 中包含的参数名称
	 * @param content 参数名称
	 * @return
	 */
	@Override
	public String handleToken(String content) {
		parameterMappings.add(buildParameterMapping(content));
		return "?";
	}

	/**
	 * 将参数名称封装成ParameterMapping对象
	 * @param content #{}或${}中的参数名
	 * @return
	 */
	private ParameterMapping buildParameterMapping(String content) {
		ParameterMapping parameterMapping = new ParameterMapping(content);
		return parameterMapping;
	}

	public List<ParameterMapping> getParameterMappings() {
		return parameterMappings;
	}

	public void setParameterMappings(List<ParameterMapping> parameterMappings) {
		this.parameterMappings = parameterMappings;
	}

}
