package com.ingeniods.databasereader.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TablaInfo {
    private String nombre;
    private String tipo;
    private List<CampoInfo> campos;

}