package com.github.com.jorgdz.app.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Movie implements Serializable{
	
	private static final long serialVersionUID = -1888379301644016676L;

	private Long id;
	
	@Size(min = 3, max = 20, message = "El nombre debe ser mayor a 3 y menor a 20 caracteres.")
	@NotEmpty(message = "El campo nombre no puede estar vacío")
	private String name;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent(message = "La fecha debe ser menor o igual a la actual")
	@NotNull(message = "La fecha no puede ser nula")
	private LocalDate date;
	
	@NotNull(message = "El precio no debe ser nulo")
	@Min(value = 0, message = "El precio debe ser mayor a 0")
	private Double price;

	public Movie(Long id, String name, LocalDate date, Double price) {
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
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
