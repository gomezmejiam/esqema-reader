package com.ingeniods.databasereader.infrastrucutre.controller;

import com.ingeniods.databasereader.application.GenerateSchema;
import com.ingeniods.databasereader.domain.entity.TablaInfo;
import com.ingeniods.databasereader.domain.service.EsquemaService;
import com.ingeniods.databasereader.domain.service.TablaStorageService;
import com.ingeniods.databasereader.infrastrucutre.controller.dto.EsquemaRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class EsquemaController {

    private final GenerateSchema generateSchema;

    private final EsquemaService esquemaService;

    private final TablaStorageService tablaStorageService;

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
        List<String> result = esquemaService.getEsquemas(request.getUrl(), request.getUsername(), request.getPassword());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{application}/module/{module}")
    public ResponseEntity<Void> deleteEsquemaByApplicationAndSchema(
            @PathVariable String application,
            @PathVariable String module) {
        tablaStorageService.deleteTablasByApplicationAndSchema(application, module);
        return ResponseEntity.noContent().build();
    }
}