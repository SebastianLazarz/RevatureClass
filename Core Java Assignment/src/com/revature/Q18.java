package com.revature;

import com.revature.additional.Q18Abstract;

public class Q18 extends Q18Abstract {

	@Override
	public boolean checkUppercase(String str) {
		for (int i=0; i < str.length(); i++) {
			if (Character.isUpperCase(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toUppercase(String str) {
		return str.toUpperCase();
	}

	@Override
	public int toInteger(String str) {
		return Integer.parseInt(str) + 10;
	}

}
