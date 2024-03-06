package com.example.EStockMarketApplication.Service;

import com.example.EStockMarketApplication.DTOs.CompanyResponseDTO;
import com.example.EStockMarketApplication.Exceptions.CompanyNotFound;
import com.example.EStockMarketApplication.Exceptions.LessTurnOverException;
import com.example.EStockMarketApplication.Models.Company;
import com.example.EStockMarketApplication.Models.StockPrice;
import com.example.EStockMarketApplication.Repository.CompanyRepository;
import com.example.EStockMarketApplication.Repository.StockPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private StockPriceRepository stockPriceRepository;

    @Override
    public Company RegisterCompany(Company company) {
        if (company.getCompanyTurnover().intValue() < 100000000) {
            throw new LessTurnOverException("Turnover cannot be less than 10CR");
        }
        if(company.getStockPrice().size()>=1)
        {
            company.setLatestStockPrice(company.getStockPrice().get(company.getStockPrice().size()-1).getPrice());
        }
        companyRepository.save(company);
        List<StockPrice> stockPrices = company.getStockPrice();
        stockPrices.forEach(stockPrice -> stockPrice.setCompany(company));
        stockPriceRepository.saveAll(stockPrices);

        return company;
    }

    @Override
    public List<CompanyResponseDTO> getAllCompanies() {
        List<Company> companies=companyRepository.findAll();
        List<CompanyResponseDTO> responseDTOs = new ArrayList<>();
        for (Company company : companies) {
            responseDTOs.add(new CompanyResponseDTO(company));
        }

        return responseDTOs;
    }

    @Override
    public Optional<CompanyResponseDTO> getCompanyByID(Long CompanyCode) {
        Optional<Company> company=companyRepository.findById(CompanyCode);
        if(!company.isPresent())
        {
            throw new RuntimeException("Invalid Company Code");
        }
        return company.map(CompanyResponseDTO::new);
    }

    @Override
    public void UpdateCompany(Long companyCode, Company company)
    {
        Optional<Company> company1=companyRepository.findById(companyCode);
        if(company1.isPresent()) {
            Company c = company1.get();
            c.setCompanyName(company.getCompanyName());
            c.setCompanyCEO(company.getCompanyCEO());
            if(company.getCompanyTurnover()<100000000)
            {
                throw new LessTurnOverException("Turn Over should be less than 10cr");
            }
            c.setCompanyTurnover(company.getCompanyTurnover());
            c.setCompanyWebsite(company.getCompanyWebsite());
            c.setStockExchange(company.getStockExchange());
            companyRepository.save(c);
        }
        else
        {
            throw new CompanyNotFound("Invalid Company Code");
        }
    }

    @Override
    public boolean deleteCompany(Long CompanyCode) {
        Optional<Company> company = companyRepository.findById(CompanyCode);
        if (company.isEmpty()) throw new CompanyNotFound("Company isn't found!");

        stockPriceRepository.deleteById(company.get().getCompanyCode());
        companyRepository.deleteById(CompanyCode);
        return true;
    }
}