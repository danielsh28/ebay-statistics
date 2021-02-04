package com.ebay.danielshely.service;

import com.ebay.danielshely.dao.AccountManagerStatisticsDao;
import com.ebay.danielshely.model.AccountManagerStatistics;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Map;

public class AccountStatisticsService {

    public AccountStatisticsService(@Qualifier("mockData") AccountManagerStatisticsDao accountManagerStatisticsDao) {
        this.accountManagerStatisticsDao = accountManagerStatisticsDao;
    }

    private AccountManagerStatisticsDao accountManagerStatisticsDao;
    Map<String, AccountManagerStatistics> getAllAccountManagerStatistics(){
        return accountManagerStatisticsDao.getAllAccountManagerStatistics();
    }


    JSONArray getJSONObject(String name){
        return accountManagerStatisticsDao.getAccountStatistics(name);
    }


}
