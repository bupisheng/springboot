package com.springboot.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


/**
 * JSON工具类
 */
public class JSONUtil {
	
    /**
     * Json String convert to object with javaBean
     */
    public static <T> T jsonToObject(String jsonStr, Class<T> clz) {
        if (StringUtil.isEmpty(jsonStr)) {
            return null;
        }
        ObjectMapper mapper = getObjectMapper();
		try {
			return mapper.readValue(jsonStr, clz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
    
    /**
     * json string convert to map
     */
    @SuppressWarnings("unchecked")
	public static Map<String, Object> jsonToMap(String jsonStr){
    	ObjectMapper mapper = getObjectMapper();
        try {
			return mapper.readValue(jsonStr, Map.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
    }
    
    /**
     * json string convert to SortedMap
     */
    @SuppressWarnings("unchecked")
	public static Map<String, Object> jsonToSortedMap(String jsonStr){
    	ObjectMapper mapper = getObjectMapper();
        try {
			return mapper.readValue(jsonStr, SortedMap.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
    }
    
    /**
     * json string convert to map with javaBean
     */
    public static <T> Map<String, T> jsonToMap(String jsonStr, Class<T> clazz){
    	ObjectMapper mapper = getObjectMapper();
        Map<String, Map<String, Object>> map;
        Map<String, T> result = new HashMap<String, T>();
		try {
			map = mapper.readValue(jsonStr,
			        new TypeReference<Map<String, T>>() {
			        });
			for (Entry<String, Map<String, Object>> entry : map.entrySet()) {
		            result.put(entry.getKey(), mapToObject(entry.getValue(), clazz));
		    }
		    return result;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }
    
    /**
     * json array string convert to list with javaBean
     */
    public static <T> List<T> jsonToList(String jsonArrayStr, Class<T> clazz)
            throws Exception {
    	ObjectMapper mapper = getObjectMapper();
        List<Map<String, Object>> list = mapper.readValue(jsonArrayStr,
                new TypeReference<List<T>>() {
                });
        List<T> result = new ArrayList<T>();
        for (Map<String, Object> map : list) {
            result.add(mapToObject(map, clazz));
        }
        return result;
    }
    
    public static  List<String> jsonToStringList(String jsonArrayStr)
            throws Exception {
    	ObjectMapper mapper = getObjectMapper();
        List<String> list = mapper.readValue(jsonArrayStr,
                new TypeReference<List<String>>() {
                });
        List<String> result = new ArrayList<String>();
        for (String str : list) {
            result.add(str);
        }
        return result;
    }

    /**
     *Json String convert to object with TypeReference
     */
    public static <T> T jsonToObject(String jsonStr, TypeReference<T> typeRef) {
    	if (StringUtil.isEmpty(jsonStr)) {
    		return null;
    	}
    	ObjectMapper mapper = getObjectMapper();
    	try {
    		return mapper.readValue(jsonStr, typeRef);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }

    /**
     * object convert to Json String
     */
    public static String objectToJson(Object object) {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = getObjectMapper();
        try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        return null;
    }
    
    /**
     * map convert to javaBean
     */
    public static <T> T mapToObject(Map<String,Object> map, Class<T> clazz) {
    	ObjectMapper mapper = getObjectMapper();
        return mapper.convertValue(map, clazz);
    }
    
    /**
     * javaBean convert to map
     */
    public static  Map<String,Object> objectToMap(Object object){
    	String json = objectToJson(object);
    	return jsonToMap(json);
    }
    
    /**
     * javaBean convert to SortedMap
     */
    public static  Map<String,Object> objectToSortedMap(Object object){
    	String json = objectToJson(object);
    	return jsonToSortedMap(json);
    }
    
    private static ObjectMapper getObjectMapper() {
    	ObjectMapper objectMapper = new ObjectMapper();
    	objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
    	objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);  
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")); 
        return objectMapper;
    }
}
