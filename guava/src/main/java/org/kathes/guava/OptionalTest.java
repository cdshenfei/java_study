package org.kathes.guava;

import com.google.common.base.Optional;

/**
 * 学习Guava的Optional示例
 * 
 * @author wyshenfei
 * 
 */
public class OptionalTest {

	/**
	 * 使用Optional处理值为NULL的情况
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Integer age = 10;
		Optional<Integer> ageOpt = Optional.of(age);
		System.out.println(ageOpt.isPresent());
		System.out.println(ageOpt.get());

		age = null;
		System.out.println(Optional.fromNullable(age).orNull());
		System.out.println(Optional.absent().or("Empty Value!"));
		System.out.println(Optional.of(1).asSet());
	}

}
