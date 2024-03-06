package com.example.EStockMarketApplication.Service;

import com.example.EStockMarketApplication.DTOs.CompanyResponseDTO;
import com.example.EStockMarketApplication.Models.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Company RegisterCompany(Company company);

    List<CompanyResponseDTO> getAllCompanies();

    Optional<CompanyResponseDTO> getCompanyByID(Long CompanyCode);

    void UpdateCompany(Long companyCode, Company company);

    boolean deleteCompany(Long CompanyCode);
}
