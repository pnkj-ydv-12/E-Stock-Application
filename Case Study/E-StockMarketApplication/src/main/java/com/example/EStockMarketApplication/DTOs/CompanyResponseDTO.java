package com.example.EStockMarketApplication.DTOs;

import com.example.EStockMarketApplication.Models.Company;
import com.example.EStockMarketApplication.Models.StockExchange;
import com.example.EStockMarketApplication.Models.StockPrice;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class CompanyResponseDTO {

    private Long CompanyCode;
    private String CompanyName;
    private String CompanyCEO;
    private Integer CompanyTurnover;
    private String CompanyWebsite;
    private StockExchange stockExchange;
    private StockPrice LatestStockPrice;
    private List<StockPrice> PreviousStockPrices;
    public CompanyResponseDTO()
    {

    }
    public CompanyResponseDTO(Company company)
    {
        this.CompanyCode=company.getCompanyCode();
        this.CompanyName=company.getCompanyName();
        this.CompanyCEO=company.getCompanyCEO();
        this.CompanyTurnover=company.getCompanyTurnover();
        this.CompanyWebsite=company.getCompanyWebsite();
        this.stockExchange=company.getStockExchange();
        this.LatestStockPrice=getLatestStockPrice(company);
        this.PreviousStockPrices=getPreviousStockPrices(company);
    }
    private StockPrice getLatestStockPrice(Company company) {
        if (company.getStockPrice() != null && !company.getStockPrice().isEmpty()) {
            return company.getStockPrice().get(company.getStockPrice().size()-1);
        }
        return null;
    }
    private List<StockPrice> getPreviousStockPrices(Company company)
    {
        if(company.getStockPrice()!=null && company.getStockPrice().size()>1)
        {
            return company.getStockPrice().subList(0,company.getStockPrice().size()-1);
        }
        return Collections.emptyList();
    }
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
	public StockPrice getLatestStockPrice() {
		return LatestStockPrice;
	}
	public void setLatestStockPrice(StockPrice latestStockPrice) {
		LatestStockPrice = latestStockPrice;
	}
}

