package com.ebay.danielshely.model;

public class ConditionSummary {
    private Long epid;
    private Boolean contextAsSite;
    private Boolean contextAsSeller;


    public Long getEpid() {
        return epid;
    }

    public Boolean getContextAsSite() {
        return contextAsSite;
    }

    public Boolean getContextAsSeller() {
        return contextAsSeller;
    }


    public ConditionSummary(Long epid, Boolean contextAsSite, Boolean contextAsSeller) {
        this.epid = epid;
        this.contextAsSite = contextAsSite;
        this.contextAsSeller = contextAsSeller;
    }
}
