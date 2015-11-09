package utils;

import org.apache.commons.lang3.StringUtils;

public class TjUtils {

	public static String toNChars(String string, int n) {
		return StringUtils.center(string, n, '*');
	}
	
	public static String to80Chars(String string) {
		return toNChars(string, 80);
	}
	
	
}
