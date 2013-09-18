package test.org.church.management.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.church.management.domain.Language;
import org.junit.Test;

public class LanguageTester 
{
	@Test
	public void testEquals()
	{
		Language lang = new Language();
		lang.setAbbrevation("en");
		lang.setName("English");
		
		Language lang2 = lang.clone();
		
		//same object (true)
		Assert.assertEquals(lang.equals(lang), true);
		
		//clone (true)
		Assert.assertEquals(lang.equals(lang2), true);
	
		//null false
		Assert.assertEquals(lang.equals(null), false);
		
		//different kind of object
		Assert.assertEquals(lang.equals(""), false);
	}
	
	@Test
	public void testHashCode()
	{
		Language lang = new Language();
		lang.setAbbrevation("en");
		lang.setName("English");
		
		Language lang2 = lang.clone();
		
		System.out.println("Hash code: "+lang.hashCode());
		
		Assert.assertEquals(lang2.hashCode(), lang.hashCode());
	}
	
	@Test
	public void testClone()
	{
		Language lang = new Language();
		lang.setAbbrevation("en");
		lang.setName("English");
		
		Language lang2 = lang.clone();
		
		Assert.assertEquals(lang, lang2);
	}
	
	@Test
	public void testList()
	{
		Language lang = new Language();
		lang.setAbbrevation("en");
		lang.setName("English");
		lang.setId(1);
		
		Language lang2 = new Language();
		lang2.setAbbrevation("es");
		lang2.setName("Spanish");
		lang2.setId(2);
		
		Language lang3 = new Language();
		lang3.setAbbrevation("gr");
		lang3.setName("Greek");
		lang3.setId(3);
		
		List<Language> list = new ArrayList<Language>();
		
		list.add(lang);
		list.add(lang2);
		
		Assert.assertEquals(list.contains(lang), true);
		Assert.assertEquals(list.contains(lang2), true);
		Assert.assertEquals(list.contains(lang3), false);
		
		Assert.assertEquals(list.remove(lang2), true);
		Assert.assertEquals(list.remove(lang), true);
		Assert.assertEquals(list.remove(lang), false);
		
		Assert.assertEquals(list.size(), 0);
	}

	@Test
	public void testMap()
	{
		Language lang = new Language();
		lang.setAbbrevation("en");
		lang.setName("English");
		lang.setId(1);
		
		Language lang2 = new Language();
		lang2.setAbbrevation("es");
		lang2.setName("Spanish");
		lang2.setId(2);
		
		Map<Language, Integer> map = new HashMap<Language, Integer>();
		map.put(lang, lang.getId());
		map.put(lang2, lang2.getId());
		
		Assert.assertEquals(map.get(lang), new Integer(1));
		
		Assert.assertEquals(map.get(lang2), new Integer(2));
		
		map.remove(lang);
		map.remove(lang2);
		
		Assert.assertEquals(map.size(), 0);
	}
}
