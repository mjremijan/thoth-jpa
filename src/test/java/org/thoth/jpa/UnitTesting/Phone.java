package org.thoth.jpa.UnitTesting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 * 
 * Section 5.2
 * https://www.javacodegeeks.com/2015/02/jpa-tutorial.html#relationships_onetomany
 */
@Entity
@Table(name = "T_PHONE")
public class Phone {
	private Long id;
	private String number;
	private Person person;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "NUMBER")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

    
   /*
    * Next to this we also have to specify the relation to the Person with the 
    * @ManyToOne, as we have “many” phones for “one” person. The annotation 
    * @JoinColumn specifies the column in the T_PHONE table that stores the 
    * foreign key to the person.
    */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSON_ID")
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}

