package com.ingeniods.databasereader.application;


import com.ingeniods.databasereader.domain.entity.TablaInfo;
import com.ingeniods.databasereader.domain.service.EsquemaService;
import com.ingeniods.databasereader.domain.service.TablaStorageService;
import com.ingeniods.databasereader.infrastrucutre.controller.dto.EsquemaRequest;
import lombok.AllArgsConstructor;

import java.util.List;


@AllArgsConstructor
public class GenerateSchema {

    private EsquemaService esquemaService;

    private TablaStorageService tablaStorageService;

    public List<TablaInfo> fetchAndStoreEsquemaInfo(String application, String module, EsquemaRequest request) {
        List<TablaInfo> result = esquemaService.getEsquemaInfo(request.getUrl(), request.getUsername(), request.getPassword(), request.getSchema());
        tablaStorageService.storeTablas(application, module, result);
        return result;
    }

}
