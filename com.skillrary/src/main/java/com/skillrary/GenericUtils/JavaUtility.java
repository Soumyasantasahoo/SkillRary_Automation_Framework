package com.skillrary.GenericUtils;

import java.util.Date;
import java.util.Random;

/**
 * This class contains java specific generic libraries
 * @author Chaitra
 */

public class JavaUtility {
	
	/**
	 * generate random number 
	 * @author Chaitra
	 * 
	 */
	public static String getRandomData()
	{
		Random random=new Random();
		int ran=random.nextInt(1000);
		return ""+ran;			
	}
	
	/**
	 * Generate current system date
	 * @author Chaitra
	 */
	public static String getCurrentSystemDate()
	{
		Date date = new Date();
		String currentDate=date.toString();
		return currentDate.replace(" ", "_").replace(":", "_");
		
	}
	
	

}
