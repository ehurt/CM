package org.church.management.domain;

public class VisitorChild
{
	//TODO Need allegry table for childrens allegries.
	
	private Visitor visitor = null;
	
	public VisitorChild()
	{
		this.setVisitor(null);
	}

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}
}
