package com.example.EStockMarketApplication.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Company {
    public Long getCompanyCode() {
		return CompanyCode;
	}

	public void setCompanyCode(Long companyCode) {
		CompanyCode = companyCode;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	public String getCompanyCEO() {
		return CompanyCEO;
	}

	public void setCompanyCEO(String companyCEO) {
		CompanyCEO = companyCEO;
	}

	public Integer getCompanyTurnover() {
		return CompanyTurnover;
	}

	public void setCompanyTurnover(Integer companyTurnover) {
		CompanyTurnover = companyTurnover;
	}

	public String getCompanyWebsite() {
		return CompanyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		CompanyWebsite = companyWebsite;
	}

	public StockExchange getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(StockExchange stockExchange) {
		this.stockExchange = stockExchange;
	}

	public List<StockPrice> getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(List<StockPrice> stockPrice) {
		this.stockPrice = stockPrice;
	}

	public BigDecimal getLatestStockPrice() {
		return LatestStockPrice;
	}

	public void setLatestStockPrice(BigDecimal latestStockPrice) {
		LatestStockPrice = latestStockPrice;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CompanyCode;
    @Column(nullable = false)
    private String CompanyName;
    @Column(nullable = false)
    private String CompanyCEO;
    @Column(nullable = false)
    private Integer CompanyTurnover;
    @Column(nullable = false)
    private String CompanyWebsite;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StockExchange stockExchange;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<StockPrice> stockPrice=new ArrayList<>();

    private BigDecimal LatestStockPrice;

}
