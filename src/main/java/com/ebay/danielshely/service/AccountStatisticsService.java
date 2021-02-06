package com.ebay.danielshely.service;

import com.ebay.danielshely.dao.AccountManagerStatisticsDao;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AccountStatisticsService {
    private final AccountManagerStatisticsDao accountManagerStatisticsDao;


    public AccountStatisticsService(@Qualifier("mockData") AccountManagerStatisticsDao accountManagerStatisticsDao) {
        this.accountManagerStatisticsDao = accountManagerStatisticsDao;
    }


    public JSONArray getAccountStatistics(String name) {
        return accountManagerStatisticsDao.getAccountStatistics(name);
    }


}
