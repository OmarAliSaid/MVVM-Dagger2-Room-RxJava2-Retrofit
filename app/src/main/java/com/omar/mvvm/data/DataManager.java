package com.omar.mvvm.data;

import com.omar.mvvm.data.local.db.DbHelper;
import com.omar.mvvm.data.remote.ApiService;
import com.omar.mvvm.data.local.prefs.PreferenceHelper;

public interface DataManager extends ApiService , PreferenceHelper , DbHelper{

}
