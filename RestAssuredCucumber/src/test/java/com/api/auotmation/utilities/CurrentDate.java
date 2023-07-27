package com.api.auotmation.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDate {

	public String getCurrentDate(){
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		String timestamp = df.format(new Date());
		return (timestamp);
	}
}