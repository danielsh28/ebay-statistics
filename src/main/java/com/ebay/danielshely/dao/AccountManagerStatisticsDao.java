package com.ebay.danielshely.dao;

import org.json.simple.JSONArray;


public interface AccountManagerStatisticsDao {

    JSONArray getAccountStatistics(String name);

}
