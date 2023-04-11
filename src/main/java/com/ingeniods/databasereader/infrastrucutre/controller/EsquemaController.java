package com.ingeniods.databasereader.infrastrucutre.controller;

import com.ingeniods.databasereader.application.GenerateSchema;
import com.ingeniods.databasereader.infrastrucutre.controller.dto.EsquemaRequest;
import com.ingeniods.databasereader.domain.service.EsquemaService;
import com.ingeniods.databasereader.domain.entity.TablaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EsquemaController {

    @Autowired
    private GenerateSchema generateSchema;

    @Autowired
    private EsquemaService esquemaService;

    @PostMapping("/{application}/module/{module}")
    public ResponseEntity<List<TablaInfo>> getEsquemaInfo(
            @PathVariable String application,
            @PathVariable String module,
            @RequestBody EsquemaRequest request) {
        List<TablaInfo> result = generateSchema.fetchAndStoreEsquemaInfo(application, module, request);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/schemas")
    public ResponseEntity<List<String>> getEsquemas(@RequestBody EsquemaRequest request) {
        List<String> result = esquemaService.getEsquemas (request.getUrl(), request.getUsername(), request.getPassword());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}