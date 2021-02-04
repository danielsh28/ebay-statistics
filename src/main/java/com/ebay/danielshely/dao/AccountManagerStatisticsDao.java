package com.ebay.danielshely.dao;

import com.ebay.danielshely.model.AccountManagerStatistics;
import com.ebay.danielshely.model.ConditionSummary;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

public interface AccountManagerStatisticsDao {

      Map<String,AccountManagerStatistics> getAllAccountManagerStatistics();

      JSONArray getAccountStatistics(String name);

}
