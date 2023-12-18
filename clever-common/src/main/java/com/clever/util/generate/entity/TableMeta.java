package com.clever.util.generate.entity;

import com.clever.util.generate.entity.ColumnMeta;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 表元数据类
 *
 * @author: xixi
 * @create: 2023-12-14 11:10
 **/
public class TableMeta {
    /**
     * 数据库表所属的模式（数据库名）
     */
    private String tableSchema;

    /**
     * 数据库表名
     */
    private String tableName;

    /**
     * 数据库表的注释
     */
    private String tableComment;

    /**
     * 表的列元数据列表
     */
    private List<ColumnMeta> columns;

    /**
     * 默认构造函数
     */
    public TableMeta() {
    }

    /**
     * 构造函数
     *
     * @param tableSchema  数据库表所属的模式（数据库名）
     * @param tableName    数据库表名
     * @param tableComment 数据库表的注释
     */
    public TableMeta(String tableSchema, String tableName, String tableComment) {
        this.tableSchema = tableSchema;
        this.tableName = tableName;
        this.tableComment = tableComment;
    }

    /**
     * 检查表是否有日期类型的列
     *
     * @return 如果有日期类型的列，返回true，否则返回false
     */
    public boolean isHasDateTypeColumn() {
        for (ColumnMeta column : columns) {
            if (column.getJavaType().equals("Date")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测是否存在某个字段名
     *
     * @param name 字段名
     * @return 如果有指定列名的列，返回true，否则返回false
     */
    public boolean isHasColumnName(String name) {
        if (StringUtils.isNotBlank(name)) return false;
        for (ColumnMeta column : columns) {
            if (column.getColumnName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取主键列的元数据
     *
     * @return 如果有主键列，返回该列的元数据，否则返回null
     */
    public ColumnMeta getPrimaryKeyColumn() {
        for (ColumnMeta column : columns) {
            if (column.getColumnKey().equals("PRI")) {
                return column;
            }
        }
        return null;
    }

    /**
     * 获取列元数据列表
     *
     * @return 列元数据列表
     */
    public List<ColumnMeta> getColumns() {
        return columns;
    }

    /**
     * 设置列元数据列表
     *
     * @param columns 列元数据列表
     */
    public void setColumns(List<ColumnMeta> columns) {
        this.columns = columns;
    }

    /**
     * 获取数据库表所属的模式（数据库名）
     *
     * @return 数据库表所属的模式（数据库名）
     */
    public String getTableSchema() {
        return tableSchema;
    }

    /**
     * 设置数据库表所属的模式（数据库名）
     *
     * @param tableSchema 数据库表所属的模式（数据库名）
     */
    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    /**
     * 获取数据库表名
     *
     * @return 数据库表名
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * 设置数据库表名
     *
     * @param tableName 数据库表名
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * 获取数据库表的注释
     *
     * @return 数据库表的注释
     */
    public String getTableComment() {
        return tableComment;
    }

    /**
     * 设置数据库表的注释
     *
     * @param tableComment 数据库表的注释
     */
    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }
}
