package entity;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class Company extends InfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name, description;
    private int NumEmployees, cvr;
    private double marketValue;

    public Company() {
    }

    public Company(String name, String description, int NumEmployees, int cvr, double marketValue, String email) {
        super(email);
        this.name = name;
        this.description = description;
        this.NumEmployees = NumEmployees;
        this.cvr = cvr;
        this.marketValue = marketValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumEmployees() {
        return NumEmployees;
    }

    public void setNumEmployees(int NumEmployees) {
        this.NumEmployees = NumEmployees;
    }

    public int getCvr() {
        return cvr;
    }

    public void setCvr(int cvr) {
        this.cvr = cvr;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }

    @Override
    public String toString() {
        return "Company{" + "name=" + name + ", description=" + description + ", NumEmployees=" + NumEmployees + ", cvr=" + cvr + ", marketValue=" + marketValue + '}';
    }

}
