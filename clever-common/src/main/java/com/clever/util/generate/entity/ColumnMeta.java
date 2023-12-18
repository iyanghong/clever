package com.clever.util.generate.entity;

/**
 * @author: xixi
 * @create: 2023-12-14 11:11
 **/
public class ColumnMeta {
    /**
     * 数据库表所属的模式（数据库名）
     */
    private String tableSchema;

    /**
     * 数据库表名
     */
    private String tableName;

    /**
     * 列名
     */
    private String columnName;

    /**
     * 列的顺序位置
     */
    private int ordinalPosition;

    /**
     * 列的默认值
     */
    private String columnDefault;

    /**
     * 列是否可为空
     */
    private boolean nullable;

    /**
     * 列的数据类型
     */
    private String dataType;

    /**
     * 列的最大字符长度（仅适用于字符类型列）
     */
    private int characterMaximumLength;

    /**
     * 列的键类型（如主键、外键等）
     */
    private String columnKey;

    /**
     * 列的注释
     */
    private String columnComment;

    private String javaType;

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public int getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(int ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    public String getColumnDefault() {
        return columnDefault;
    }

    public void setColumnDefault(String columnDefault) {
        this.columnDefault = columnDefault;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getCharacterMaximumLength() {
        return characterMaximumLength;
    }

    public void setCharacterMaximumLength(int characterMaximumLength) {
        this.characterMaximumLength = characterMaximumLength;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }
}
