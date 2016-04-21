package org.thoth.jpa.PersistenceXmlWithMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Beta_Table")
public class BetaTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "beta_id")
    private Integer id;

    @Column(name = "beta_desc")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
