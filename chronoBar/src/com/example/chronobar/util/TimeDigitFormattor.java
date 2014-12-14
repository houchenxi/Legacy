package com.example.chronobar.util;

public class TimeDigitFormattor {
	
	public static String Convert(int value)
	{
		String ori = "" + value;
		if (ori.length() < 2)
		{
			return "0" + ori;
		}
		else
		{
			return ori;
		}
	}

}
