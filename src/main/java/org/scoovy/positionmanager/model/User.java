package org.scoovy.positionmanager.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/**
 * @author tomohiko
 *
 */
public class User {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((educationNumber == null) ? 0 : educationNumber.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (educationNumber == null) {
			if (other.educationNumber != null)
				return false;
		} else if (!educationNumber.equals(other.educationNumber))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	private String educationNumber;
	private String name;
	
	public User(String educationNumber, String name) {
		super();
		Validate.notNull(educationNumber, "educationNumber is null");
		Validate.notNull(name, "name is null");
		Validate.isTrue(!StringUtils.isEmpty(educationNumber), "educationNumber is Empty");
		Validate.isTrue(!StringUtils.isEmpty(name), "name is Empty");
		this.educationNumber = educationNumber;
		this.name = name;
	}
	
	public String getEducationNumber() {
		return this.educationNumber;
	}
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return "User [educationNumber=" + educationNumber + ", name=" + name
				+ "]";
	}
	
	
}
