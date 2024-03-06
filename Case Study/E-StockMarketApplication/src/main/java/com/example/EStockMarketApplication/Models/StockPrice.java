package com.example.EStockMarketApplication.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity

public class StockPrice {
    public Long getid() {
		return id;
	}
	public void setid(Long id) {
		id = id;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price;
    @Column(updatable = false)
    private LocalDateTime timeStamp;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CompanyCode" ,referencedColumnName = "CompanyCode")
    @JsonBackReference
    private Company company;
    @PrePersist
    protected void onCreate() {
        timeStamp = LocalDateTime.now();
    }
}
