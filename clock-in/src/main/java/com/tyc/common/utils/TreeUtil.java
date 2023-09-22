package com.tyc.common.utils;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author TangYiCong
 * @since 2021-09-01
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TreeUtil {

    /**
     * 列表组装成树结构
     *
     * @param list     数据列表
     * @param pid      父id
     * @param pidName  父ID名称
     * @param idName   ID名称
     * @param children 子节点
     * @return {xxx:xxx,children:[]}
     */
    public static List<Map<String, Object>> generator(List<?> list, Long pid, String pidName, String idName, String children) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Object o : list) {
            JSONObject parse = JSON.parseObject(JSON.toJSONString(o));
            if (parse.getLong(pidName).equals(pid)) {
                parse.put(children, generator(list, parse.getLong(idName), pidName, idName, children));
                resultList.add(parse);
            }
        }
        return resultList;
    }

    public static <T> List<T> generator(List<?> list, String pidName, String idName, String children, Object pidValue, Map<String, String> mapping, final Class<T> elementType) {
        List<T> resultList = new ArrayList<>();
        try {
            for (Object o : list) {
                Object pid = BeanUtil.getFieldValue(o, pidName);
                Object id = BeanUtil.getFieldValue(o, idName);
                if (pid.toString().equals(pidValue.toString())) {
                    T newInstance = elementType.newInstance();
                    // 映射字段
                    Set<Map.Entry<String, String>> entrySet = mapping.entrySet();
                    for (Map.Entry<String, String> entry : entrySet) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        Object mappingValue = BeanUtil.getFieldValue(o, key);
                        Field field = elementType.getDeclaredField(value);
                        field.setAccessible(true);
                        field.set(newInstance, mappingValue);
                    }
                    Field field = elementType.getDeclaredField(children);
                    field.setAccessible(true);
                    field.set(newInstance, generator(list, pidName, idName, children, id, mapping, elementType));
                    resultList.add(newInstance);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

}
