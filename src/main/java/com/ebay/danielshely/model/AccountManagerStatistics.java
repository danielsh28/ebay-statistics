package com.ebay.danielshely.model;

import java.util.ArrayList;
import java.util.List;

public class AccountManagerStatistics {
    public String getFullName() {
        return fullName;
    }

    public List<ConditionSummary> getConditionSummaries() {
        return conditionSummaries;
    }

    private final String  fullName;
    private final List<ConditionSummary> conditionSummaries ;

    public AccountManagerStatistics(String fullName) {
        this.fullName = fullName;
        this.conditionSummaries = new ArrayList<>();
    }

    public void addConditionSummary(Long epid, Boolean contextAsSite, Boolean contextAsSeller){
        ConditionSummary newCondSummary = new ConditionSummary(epid,contextAsSite,contextAsSeller);
        conditionSummaries.add(newCondSummary);

    }
}
