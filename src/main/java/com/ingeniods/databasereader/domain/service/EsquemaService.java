package com.ingeniods.databasereader.domain.service;

import com.ingeniods.databasereader.domain.entity.TablaInfo;

import java.util.List;

public interface EsquemaService {

    List<TablaInfo> getEsquemaInfo(String url, String username, String password, String schema);

    List<String> getEsquemas(String url, String username, String password);
}
