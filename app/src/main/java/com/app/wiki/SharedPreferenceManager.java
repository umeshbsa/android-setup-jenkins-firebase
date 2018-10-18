package com.app.wiki;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPreferenceManager {

    private static SharedPreferenceManager keeper;
    private SharedPreferences prefs;

    private SharedPreferenceManager(Context context) {
        if (context != null)
            prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SharedPreferenceManager getInstance() {
        if (keeper == null) {
            keeper = new SharedPreferenceManager(App.app);
        }
        return keeper;
    }


    public void saveTransactionsInKeeper(List<DataModel> modules) {
        Gson gson = new Gson();
        String json = gson.toJson(modules);
        prefs.edit().putString(Constants.TRANSACTION_ITEM_KEY, json).commit();
    }

    public List<DataModel> getTransactionsFromKeeper() {
        Gson gson = new Gson();
        List<DataModel> transactionItems;
        String string = prefs.getString(Constants.TRANSACTION_ITEM_KEY, null);
        Type type = new TypeToken<List<DataModel>>() {
        }.getType();
        transactionItems = gson.fromJson(string, type);
        if (transactionItems == null) {
            transactionItems = new ArrayList<>();
        }
        return transactionItems;
    }
}
