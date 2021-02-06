package com.ebay.danielshely.api;

import com.ebay.danielshely.service.AccountStatisticsService;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/statistic")
public class AccountStatisticController {

    private final AccountStatisticsService accountManagerStatisticsService;

    @Autowired
    public AccountStatisticController(AccountStatisticsService accountStatisticsService) {
        this.accountManagerStatisticsService = accountStatisticsService;
    }


    @GetMapping(path = "{accountManager}")
    //all empty string and null names can be get  trough submitting  "unknown"
    public JSONArray getAccountManagerStatistics(@PathVariable("accountManager") String accountManager) {

        return accountManagerStatisticsService.getAccountStatistics(accountManager);

    }

}
