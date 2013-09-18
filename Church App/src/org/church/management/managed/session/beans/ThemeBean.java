package org.church.management.managed.session.beans;

import java.io.Serializable;

import org.church.management.configuration.Configuration;

/**
 * 
 * @author Trae
 *
 * This bean will control the theme for a single user.
 *
 */
public class ThemeBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String theme;
	
	public ThemeBean()
	{
		setTheme(Configuration.getString("theme"));
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
}
