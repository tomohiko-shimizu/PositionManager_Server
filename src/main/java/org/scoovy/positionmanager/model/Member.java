package org.scoovy.positionmanager.model;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
/**
 * @author tomohiko
 *
 */
public class Member {
	private String educationNumber;
	private String name;
	private Long id;
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	
	public Member(String educationNumber, String name, long id) {
		super();
		Validate.notNull(educationNumber, "educationNumber is null");
		Validate.notNull(name, "name is null");
		Validate.isTrue(!StringUtils.isEmpty(educationNumber), "educationNumber is Empty");
		Validate.isTrue(!StringUtils.isEmpty(name), "name is Empty");
		this.educationNumber = educationNumber;
		this.name = name;
		this.id = id;
	}
	public long getId(){
		return this.id;
	}
	public String getEducationNumber() {
		return this.educationNumber;
	}
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	
}
