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



@SuppressWarnings({"unchecked","rawtypes"})
@Repository("mockData")
public class MockStaticsDataAccessService  implements AccountManagerStatisticsDao{
    private Map<String, AccountManagerStatistics> dataMap;
     private String USED = "USED" ;
     private String NEW = "NEW" ;

    MockStaticsDataAccessService(){
        dataMap = new HashMap<>();
        try {
            JSONParser parser = new JSONParser();
            JSONObject newConditionDocs = (JSONObject) parser.parse(new FileReader(new File("").
                    getAbsolutePath() + "/src/DocsConditionNew.json"));
            JSONObject usedConditionDocs = (JSONObject) parser.parse(new FileReader(new File("").
                    getAbsolutePath() +  "/src/DocsConditionUsed.json"));
            ArrayList<Pair<String,JSONObject>> newList = buildAccountFlattenList(newConditionDocs);
            ArrayList<Pair<String,JSONObject>> usedList = buildAccountFlattenList(usedConditionDocs);
            BuildDBMap(newList,NEW);
            BuildDBMap(usedList,USED);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void BuildDBMap(ArrayList<Pair<String,JSONObject>> list,String condition ){

        //key will be name of the account manager underscore condition

        for(Pair<String,JSONObject> p : list ){
            JSONObject productData = p.getValue();
            long totalActiveSellers = (Long)productData.get("totalActiveSellers");
            long totalActiveQuantity = (Long)productData.get("totalActiveQuantity");

            if(totalActiveSellers > 3 || totalActiveQuantity > 5) {
            String name = p.getKey();
            JSONObject contextData = (JSONObject) productData.get("contextData");

            if(dataMap.get(name) == null){
                dataMap.put(name ,new AccountManagerStatistics(name));
            }
            dataMap.get(name).addConditionSummary(condition,(Long)productData.get("epid"),(Boolean)contextData.get("contextAsSite"),(Boolean)contextData.get("contextAsSeller"));

        }

        }
    }
    ArrayList<Pair> buildProdAccountArray(JSONObject json){
        JSONObject prod =  (JSONObject) json.get("product");
        JSONArray accounts = (JSONArray) json.get("items");
        return (ArrayList<Pair>)accounts.stream().map(account -> new Pair<>(((JSONObject)account).
                get("accountManager") == null ? "unknown" : ((JSONObject)account).get("accountManager") ,prod)).collect(Collectors.toList());
    }

    ArrayList<Pair<String,JSONObject>> buildAccountFlattenList(JSONObject docs){
        JSONArray docsList = ((JSONArray) (docs.get("docList")));
        ArrayList<ArrayList<Pair<String,JSONObject>>> list = (ArrayList) docsList.stream().
                map(e -> buildProdAccountArray((JSONObject) e)).collect(Collectors.toList());
        return  (ArrayList) list.stream().flatMap(e-> ((List)e).stream()).collect(Collectors.toList());
    }


    @Override
    public Map<String, AccountManagerStatistics> getAllAccountManagerStatistics() {
        return dataMap;
    }

    @Override
    public JSONArray getAccountStatistics(String name) {

     JSONArray accountStatistics = new JSONArray();
     accountStatistics.add(dataMap.get(name));
        /*JSONObject newConditionStatistics =  new JSONObject();
        JSONObject usedConditionStatistics =  new JSONObject();
        newConditionStatistics.put(name,dataMap.get(NEW + "_" + name));
        usedConditionStatistics.put(USED + "_" + name,dataMap.get(USED + "_" + name));
      accountStatistics.add(newConditionStatistics);
      accountStatistics.add(usedConditionStatistics);

*/
        return accountStatistics ;
    }
}

