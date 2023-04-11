package com.ingeniods.databasereader.infrastrucutre.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EsquemaRequest {
    private String url;
    private String username;
    private String password;
    private String schema;


}
