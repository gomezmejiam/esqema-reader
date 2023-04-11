package com.ingeniods.databasereader.infrastrucutre.controller;

import com.ingeniods.databasereader.domain.service.TablaStorageService;
import com.ingeniods.databasereader.infrastrucutre.controller.dto.EsquemaRequest;
import com.ingeniods.databasereader.infrastrucutre.repository.EsquemaServiceImpl;
import com.ingeniods.databasereader.domain.entity.TablaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/html")
public class TablaController {

    @Autowired
    private TablaStorageService tablaStorageService;

    @PostMapping("/application/{application}")
    public String getEsquemaInfo(@PathVariable String application, Model model) {
        Map<String, List<TablaInfo>> result = tablaStorageService.getAllTablasByApplication(application);
        model.addAttribute("tablas", result);
        return "application";
    }

    @PostMapping("/application/{application}/module/{module}")
    public String getEsquemaInfo(@PathVariable String application, @PathVariable String module, Model model) {
        List<TablaInfo> result = tablaStorageService.getTablas(application, module);
        model.addAttribute("tablas", result);
        return "esquema";
    }
}
