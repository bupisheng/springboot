package com.springboot.util;

import java.util.Random;

public abstract class StringUtil {

	/**
	 * 返回字符串的值，如果字符串为空则返回默认值
	 * 
	 * @param src
	 *            字符串
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public static final String nvl(String src, String defaultValue) {
		if (src != null && src.length() > 0) {
			return src;
		} else {
			return defaultValue;
		}
	}

	public static final String nvl(String src) {
		return nvl(src, "");
	}

	/**
	 * 判断给入的字符串是否为空,null、""、" "都表示空字符串
	 * 
	 * @param str
	 *            待判定的字符串
	 * @return 空符串返回true，否则返回false
	 */
	public static boolean isEmpty(String str) {
		if (null == str || "".equals(str.trim())) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * 判断给入的字符串是否非空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 在字符串中找出连续最长的数字
	 * 
	 * @param intputStr
	 * @return
	 */
	public static int getMax(String intputStr) {
		int maxlength = 0;
		StringBuffer maxNumberStr = null;

		int nowlength = 0;
		StringBuffer nowNumberStr = null;

		for (int i = 0; i < intputStr.length(); i++) {
			if ((intputStr.charAt(i)) >= 48 && (intputStr.charAt(i)) <= 57) {
				if (nowlength == 0) {
					nowNumberStr = new StringBuffer(String.valueOf(intputStr.charAt(i)));
					nowlength++;
				} else {

					nowNumberStr.append(intputStr.charAt(i));
					nowlength++;
				}

				if (nowlength >= maxlength) {
					maxNumberStr = nowNumberStr;
					maxlength = nowlength;
				}
			} else {
				nowlength = 0;
				nowNumberStr = null;
			}

		}

		return maxNumberStr == null ? 0 : Integer.parseInt(maxNumberStr.toString());
	}

	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(getRandomString(32));
	}
}
