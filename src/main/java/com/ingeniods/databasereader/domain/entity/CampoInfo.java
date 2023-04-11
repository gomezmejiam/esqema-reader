package com.ingeniods.databasereader.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampoInfo {

    private String nombre;
    private String tipo;
    private String descripcion;

    private String longitud;

}
