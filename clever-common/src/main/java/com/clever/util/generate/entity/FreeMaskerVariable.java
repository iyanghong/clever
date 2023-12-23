package com.clever.util.generate.entity;

import com.clever.util.generate.config.GenerateConfig;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author xixi
 * @Date 2023-12-23 21:19
 **/
public class FreeMaskerVariable {
    private final Map<String, Object> variables;

    public FreeMaskerVariable(GenerateConfig config,TableMeta tableMeta) {
        this.variables = new HashMap<>();
        setVariable("author", "xixi");
        setVariable("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));


        setVariable("appName", config.getAppName());
        setVariable("DB_URL", config.DB_URL);
        setVariable("DB_DATABASE", config.DB_DATABASE);
        setVariable("DB_USERNAME", config.DB_USERNAME);
        setVariable("entityPackageName", config.getEntityPackageName());
        setVariable("mapperPackageName", config.getMapperPackageName());
        setVariable("servicePackageName", config.getServicePackageName());
        setVariable("idFieldName", config.getIdFieldName());
        setVariable("creatorFieldName", config.getCreatorFieldName());
        setVariable("modifierFieldName", config.getModifierFieldName());
        setVariable("createTimeFieldName", config.getCreateTimeFieldName());
        setVariable("modifyTimeFieldName", config.getModifyTimeFieldName());
        setVariable("deleteFlagFieldName", config.getDeleteFlagFieldName());


        setVariable("tableSchema", tableMeta.getTableSchema());
        setVariable("tableName", tableMeta.getTableName());
        setVariable("tableComment", tableMeta.getTableComment());
        setVariable("lowerCamelCaseName", tableMeta.getLowerCamelCaseName());
        setVariable("upperCamelCaseName", tableMeta.getUpperCamelCaseName());
        setVariable("isHasDateTypeColumn", tableMeta.isHasDateTypeColumn());
        setVariable("isHasNeedNotBlankValidate", tableMeta.isHasNeedNotBlankValidate());
        setVariable("primaryKeyColumn", tableMeta.getPrimaryKeyColumn());
        setVariable("commentOrName", tableMeta.getCommentOrName());
        setVariable("commentOrUpperCamelCaseName", tableMeta.getCommentOrUpperCamelCaseName());
        setVariable("commentOrLowerCamelCaseName", tableMeta.getCommentOrLowerCamelCaseName());

        List<Map<String, Object>> columns = getColumns(tableMeta);
        setVariable("columns", columns);
    }

    private static List<Map<String, Object>> getColumns(TableMeta tableMeta) {
        List<Map<String, Object>> columns = new ArrayList<>();
        for (ColumnMeta columnMeta : tableMeta.getColumns()) {
            Map<String, Object> column = new HashMap<>();
            column.put("tableSchema", columnMeta.getTableSchema());
            column.put("tableName", columnMeta.getTableName());
            column.put("columnName", columnMeta.getColumnName());
            column.put("ordinalPosition", columnMeta.getOrdinalPosition());
            column.put("columnDefault", columnMeta.getColumnDefault());
            column.put("nullable", columnMeta.isNullable());
            column.put("dataType", columnMeta.getDataType());
            column.put("characterMaximumLength", columnMeta.getCharacterMaximumLength());
            column.put("columnKey", columnMeta.getColumnKey());
            column.put("columnComment", columnMeta.getColumnComment());
            column.put("javaType", columnMeta.getJavaType());
            column.put("lowerCamelCaseName", columnMeta.getLowerCamelCaseName());
            column.put("upperCamelCaseName", columnMeta.getUpperCamelCaseName());
            column.put("isHasNeedNotBlankValidate", columnMeta.isHasNeedNotBlankValidate());
            column.put("commentOrName", columnMeta.getCommentOrName());
            column.put("commentOrUpperCamelCaseName", columnMeta.getCommentOrUpperCamelCaseName());
            column.put("commentOrLowerCamelCaseName", columnMeta.getCommentOrLowerCamelCaseName());
            columns.add(column);
        }
        return columns;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariable(String key, Object value) {
        this.variables.put(key, value);
    }
}
