package org.kathes.guava;

import com.google.common.base.Optional;

public class OptionalTest {

	/**
	 * 使用Optional处理值为NULL的情况
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Integer age = 10;
		Optional<Integer> ageOption = Optional.of(age);
		System.out.println(ageOption);
	}

}
