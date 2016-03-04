package com.vastikaEIS.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "resumemarketing")
public class ResumeMarketing implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne()
	@JoinColumn(name = "heroResumeId")
	private HeroesResume heroResume;

	// recruiter
	@ManyToOne()
	@JoinColumn(name = "recruiterId", referencedColumnName = "id")
	private User recruiter;

	@ManyToOne()
	@JoinColumn(name = "heroesId", referencedColumnName = "id")
	private Heroes heroes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public HeroesResume getHeroResume() {
		return heroResume;
	}

	public void setHeroResume(HeroesResume heroResume) {
		this.heroResume = heroResume;
	}

	public User getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(User recruiter) {
		this.recruiter = recruiter;
	}

	public Heroes getHeroes() {
		return heroes;
	}

	public void setHeroes(Heroes heroes) {
		this.heroes = heroes;
	}

}
