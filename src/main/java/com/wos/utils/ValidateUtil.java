package com.wos.utils;



import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

/**
 * @author chenyuwei
 * @date 2018/10/23
 * @description 常用的验证工具集
 */
public class ValidateUtil {


    public static void hasAllRequired(final JSONObject jsonObject,String requiredColumns) throws Exception {
        if (StringUtils.isNotEmpty(requiredColumns)){
            //验证字段非空
            String[] columns = requiredColumns.split(",");
            String missCol = "";
            for (String column : columns) {
                Object val = jsonObject.get(column.trim());
                if (StringUtils.isEmpty(val.toString())) {
                    missCol += column + "  ";
                }
            }
            if (StringUtils.isNotEmpty(missCol)) {
                throw new Exception("缺少必填参数:" + missCol.trim());
            }
        }
    }
}
