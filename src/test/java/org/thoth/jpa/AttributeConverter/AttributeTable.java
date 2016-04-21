package org.thoth.jpa.AttributeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Entity
@Table(name = "Attribute_Table")
public class AttributeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "attr_id")
    private Integer id;

    @Column(name = "attr_name")
    @Convert(converter = NameConverter.class)
    private String name;

    @Column(name = "attr_address")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
