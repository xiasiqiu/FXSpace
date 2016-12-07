package com.fx.fxspace.model.api;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Title:
 * Description:
 * Created by Administrator on 2016/12/7.
 * 作者：fx on 2016/12/7 20:32
 */

public class StringConverter implements Converter<ResponseBody ,String> {

    @Override
    public String convert(ResponseBody value) throws IOException {
        return value.string();
    }
}
