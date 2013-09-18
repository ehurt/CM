package test.org.church.management.domain;

import java.util.List;

import junit.framework.Assert;

import org.church.management.domain.Allergy;
import org.church.management.domain.Attender;
import org.church.management.domain.AttenderAllergy;
import org.church.management.domain.AttenderTalent;
import org.church.management.domain.Talent;
import org.church.management.exceptions.DuplicateException;
import org.junit.Test;

public class AttenderTester 
{
/*
	@Test
	public void testTalents()
	{
		Attender attender = new Attender();
		
		attender.setId(4);
		attender.setFemale(true);
		attender.setFirstname("Ashley");
		attender.setMiddlename("Elizabeth");
		attender.setLastname("Hurt");
		
		Talent talent = new Talent();
		talent.setName("Foreman");
		talent.setId(1);
		
		try 
		{
			attender.addTalent(talent, "Good at plumbing.");
		} catch (DuplicateException e) {
			Assert.assertEquals(false, true);
		}
		
		//lower case did work
		try 
		{
			talent = new Talent();
			talent.setId(3);
			talent.setName("foreman");
			attender.addTalent(talent, "Good at electrical wiring.");
			Assert.assertEquals(false, true);
		} catch (DuplicateException e) {
			e.printStackTrace();
		}
		
		try 
		{
			talent = new Talent();
			talent.setId(2);
			talent.setName("foreign language");
			attender.addTalent(talent, "speaks spanish");
		} catch (DuplicateException e) {
			Assert.assertEquals(false, true);
		}
		
		talent = new Talent();
		talent.setName("foreman");
		
		AttenderTalent foreman = attender.getTalent(talent);
		
		Assert.assertEquals(foreman.getId().getTalent().getId().intValue(), 1);
		
		attender.removeTalent(foreman);
		
		List<AttenderTalent> talents = attender.getTalents();
		
		Assert.assertEquals(talents.size(), 1);
	}
	
	@Test
	public void testAllergies()
	{
		Attender attender = new Attender();
		
		attender.setId(4);
		attender.setFemale(true);
		attender.setFirstname("Ashley");
		attender.setMiddlename("Elizabeth");
		attender.setLastname("Hurt");
		
		Allergy allergy = new Allergy();
		allergy.setId(1);
		allergy.setName("Nuts");
		
		try 
		{
			attender.addAllergy(allergy, "Anything with nuts in it.");
		}
		catch (DuplicateException e) 
		{
			Assert.assertEquals(false, true);
		}
		
		Allergy allergy2 = new Allergy();
		allergy2.setId(2);
		allergy2.setName("oranges");
		
		try {
			attender.addAllergy(allergy2, "Anything with oranges.");
		} catch (DuplicateException e) 
		{
			Assert.assertEquals(false, true);
		}
		
		Assert.assertEquals(attender.getAllergies().size(), 2);
		
		allergy = new Allergy();
		allergy.setId(3);
		allergy.setName("nuts");
		
		try {
			attender.addAllergy(allergy, "Anything with oranges.");
			Assert.assertEquals(false, true);
		} catch (DuplicateException e) 
		{
		}
		
		AttenderAllergy attenderAllergy = attender.getAllergy(allergy);
		
		Assert.assertEquals(attenderAllergy.getId().getAllergy().getId().intValue(), 1);
		
		Assert.assertEquals(attenderAllergy.getId().getAllergy().getNameLc(), "nuts");
		
		attender.removeAllergy(attenderAllergy);
		
		Assert.assertEquals(attender.getAllergies().size(), 1);
	}*/
}
