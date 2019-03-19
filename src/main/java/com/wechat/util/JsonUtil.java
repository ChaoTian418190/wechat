package com.wechat.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil
{
    private static ObjectMapper MAPPER = new ObjectMapper();
    static{
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    /**
     * @MethodName readValueToBean
     * @Description 将json转为对象
     * 
     * @author chenpy
     * @date 2015年10月14日 
     * @param json
     * @param clazz
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static <X> X readValueToBean(String json, Class<X> clazz)
    {
        try
        {
            return MAPPER.readValue(json, clazz);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    

    /**
     * @MethodName readValueToBeanArray
     * @Description 将json转为对象数组
     * 
      * @author chenpy
     * @date 2015年10月14日 
     * @param json
     * @param clazz
     * @return
     */
    public static <X> X[] readValueToBeanArray(String json, Class<X> clazz)
    {
        try
        {
            return MAPPER.readValue(json, MAPPER.getTypeFactory()
                    .constructArrayType(clazz));
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * @MethodName readValueToBeanList
     * @Description 将json转为对象集合
     * 
     * @author chenpy
     * @date 2015年10月14日 
     * @param json
     * @param clazz
     * @return
     */
    public static <X> List<X> readValueToBeanList(String json, Class<X> clazz)
    {
        try
        {
            return MAPPER.readValue(json, MAPPER.getTypeFactory()
                    .constructCollectionType(List.class, clazz));

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * @MethodName readValueToMap
     * @Description 将json转为Map
     * 
     * @author chenpy
     * @date 2015年10月14日 
     * @param json
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> readValueToMap(String json)
    {
        try
        {
            return MAPPER.readValue(json, Map.class);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * @MethodName writeValueAsString
     * @Description 将对象转为json字符串
     * 
     * @author chenpy
     * @date 2015年10月14日 
     * @param value
     * @return
     * @throws JsonProcessingException
     */
    public static String writeValueAsString(Object value)
    {
        try
        {
            return MAPPER.writeValueAsString(value);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * @MethodName convertJsonString
     * @Description 数组转json
     * 
     * @author chenpy
     * @date 2015年10月14日 
     * @param keys
     * @param values
     * @return
     */
    public static String convertArrayToJson(String[] keys, Object... values)
    {
        if (ArrayUtils.isEmpty(keys))
            return null;
        if (keys.length != values.length)
            throw new RuntimeException("json转换失败,键值对数目不等!");
        StringBuffer sb = new StringBuffer("{");
        for (int i = 0; i < keys.length; i++)
        {
            sb.append("\"" + keys[i] + "\":");
            if (String.class.isAssignableFrom(values[i].getClass()))
            {
                sb.append("\"" + values[i] + "\"");
            }
            else
            {
                sb.append(values[i]);
            }
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * @MethodName getJsonNodeValue
     * @Description 获取节点值
     * 
     * @author chenpy
     * @date 2015年10月14日 
     * @param node
     * @return
     */
    public static String getJsonNodeValue(JsonNode node)
    {
        return node.toString();
    }

    /**
     * @MethodName getChildJsonNodeValue
     * @Description 获取子节点节点值
     * 
     * @author chenpy
     * @date 2015年10月14日 
     * @param node
     * @param name
     * @return
     */
    public static String getChildJsonNodeValue(JsonNode node, String name)
    {
        return getJsonNodeValue(node.get(name));
    }

    /**
     * @MethodName covertNodeTree
     * @Description 将json转为json节点树
     * 
     * @author chenpy
     * @date 2015年10月14日 
     * @param json
     * @return
     */
    public static JsonNode covertNodeTree(String json)
    {
        try
        {
            return MAPPER.readTree(json);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }

    /**
     * @MethodName getJsonValueByName
     * @Description 获取json字符串中某节点的值
     * 
     * @author chenpy
     * @date 2015年10月14日 
     * @param json
     * @param name
     * @return
     */
    public static String getJsonValueByName(String json, String name)
    {
        return getChildJsonNodeValue(covertNodeTree(json), name);
    }

}
