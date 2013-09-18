package test.org.church.management.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.church.management.domain.Address;
import org.church.management.domain.City;
import org.church.management.domain.Country;
import org.church.management.domain.State;
import org.junit.Test;

public class AddressTester 
{
	@Test
	public void testAddressEquals()
	{
		Address address = new Address();
		
		Country country = new Country();
		country.setName("United States of America");
		country.setAbbrevation("usa");
		
		address.setCountry(country);
		
		State state = new State();
		state.setCountry(country);
		state.setName("Florida");
		
		address.setState(state);
		
		City city = new City();
		city.setName("Orlando");
		city.setState(state);
		
		address.setAddress("1399 Land Place");
		address.setCity(city);
		
		address.setZipcode("23433");
		
		Address address2 = address.clone();
		
		//true (same reference)
		Assert.assertEquals(address.equals(address),true);
		
		//true (clone of address)
		Assert.assertEquals(address.equals(address2), true);
		
		//false (object is null)
		Assert.assertEquals(address.equals(null), false);
		
		//false (different kind of object)
		Assert.assertEquals(address.equals(""), false);
		
		Address address3 = new Address();
		address3.setAddress("2883 Sun Drive");
		
		address3.setCity(city);
		address3.setCountry(country);
		address3.setState(null);
		address3.setZipcode("39933");
		
		//different address false
		Assert.assertEquals(address3.equals(address), false);
	}
	
	@Test
	public void testHashCode()
	{
		Address address = new Address();
		
		Country country = new Country();
		country.setName("United States of America");
		country.setAbbrevation("usa");
		
		address.setCountry(country);
		
		State state = new State();
		state.setCountry(country);
		state.setName("Florida");
		
		address.setState(state);
		
		City city = new City();
		city.setName("Orlando");
		city.setState(state);
		
		address.setAddress("1399 Land Place");
		address.setCity(city);
		
		address.setZipcode("23433");
		
		System.out.println("Hash Code "+address.hashCode());
		
		Address address2 = address.clone();
		
		Assert.assertEquals(address.hashCode(), address2.hashCode());
	}
	
	@Test
	public void testList()
	{
		Address address = new Address();
		
		Country country = new Country();
		country.setName("United States of America");
		country.setAbbrevation("usa");
		
		address.setCountry(country);
		
		State state = new State();
		state.setCountry(country);
		state.setName("Florida");
		
		address.setState(state);
		
		City city = new City();
		city.setName("Orlando");
		city.setState(state);
		
		address.setAddress("1399 Land Place");
		address.setCity(city);
		
		address.setZipcode("23433");
		address.setId(1);
		
		Address address3 = new Address();
		address3.setId(3);
		address3.setAddress("2883 Sun Drive");
		
		address3.setCity(city);
		address3.setCountry(country);
		address3.setState(null);
		address3.setZipcode("39933");
		
		Address address2 = address.clone();
		address2.setId(2);
		address2.setAddress("138 Testing Drive");
		
		List<Address> addresses = new ArrayList<Address>();
		
		addresses.add(address);
		addresses.add(address3);
		
		boolean contains = addresses.contains(address);
		Assert.assertEquals(contains, true);
		
		contains = addresses.contains(address3);
		Assert.assertEquals(contains, true);
		
		contains = addresses.contains(address2);
		Assert.assertEquals(contains, false);
		
		boolean removed = addresses.remove(address);
		Assert.assertEquals(removed, true);
		
		removed = addresses.remove(address2);
		Assert.assertEquals(removed, false);
		
		removed = addresses.remove(address3);
		Assert.assertEquals(removed, true);
		
		Assert.assertEquals(addresses.size(), 0);
	}
	
	@Test
	public void testMap()
	{
		Address address = new Address();
		
		Country country = new Country();
		country.setName("United States of America");
		country.setAbbrevation("usa");
		
		address.setCountry(country);
		
		State state = new State();
		state.setCountry(country);
		state.setName("Florida");
		
		address.setState(state);
		
		City city = new City();
		city.setName("Orlando");
		city.setState(state);
		
		address.setAddress("1399 Land Place");
		address.setCity(city);
		
		address.setZipcode("23433");
		address.setId(1);
		
		Address address3 = new Address();
		address3.setId(3);
		address3.setAddress("2883 Sun Drive");
		
		address3.setCity(city);
		address3.setCountry(country);
		address3.setState(null);
		address3.setZipcode("39933");
		
		Address address2 = address.clone();
		address2.setId(2);
		address2.setAddress("138 Testing Drive");
		
		Map<Address, Integer> addresses = new HashMap<Address, Integer>();
		
		addresses.put(address, address.getId());
		addresses.put(address2, address2.getId());
		addresses.put(address3, address3.getId());
		
		Integer id = addresses.get(address);
		Assert.assertEquals(1, id.intValue());
		
		id = addresses.get(address2);
		Assert.assertEquals(2, id.intValue());
		
		id = addresses.get(address3);
		Assert.assertEquals(3, id.intValue());
		
		Address wontWork = address.clone();
		wontWork.setAddress("Testing");
		
		id = addresses.get(wontWork);
		Assert.assertEquals(null, id);
	}
}
