package org.church.management.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.DiscriminatorType;
import javax.persistence.InheritanceType;

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
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="VI")
public class Visitor extends Attender
{
	private static final long serialVersionUID = 1L;
	
	//(website, fryer, newspaper, mail, friend, radio)
	@Column(name="hear_about_us", length=300)
	private String hearAboutUs;
	
	//text field
	@Column(name="other", length=100)
	private String hearAboutUsOther;
	
	@Column(name="church_home")
	private boolean churchHome;
	
	@Column(name="what_church", length=70)
	private String whatChurch;
	
	@Column(name="first_time_visitor")
	private boolean firstTimeVisitor;
	
	@Column(name="returning_visitor")
	private boolean returningVisitor;
	
	@Column(name="information_about_church")
	private boolean informationAboutChurch;
	
	@Column(name="new_to_area")
	private boolean newToArea;
	
	@Column(name="like_to_visit")
	private boolean likeToVisit;
	
	@Column(name="become_christian")
	private boolean likeBecomeAChristian;
	
	@Column(name="prayer_request", length=500)
	private String prayerRequest;
	
	@Column(name="public_prayer")
	private boolean publicPrayer;
	
	@Column(name="comment", length=500)
	private String comment;
	
	private List<VisitorChild> children;
	
	public Visitor()
	{
		this.setChildren(new ArrayList<VisitorChild>());
		this.comment = "";
		this.publicPrayer = false;
		this.prayerRequest = "";
		this.marriedStatus = "";
	}

	public String getMarriedStatus() {
		return marriedStatus;
	}

	public void setMarriedStatus(String status) {
		this.marriedStatus = status;
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

	public String getHearAboutUs() {
		return hearAboutUs;
	}

	public void setHearAboutUs(String hearAboutUs) {
		this.hearAboutUs = hearAboutUs;
	}
}
