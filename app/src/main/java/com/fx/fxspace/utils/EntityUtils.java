package com.fx.fxspace.utils;

import com.fx.fxspace.model.util.DataTimeTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

/**
 * Title:
 * Description:
 * Created by Administrator on 2016/12/7.
 * 作者：fx on 2016/12/7 20:33
 */

public class EntityUtils {
    private EntityUtils(){}
    public static final Gson gson=new GsonBuilder().registerTypeAdapter(Date.class,new DataTimeTypeAdapter()).create();

}
