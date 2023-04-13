package com.ingeniods.databasereader.infrastrucutre.controller;

import com.ingeniods.databasereader.domain.entity.TablaInfo;
import com.ingeniods.databasereader.domain.service.TablaStorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/html")
@AllArgsConstructor
public class TablaController {

    private final TablaStorageService tablaStorageService;

    @GetMapping("/application/{application}")
    public String getEsquemaInfo(@PathVariable String application, Model model) {
        Map<String, List<TablaInfo>> result = tablaStorageService.getAllTablasByApplication(application);
        model.addAttribute("tablas", result);
        return "application";
    }

    @GetMapping("/application/{application}/module/{module}")
    public String getEsquemaInfo(@PathVariable String application, @PathVariable String module, Model model) {
        List<TablaInfo> result = tablaStorageService.getTablas(application, module);
        model.addAttribute("tablas", result);
        return "esquema";
    }
}
