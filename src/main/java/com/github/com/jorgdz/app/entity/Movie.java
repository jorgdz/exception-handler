package com.github.com.jorgdz.app.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Movie implements Serializable{
	
	private static final long serialVersionUID = -1888379301644016676L;

	private Long id;
	
	private String name;
	
	private LocalDate date;
	
	private double price;

	public Movie(Long id, String name, LocalDate date, double price) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.price = price;
	}
	
	public Movie() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Movie other = (Movie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", date=" + date + ", price=" + price + "]";
	}
	
}
