package com.github.com.jorgdz.app.util;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class Helper implements IHelper{
		
	public LocalDate getDate (String strDate)
	{
		LocalDate date = LocalDate.parse(strDate);
		return date;		
	}
	
}
