package com.ebay.danielshely.dao;

import com.ebay.danielshely.model.AccountManagerStatistics;
import javafx.util.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@SuppressWarnings({"unchecked", "rawtypes"})
@Repository("mockData")
public class MockStaticsDataAccessService implements AccountManagerStatisticsDao {
    private Map<String, AccountManagerStatistics> dataMap;
    private String USED = "USED";
    private String NEW = "NEW";

    public MockStaticsDataAccessService() {
        dataMap = new HashMap<>();
        try {
            JSONParser parser = new JSONParser();
            JSONObject newConditionDocs = (JSONObject) parser.parse(new FileReader(new File("").
                    getAbsolutePath() + "/src/DocsConditionNew.json"));
            JSONObject usedConditionDocs = (JSONObject) parser.parse(new FileReader(new File("").
                    getAbsolutePath() + "/src/DocsConditionUsed.json"));
            ArrayList<Pair<String, JSONObject>> newList = buildAccountFlattenList(newConditionDocs);
            ArrayList<Pair<String, JSONObject>> usedList = buildAccountFlattenList(usedConditionDocs);
            BuildDBMap(newList, NEW);
            BuildDBMap(usedList, USED);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * method thakes list of account manager and product and build the db map for it
     *
     * @param list      - the alist of pairs
     * @param condition the  product condition
     */
    void BuildDBMap(ArrayList<Pair<String, JSONObject>> list, String condition) {
        for (Pair<String, JSONObject> p : list) {
            JSONObject productData = p.getValue();
            long totalActiveSellers = (Long) productData.get("totalActiveSellers");
            long totalActiveQuantity = (Long) productData.get("totalActiveQuantity");

            if (totalActiveSellers > 3 || totalActiveQuantity > 5) {
                String name = p.getKey();
                JSONObject contextData = (JSONObject) productData.get("contextData");

                if (dataMap.get(name) == null) {
                    dataMap.put(name, new AccountManagerStatistics(name));
                }
                dataMap.get(name).addConditionSummary(condition, (Long) productData.get("epid"),
                        (Boolean) contextData.get("contextAsSite"), (Boolean) contextData.get("contextAsSeller"));

            }

        }
    }

    /**
     * build an unwind list of pairs for a specific product from a specific condition file.
     *
     * @param json
     * @return
     */
    private ArrayList<Pair> buildProdAccountArray(JSONObject json) {
        JSONObject prod = (JSONObject) json.get("product");
        JSONArray accounts = (JSONArray) json.get("items");
        return (ArrayList<Pair>) accounts.stream().map(account -> new Pair<>(((JSONObject) account).
                get("accountManager") == null ? "unknown" :
                ((JSONObject) account).get("accountManager"), prod)).collect(Collectors.toList());
    }

    /**
     * method build an unwind pairs array- creating list of product and account managers pairs.
     *
     * @param docs the raw dta jsonObject
     * @return
     */
    private ArrayList<Pair<String, JSONObject>> buildAccountFlattenList(JSONObject docs) {
        JSONArray docsList = ((JSONArray) (docs.get("docList")));

        // first mapping the list to a list of lists of pairs - each list representing product.
        ArrayList<ArrayList<Pair<String, JSONObject>>> list = (ArrayList) docsList.stream().
                map(e -> buildProdAccountArray((JSONObject) e)).collect(Collectors.toList());

        //returning the flattened version of the list
        return (ArrayList) list.stream().flatMap(e -> ((List) e).stream()).collect(Collectors.toList());
    }

    @Override
    public JSONArray getAccountStatistics(String name) {
        JSONArray accountStatistics = new JSONArray();
        AccountManagerStatistics accountManagerStatistics = dataMap.get(name);
        if (accountManagerStatistics == null) {
            accountStatistics.add("Account manager name not exist!!");
        } else {
            accountStatistics.add(accountManagerStatistics);
        }
        return accountStatistics;
    }
}

