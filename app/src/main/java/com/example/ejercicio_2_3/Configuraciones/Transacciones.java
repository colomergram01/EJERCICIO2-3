package com.example.ejercicio_2_3.Configuraciones;

public class Transacciones {

    public static final String NameDatabase = "PM01DB";
    public static final String tablapersonas_imag = "personasImg";
    public static final int VersionDatabase=1;
    public static final String id = "id";
    public static final String descripcion = "descripcion";
    public static final String imagenes = "imagenes";

    public static final String createTablePersonasImg = "CREATE TABLE " + tablapersonas_imag +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "descripcion TEXT, imagenes TEXT, added_timestamp TEXT, updated_timestamp TEXT)";

    public static final String dropTablePersonasImg = "DROP TABLE IF EXIST" + tablapersonas_imag;
    public static final String test1 = "select * from personasImg";


}
