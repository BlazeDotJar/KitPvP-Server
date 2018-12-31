package me.teamdream.de.kitpvp.bank;

import java.text.NumberFormat;

public class CurrencyManager {
	private static NumberFormat nf = NumberFormat.getCurrencyInstance();
	
	public static final String currency = "ℂ";
	
	public static int[] splitInCreditsAndSubCredits(double money) {
		int[] split = new int[2];
		int full = ((int)money/1);
		double half = (money-full);
		String s = nf.format(half);
		s=s.substring(1);
		half = Double.parseDouble(s);
		half = (half*100);
		split[0] = (int)full;
		split[1] = (int)half;
		return split;
	}
//	
//	public static int[] splitFromStringToCredits(String money) {
//		int[] split = new int[2];
//		String[] a = money.split(":");
//		int full = Integer.parseInt(a[0]);
//		int half = Integer.parseInt(a[1]);
//		split[0] = full;
//		split[1] = half;
//		return split;
//	}
	
}
