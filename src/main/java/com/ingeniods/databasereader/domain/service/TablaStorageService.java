package com.ingeniods.databasereader.domain.service;

import com.ingeniods.databasereader.domain.entity.TablaInfo;

import java.util.List;
import java.util.Map;

public interface TablaStorageService {

    void storeTablas(String application, String module, List<TablaInfo> tablas);

    List<TablaInfo> getTablas(String application, String module);

    Map<String, List<TablaInfo>> getAllTablasByApplication(String application);

}
