package com.ebay.danielshely.dao;

import org.json.simple.JSONArray;

/**
 * interface for the account manager status service Data access object
 */
public interface AccountManagerStatisticsDao {

    JSONArray getAccountStatistics(String name);

}
