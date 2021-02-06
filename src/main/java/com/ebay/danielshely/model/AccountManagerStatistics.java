package com.ebay.danielshely.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountManagerStatistics {
    private final String fullName;
    private final Map<String, List<ConditionSummary>> conditionSummaries;

    public String getFullName() {
        return fullName;
    }

    public Map<String, List<ConditionSummary>> getConditionSummaries() {
        return conditionSummaries;
    }

    public AccountManagerStatistics(String fullName) {
        this.fullName = fullName;
        this.conditionSummaries = new HashMap<>();
    }

    public void addConditionSummary(String condition, Long epid, Boolean contextAsSite, Boolean contextAsSeller) {
        ConditionSummary newCondSummary = new ConditionSummary(epid, contextAsSite, contextAsSeller);
        if (conditionSummaries.get(condition) == null) {
            conditionSummaries.put(condition, new ArrayList<>());
        }

        conditionSummaries.get(condition).add(newCondSummary);

    }
}
