package com.OlikTask.Library.models;


import java.time.LocalDate;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rentals")
public class Rental {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
	
    private UUID bookId;

	private UUID renterId;
	
    private LocalDate rentalDate;

    private LocalDate returnDate;
    
    private String status;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getBookId() {
		return bookId;
	}

	public void setBookId(UUID bookId) {
		this.bookId = bookId;
	}

	public UUID getRenterId() {
		return renterId;
	}

	public void setRenterId(UUID renterId) {
		this.renterId = renterId;
	}

	public LocalDate getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(LocalDate rentalDate) {
		this.rentalDate = rentalDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Rental [id=" + id + ", bookId=" + bookId + ", renterId=" + renterId + ", rentalDate=" + rentalDate
				+ ", returnDate=" + returnDate + ", status=" + status + "]";
	}

	public Rental(UUID id, UUID bookId, UUID renterId, LocalDate rentalDate, LocalDate returnDate, String status) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.renterId = renterId;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
		this.status = status;
	}

	public Rental() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
