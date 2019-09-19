package com.yfc.gc.core.thread;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Locale;

/**
 * 线程存储区的key
 * @author WangYX
 *
 */
@EqualsAndHashCode
@Getter
@ToString
public class Keys<T> {

    private String key;
	
	private Class<T> clazz;
	
	private T defaultValue;
	
	public Keys(String key, Class<T> clazz) {
		super();
		this.key = key;
		this.clazz = clazz;
	}

	public Keys(String key, Class<T> clazz, T defaultValue) {
		super();
		this.key = key;
		this.clazz = clazz;
		this.defaultValue = defaultValue;
	}
	
	public static <T> Keys<T> getKeys(String key, Class<T> clazz){
		return new Keys<>(key, clazz);
	}

	public static <T> Keys<T> getKeys(String key, Class<T> clazz, T defaultValue){
		return new Keys<>(key, clazz, defaultValue);
	}

	/**
	 * 用户语言
	 */
	public static final Keys<Locale> USER_LANGUAGE		= getKeys("USER_LANGUAGE"	, Locale.class		, Locale.CHINA);

	/**
	 * 请求唯一id
	 */
	public static final Keys<String> REQUEST_ID			= getKeys("REQUEST_ID"		, String.class);

	/**
	 * 用户id
	 */
	public static final Keys<String> USER_ID			= getKeys("USER_ID"		, String.class);

	/**
	 * 表规则
	 */
	public static final Keys<String> TABLE_RULE 		= getKeys("TABLE_RULE"		, String.class, "");

}
