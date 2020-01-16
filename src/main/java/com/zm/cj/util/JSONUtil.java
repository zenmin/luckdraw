package com.zm.cj.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.zm.cj.entity.CommonException;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.*;


public final class JSONUtil {

    private JSONUtil() {
    }

    private static final ObjectMapper m =new ObjectMapper();

    public static String toJSONString(Object o) {
        try {
            return JSONObject.toJSONString(o, SerializerFeature.WriteMapNullValue);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 不包含空字段
     *
     * @param o
     * @return
     */
    public static String toJSONStringNotEmpty(Object o) {
        String jsonString = JSONObject.toJSONString(o, SerializerFeature.DisableCircularReferenceDetect);
        return jsonString;
    }

    public static <T> T parseObject(String jsonString, Class<T> elementClasses) {
        m.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            return m.readValue(jsonString, elementClasses);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> parseArray(String jsonString, Class<T> elementClasses) {
        m.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        JavaType javaType = m.getTypeFactory().constructParametricType(ArrayList.class, elementClasses);
        try {
            return m.readValue(jsonString, javaType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean validateJSON(Object o, String field) {
        try {
            JSONObject.parse(o.toString());
            return true;
        } catch (Exception e) {
            throw new CommonException(999, "字段：" + field + " JSON格式异常");
        }
    }

    /**
     * 不包含空字段
     *
     * @param o
     * @return
     */
    public static JSONObject parse(Object o) {
        m.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            m.setSerializationInclusion(Include.NON_NULL);
            return m.readValue(JSONUtil.toJSONStringNotEmpty(o), JSONObject.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断哪些字段被修改
     *
     * @param oldJson
     * @param newJson
     * @return 节点key：节点value
     */
    public static Map<String, Object> compareObject(String oldJson, String newJson) {
        // 如果其中一个为空  无需比较
        if (StringUtils.isBlank(oldJson) || StringUtils.isBlank(newJson)) {
            return null;
        }
        //
        Map<String, Object> map = Maps.newHashMap();
        JSONObject oldObj = parse(oldJson);
        JSONObject newObj = parse(newJson);
        Set<Map.Entry<String, Object>> entrySet = oldObj.entrySet();
        for (Map.Entry<String, Object> m : entrySet) {
            String key = m.getKey();
            Object oldValue = m.getValue();
            Object newValue = newObj.get(key);
            if (Objects.nonNull(newValue)) {
                if (!newValue.equals(oldValue)) {
                    // 被修改
                    map.put(key, newValue);
                }
            }
        }
        return map;
    }

}
