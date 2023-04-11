package com.ingeniods.databasereader.infrastrucutre.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingeniods.databasereader.domain.entity.TablaInfo;
import com.ingeniods.databasereader.domain.service.TablaStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RedisTablaStorageService implements TablaStorageService {

    @Autowired
    private Jedis jedis;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void storeTablas(String application, String module, List<TablaInfo> tablas) {
        String redisKey = application + "_" + module;
        try {
            String jsonResult = objectMapper.writeValueAsString(tablas);
            jedis.set(redisKey, jsonResult);
        } catch (IOException e) {
            throw new RuntimeException("Error al serializar el listado de tablas", e);
        }
    }

    @Override
    public List<TablaInfo> getTablas(String application, String module) {
        String redisKey = application + "_" + module;
        String jsonResult = jedis.get(redisKey);

        if (jsonResult != null) {
            try {
                return objectMapper.readValue(jsonResult, new TypeReference<List<TablaInfo>>() {});
            } catch (IOException e) {
                throw new RuntimeException("Error al deserializar el listado de tablas", e);
            }
        }

        return null;
    }

    @Override
    public Map<String, List<TablaInfo>> getAllTablasByApplication(String application) {
        Map<String, List<TablaInfo>> resultMap = new HashMap<>();

        for (String key : jedis.keys(application + "_*")) {
            String schema = key.substring(key.indexOf('_') + 1);
            String jsonResult = jedis.get(key);

            if (jsonResult != null) {
                try {
                    List<TablaInfo> tablas = objectMapper.readValue(jsonResult, new TypeReference<List<TablaInfo>>() {});
                    resultMap.put(schema, tablas);
                } catch (IOException e) {
                    throw new RuntimeException("Error al deserializar el listado de tablas", e);
                }
            }
        }

        return resultMap;
    }
}