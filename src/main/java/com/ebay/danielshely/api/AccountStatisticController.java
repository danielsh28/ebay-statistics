package com.ebay.danielshely.api;


import com.ebay.danielshely.dao.AccountManagerStatisticsDao;
import com.ebay.danielshely.model.AccountManagerStatistics;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/statistic")
public class AccountStatisticController {

    private final AccountManagerStatisticsDao  accountManagerStatisticsService;

    @Autowired
    public AccountStatisticController(AccountManagerStatisticsDao accountManagerStatisticsService){
        this.accountManagerStatisticsService = accountManagerStatisticsService;
    }

    @GetMapping(path ="/all")
    Map<String, AccountManagerStatistics> getAllAccountManagerStatistics(){
        return accountManagerStatisticsService.getAllAccountManagerStatistics();
    }

    @GetMapping(path = "{accountManager}")
    public JSONArray getAccountManagerStatistics(@PathVariable("accountManager") String accountManager) {
        return accountManagerStatisticsService.getAccountStatistics(accountManager);

    }

}
