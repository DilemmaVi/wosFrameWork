package com.wos.pojo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * @author chenyuwei
 * @date 2018/9/9
 */
public class WosReturnModel {

    /**
     *定义jackson对象
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     *响应业务状态
     */
    private Integer status;

    /**
     *响应消息
     */
    private String msg;

    /**
     *响应中的数据
     */
    private Object data;

    /**
     * 不使用
     */
    private String ok;

    public static WosReturnModel build(Integer status, String msg, Object data) {
        return new WosReturnModel(status, msg, data);
    }

    public static WosReturnModel ok(Object data) {
        return new WosReturnModel(data);
    }

    public static WosReturnModel ok() {
        return new WosReturnModel(null);
    }

    public static WosReturnModel errorMsg(String msg) {
        return new WosReturnModel(500, msg, null);
    }

    public static WosReturnModel errorMap(Object data) {
        return new WosReturnModel(501, "error", data);
    }

    public static WosReturnModel errorTokenMsg(String msg) {
        return new WosReturnModel(502, msg, null);
    }

    public static WosReturnModel errorException(String msg) {
        return new WosReturnModel(555, msg, null);
    }

    public WosReturnModel() {

    }


    public WosReturnModel(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public WosReturnModel(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     *
     * @Description: 将json结果集转化为JSONResult对象
     *              需要转换的对象是一个类
     * @param jsonData
     * @param clazz
     * @return
     *
     * @author DielmamVi
     * @date 2018年9月9日
     */
    public static WosReturnModel formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, WosReturnModel.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     *
     * @Description: 没有object对象的转化
     * @param json
     * @return
     *
     * @author DielmamVi
     * @date 2018年9月9日
     */
    public static WosReturnModel format(String json) {
        try {
            return MAPPER.readValue(json, WosReturnModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @Description: Object是集合转化
     *              需要转换的对象是一个list
     * @param jsonData
     * @param clazz
     * @return
     *
     * @author DielmamVi
     * @date 2018年9月9日
     */
    public static WosReturnModel formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }
}
