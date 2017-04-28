package org.kathes.guava;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;

/**
 * 学习Guava的前置条件检查
 * 
 * @author wyshenfei
 * 
 */
public class PreconditionsTest {

	public static void main(String[] args) {
		// Preconditions.checkArgument(false, "参数不合法!");
		// Preconditions.checkNotNull(null, "值不允许为空");
		List<Integer> intList = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			intList.add(i);
		}
		Preconditions.checkState(1 == 2, "1不等于2, 所以抛出异常!");
		Preconditions.checkPositionIndex(11, intList.size(), "11已经超过intList的size范围!");
		Preconditions.checkPositionIndexes(-1, 3, intList.size());
	}
}
