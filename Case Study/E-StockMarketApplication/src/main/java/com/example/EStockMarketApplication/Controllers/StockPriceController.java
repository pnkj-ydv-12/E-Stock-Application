package com.example.EStockMarketApplication.Controllers;

import com.example.EStockMarketApplication.DTOs.StockPriceResponseDTO;
import com.example.EStockMarketApplication.Feign.AuthorizeClient;
import com.example.EStockMarketApplication.Models.Company;
import com.example.EStockMarketApplication.Models.StockPrice;
import com.example.EStockMarketApplication.Service.StockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1.0/market/stock")
@CrossOrigin("http://localhost:4200")
public class StockPriceController {
    private StockPriceService stockPriceService;
    private AuthorizeClient authorizeClient;

    @Autowired
    public StockPriceController(StockPriceService stockPriceService,AuthorizeClient authorizeClient) {
        this.stockPriceService = stockPriceService;
        this.authorizeClient=authorizeClient;
    }

    @PostMapping("/add/{companyCode}")
    public ResponseEntity<String> addNewPrice(@PathVariable long companyCode, @RequestBody StockPrice price, @RequestHeader ("Authorization") String token)
    {
         if(authorizeClient.authorize(token) && authorizeClient.getrole(token).equalsIgnoreCase("admin")) {
             stockPriceService.UpdateStockPrice(companyCode, price);
             return ResponseEntity.ok("New Price Updated for the Company");
         }
         else
         {
             return new ResponseEntity<>("Sorry, can only accessed by Admin", HttpStatus.BAD_REQUEST);
         }
    }
 // Add this method in your StockPriceController
    private List<StockPriceResponseDTO> convertToResponseDTOs(List<StockPrice> stockPrices) {
        List<StockPriceResponseDTO> responseDTOs = new ArrayList<>();
        for (StockPrice stockPrice : stockPrices) {
            responseDTOs.add(new StockPriceResponseDTO(stockPrice));
        }
        return responseDTOs;
    }

    @GetMapping("/getAllStock/{companyCode}")
    public ResponseEntity<List<StockPriceResponseDTO>> getAllStockPrice(@PathVariable long companyCode, @RequestHeader("Authorization") String token) {
        if (authorizeClient.authorize(token)) {
            List<StockPrice> stockPrices = stockPriceService.getAllStockPrices(companyCode);
            
            // Convert StockPrice entities to StockPriceResponseDTO (if needed)
            List<StockPriceResponseDTO> responseDTOs = convertToResponseDTOs(stockPrices);

            return ResponseEntity.ok().body(responseDTOs);  // Explicitly specify the type arguments
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }


    
}
