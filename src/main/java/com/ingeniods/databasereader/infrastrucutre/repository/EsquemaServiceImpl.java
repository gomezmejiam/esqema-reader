package com.ingeniods.databasereader.infrastrucutre.repository;

import com.ingeniods.databasereader.domain.entity.CampoInfo;
import com.ingeniods.databasereader.domain.entity.TablaInfo;
import com.ingeniods.databasereader.domain.service.EsquemaService;
import com.ingeniods.databasereader.shared.exception.EsquemaInfoException;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class EsquemaServiceImpl implements EsquemaService {

    private static List<CampoInfo> readCampoInfos(String schema, Connection connection, DatabaseMetaData metaData, String tableName) throws SQLException {
        List<CampoInfo> campos = new ArrayList<>();
        try (ResultSet columnsResultSet = metaData.getColumns(connection.getCatalog(), schema, tableName, null)) {
            while (columnsResultSet.next()) {
                CampoInfo campoInfo = readCampoInfo(columnsResultSet);
                campos.add(campoInfo);
            }
        }
        return campos;
    }

    private static CampoInfo readCampoInfo(ResultSet columnsResultSet) throws SQLException {
        String columnName = columnsResultSet.getString("COLUMN_NAME");
        String columnType = columnsResultSet.getString("TYPE_NAME");
        String columnDescription = columnsResultSet.getString("REMARKS");
        String longitud = String.valueOf(columnsResultSet.getInt("COLUMN_SIZE"));

        if ("DECIMAL".equalsIgnoreCase(columnType) || "NUMERIC".equalsIgnoreCase(columnType)) {
            Integer decimalDigits = columnsResultSet.getInt("DECIMAL_DIGITS");
            longitud = longitud+","+decimalDigits;
        }
        CampoInfo campoInfo = new CampoInfo(columnName, columnType, columnDescription, longitud);
        return campoInfo;
    }

    @Override
    public List<TablaInfo> getEsquemaInfo(String url, String username, String password, String schema) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            //List<String> esquemasDisponibles = listSchemas(url, username, password);
            DatabaseMetaData metaData = connection.getMetaData();

            List<TablaInfo> tablasYVistas = new ArrayList<>();
            String[] tipos = {"TABLE", "VIEW"};
            try (ResultSet tablesResultSet = metaData.getTables(connection.getCatalog(), schema, null, tipos)) {
                while (tablesResultSet.next()) {
                    String tableName = tablesResultSet.getString("TABLE_NAME");
                    String tableType = tablesResultSet.getString("TABLE_TYPE");

                    List<CampoInfo> campos = readCampoInfos(schema, connection, metaData, tableName);

                    tablasYVistas.add(new TablaInfo(tableName, tableType, campos));
                }
            }

            return tablasYVistas;
        } catch (SQLException e) {
            throw new EsquemaInfoException("Error al consultar la estructura del esquema", e);
        }
    }

    @Override
    public List<String> getEsquemas(String url, String username, String password) {
        List<String> schemaNames = new ArrayList<>();

        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection(url, username, password);

            // Execute the query to list all schemas
            String query = "SHOW DATABASES";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Iterate through the ResultSet and add schema names to the list
            while (resultSet.next()) {
                String schemaName = resultSet.getString(1);
                schemaNames.add(schemaName);
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            // Handle exceptions
            e.printStackTrace();
        }

        return schemaNames;
    }

}
