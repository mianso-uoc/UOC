package com.weldtic.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@DiscriminatorValue(value="Employee")
//@DiscriminatorColumn = "employee")
public class Employee extends User{
	
	private static final long serialVersionUID = 1L;


	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "experienceYears")
	private int experienceYears;


	@ManyToOne()
    @JoinColumn(name = "company_id")
    private Company company;

	public Employee() {}
	
	public Employee(int experienceYears) {
		this.experienceYears = experienceYears;
		
	}
 
        public int getExperienceYears() {
                return experienceYears;
        }
 
        public void setExperienceYears(int experienceYears) {
                this.experienceYears = experienceYears;
        }

		public Company getCompany() {
			return company;
		}

		public void setCompany(Company company) {
			this.company = company;
		}


		
}