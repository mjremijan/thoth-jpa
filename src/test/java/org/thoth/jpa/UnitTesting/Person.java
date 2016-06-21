package org.thoth.jpa.UnitTesting;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Entity
@Table(name = "T_PERSON")
  public class Person {

  private Long id;
  private String firstName;
  private String lastName;
  private List<Phone> phones = new ArrayList<>();

  @Id
  @GeneratedValue()
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "FIRST_NAME")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Column(name = "LAST_NAME")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
  public List<Phone> getPhones() {
    return phones;
  }
}