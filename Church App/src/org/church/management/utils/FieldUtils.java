package org.church.management.utils;

import java.lang.reflect.Field;

import javax.persistence.Column;

import org.apache.log4j.Logger;

/**
 * 
 * @author Trae
 *
 * This class is used for accessing information about a field.
 *
 */
@SuppressWarnings("rawtypes")
public class FieldUtils 
{
	private static final Logger log = Logger.getLogger(FieldUtils.class);
	
	private FieldUtils(){}
	
	public static int getColumnLength(Class entity, String fieldName, int defaultValue)
	{
		int length = 0;
		
		try
		{
			Field field = entity.getDeclaredField(fieldName);
			Column column = field.getAnnotation(Column.class);
			length = column.length();
			
			if(length == 0)
			{
				length = defaultValue;
			}
		}
		catch(Exception e)
		{
			log.error("FieldUtils.getColumnLength()- Could not find the field: "+fieldName+".", e);
			return defaultValue;
		}
		
		return length;
	}
	
	public static Class getFieldType(Class entity, String fieldName)
	{
		try
		{
			Field field = entity.getDeclaredField(fieldName);
			return field.getType();
		}
		catch(Exception e)
		{
			log.error("FieldUtils.getFieldType()- Could not retrieve the field type for field: "+fieldName+".", e);
			return null;
		}
	}
	
	public static boolean isColumnNullable(Class entity, String fieldName)
	{
		boolean nullable = false;
		
		try
		{
			Field field = entity.getDeclaredField(fieldName);
			Column column = field.getAnnotation(Column.class);
			nullable = column.nullable();
		}
		catch(Exception e)
		{
			log.error("FieldUtils.isColumnNullable()- Could not retrieve the field type for field: "+fieldName+".", e);
		}
		
		return nullable;
	}
	
	public static boolean isColumnUnique(Class entity, String fieldName)
	{
		boolean unique = false;
		
		try
		{
			Field field = entity.getDeclaredField(fieldName);
			Column column = field.getAnnotation(Column.class);
			unique = column.unique();
		}
		catch(Exception e)
		{
			log.error("FieldUtils.isColumnUnique()- Could not retrieve the field type for field: "+fieldName+".", e);
		}
		
		return unique;
	}
	
	public static String getColunmName(Class entity, String fieldName)
	{
		String name = "";
		
		try
		{
			Field field = entity.getDeclaredField(fieldName);
			Column column = field.getAnnotation(Column.class);
			name = column.name();
		}
		catch(Exception e)
		{
			log.error("FieldUtils.getColumnName()- Could not retrieve the field type for field: "+fieldName+".", e);
		}
		
		return name;
	}
}
