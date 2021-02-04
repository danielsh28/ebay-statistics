package com.ebay.danielshely.dao;

import com.ebay.danielshely.model.AccountManagerStatistics;
import org.json.simple.JSONArray;

import java.util.Map;

public interface AccountManagerStatisticsDao {

      Map<String,AccountManagerStatistics> getAllAccountManagerStatistics();

      JSONArray getAccountStatistics(String name);

}
