package rbn.edu.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity<PK> implements Serializable {

    private static final long serialVersionUID = 6588334564743977775L;
    public static final String PK = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected PK id;

    public PK getId() {
	return id;
    }

    public void setId(PK id) {
	this.id = id;
    }

}
