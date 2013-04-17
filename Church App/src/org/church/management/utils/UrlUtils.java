package org.church.management.utils;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class UrlUtils 
{
	private static final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	
	private static Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
	
	private UrlUtils()
	{
	}
	
	public static boolean validPattern(String pattern)
	{		
		boolean hasStar = pattern.contains("*");
		
		if(hasStar)
		{
			int indexOfStar = pattern.indexOf("*");
						
			if(indexOfStar < pattern.length() - 1)
			{
				return false;
			}
			
			int stars = StringUtils.countMatches(pattern, "*");
			
			if(stars > 1)
			{
				return false;
			}
			
			int periods = StringUtils.countMatches(pattern, ".");
			
			if(periods > 3)
			{
				return false;
			}
			
			boolean anyNumbers = StringUtils.containsNone(pattern, "1234567890");
			
			if(anyNumbers == true)
			{
				return false;
			}
			
			StringTokenizer tokenizer = new StringTokenizer(pattern, ".");
			
			while(tokenizer.hasMoreTokens())
			{
				String value = tokenizer.nextToken();
				
				if(value.equals("*"))
				{
					break;
				}
				
				else
				{
					boolean valid = validNumber(value);
					
					if(valid == false)
					{
						return false;
					}
				}
			}
		}
		
		else
		{
			Matcher matcher = UrlUtils.pattern.matcher(pattern);
			return matcher.matches();
		}
		
		return true;
	}
	
	public static boolean verifyIPAddress(String pattern, String host)
	{
		
		StringTokenizer hostTokens = new StringTokenizer(host, ".");
		StringTokenizer patternTokens = new StringTokenizer(pattern, ".");
		
		while(hostTokens.hasMoreTokens())
		{
			String hostToken = hostTokens.nextToken();
			String patternToken = "";
			
			if(patternTokens.hasMoreTokens())
			{
				patternToken = patternTokens.nextToken();
			}
			
			if(patternToken.equals("*"))
			{
				break;
			}
			
			int hostNumber = Integer.parseInt(hostToken);
			int patternNumber = Integer.parseInt(patternToken);
			
			if(hostNumber != patternNumber)
			{
				return false;
			}
		}
		
		return true;
	}
	
	
	private static boolean validNumber(String number)
	{
		try
		{
			int value = Integer.parseInt(number);
		
			if(value < 0 || value > 255)
			{
				return false;
			}
		}
		catch(Exception e)
		{
			return false;
		}
		
		return true;
	}
}
