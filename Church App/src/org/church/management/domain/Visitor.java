package org.church.management.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 
 * @author Trae
 *
 * This class is for holding information about a visitor.
 * Also this class can have extra fields added to it.
 *
 */
@Entity
public class Visitor extends Attender
{
	private static final long serialVersionUID = 1L;
	
	//(website, fryer, newspaper, mail, friend, radio)
	private String hearAboutUs;
	
	//text field
	private String hearAboutUsOther;
	
	private boolean churchHome;
	
	private String whatChurch;
	
	private boolean firstTimeVisitor;
	
	private boolean returningVisitor;
	
	private boolean informationAboutChurch;
	
	private boolean newToArea;
	
	private boolean likeToVisit;
	
	private boolean likeBecomeAChristian;
	
	private String prayerRequest;
	
	private boolean publicPrayer;
	
	private String comment;
	
	private List<VisitorChild> children;
	
	public Visitor()
	{
		this.setChildren(new ArrayList<VisitorChild>());
		this.comment = "";
		this.publicPrayer = false;
		this.prayerRequest = "";
		this.marriedStatus = "";
		this.hearAboutUs = "";
	}
	
	public String getEntityType()
	{
		return Visitor.class.getName();
	}

	public String getMarriedStatus() {
		return marriedStatus;
	}

	public void setMarriedStatus(String status) {
		this.marriedStatus = status;
	}

	public String getHearAboutUs() {
		return hearAboutUs;
	}

	public void setHearAboutUs(String hearAboutUs) {
		this.hearAboutUs = hearAboutUs;
	}

	public boolean isChurchHome() {
		return churchHome;
	}

	public void setChurchHome(boolean churchHome) {
		this.churchHome = churchHome;
	}

	public String getWhatChurch() {
		return whatChurch;
	}

	public void setWhatChurch(String whatChurch) {
		this.whatChurch = whatChurch;
	}

	public boolean isFirstTimeVisitor() {
		return firstTimeVisitor;
	}

	public void setFirstTimeVisitor(boolean firstTimeVisitor) {
		this.firstTimeVisitor = firstTimeVisitor;
	}

	public boolean isReturningVisitor() {
		return returningVisitor;
	}

	public void setReturningVisitor(boolean returningVisitor) {
		this.returningVisitor = returningVisitor;
	}

	public boolean isInformationAboutChurch() {
		return informationAboutChurch;
	}

	public void setInformationAboutChurch(boolean informationAboutChurch) {
		this.informationAboutChurch = informationAboutChurch;
	}

	public boolean isNewToArea() {
		return newToArea;
	}

	public void setNewToArea(boolean newToArea) {
		this.newToArea = newToArea;
	}

	public boolean isLikeToVisit() {
		return likeToVisit;
	}

	public void setLikeToVisit(boolean likeToVisit) {
		this.likeToVisit = likeToVisit;
	}

	public boolean isLikeBecomeAChristian() {
		return likeBecomeAChristian;
	}

	public void setLikeBecomeAChristian(boolean likeBecomeAChristian) {
		this.likeBecomeAChristian = likeBecomeAChristian;
	}

	public String getPrayerRequest() {
		return prayerRequest;
	}

	public void setPrayerRequest(String prayerRequest) {
		this.prayerRequest = prayerRequest;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isPublicPrayer() {
		return publicPrayer;
	}

	public void setPublicPrayer(boolean publicPrayer) {
		this.publicPrayer = publicPrayer;
	}
	
	public String getHearAboutUsOther() {
		return hearAboutUsOther;
	}

	public void setHearAboutUsOther(String hearAboutUsOther) {
		this.hearAboutUsOther = hearAboutUsOther;
	}

	public List<VisitorChild> getChildren() {
		return children;
	}

	public void setChildren(List<VisitorChild> children) {
		this.children = children;
	}
	
	public void addChild(VisitorChild child)
	{
		child.setVisitor(this);
		this.children.add(child);
	}

	public boolean equals(Object object)
	{
		if(object == null)
		{
			return false;
		}
		
		else if(object instanceof Visitor)
		{
			Visitor that = (Visitor) object;
			
			if(that == this)
			{
				return true;
			}
			
			return new EqualsBuilder().append(that.getId(), this.getId()).isEquals();
		}
		
		return false;
	}
	
	public int hashCode()
	{
		return new HashCodeBuilder().append(getId()).append(firstname).append(lastname).append(prefix).toHashCode();
	}
	
	public Visitor clone()
	{
		Visitor visitor = new Visitor();
		
		
		return visitor;
	}
}
