package com.clever.util.generate;

import com.clever.util.generate.config.GenerateConfig;
import com.clever.util.generate.entity.ColumnMeta;
import com.clever.util.generate.entity.TableMeta;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author xixi
 * @Date 2023-12-18 11:34
 **/
public class GenerateService extends BaseGenerator {
    private static final Logger log = LoggerFactory.getLogger(GenerateService.class);
    private final List<String> allowSearchColumnNames = Arrays.asList("_id", "name", "email", "phone", "status", "sex", "gender","account","type","code","host","title");
    public GenerateService(GenerateConfig config) {
        super(config);
    }

    public GenerateService(GenerateConfig config, String entityPackageName, String mapperPackageName) {
        super(config);
        config.setEntityPackageName(entityPackageName);
        config.setMapperPackageName(mapperPackageName);
    }

    /**
     * 生成service接口和实现类
     * @param tableMetaList 表列表
     * @param packageName   包名
     * @param basePath      基础路径
     */
    @Override
    protected void handler(List<TableMeta> tableMetaList, String packageName, String basePath) {
        // 遍历表元数据列表
        for (TableMeta tableMeta : tableMetaList) {
            // 将表名转换为DTCamelCase
            String dtName = toDTCamelCase(tableMeta.getTableName());
            // 将表名转换为XTCamelCase
            String xtName = toXTCamelCase(tableMeta.getTableName());
            // 初始化接口字符串构建器
            StringBuilder interfaceBuilder = initInterfaceStringBuilder(tableMeta,packageName, dtName);
            // 初始化实现字符串构建器
            StringBuilder implementBuilder = initImplementBuilder(tableMeta,packageName, dtName, xtName);
            // 获取表的主键列
            ColumnMeta primaryKeyColumn = tableMeta.getPrimaryKeyColumn();

            // 构建查询分页接口
            buildSelectPage(interfaceBuilder, implementBuilder, tableMeta, dtName, xtName);
            // 如果表有主键列
            if (primaryKeyColumn != null) {
                // 构建根据主键查询接口
                buildSelectById(interfaceBuilder, implementBuilder, tableMeta, primaryKeyColumn, dtName, xtName);
            }
            // 遍历表的列
            for (ColumnMeta columnMeta : tableMeta.getColumns()) {
                // 如果列名以_id结尾
                if (columnMeta.getColumnName().endsWith("_id")) {
                    // 构建根据外键查询接口
                    buildGetListByForeignKey(interfaceBuilder, implementBuilder, tableMeta, columnMeta, dtName, xtName);
                }
            }
            // 如果表有主键列
            if (primaryKeyColumn != null) {
                // 构建保存接口
                buildSave(interfaceBuilder, implementBuilder, tableMeta, primaryKeyColumn, dtName, xtName);
                // 构建删除接口
                buildDelete(interfaceBuilder, implementBuilder, tableMeta, primaryKeyColumn, dtName, xtName);
            }
            // 遍历表的列
            for (ColumnMeta columnMeta : tableMeta.getColumns()) {
                // 如果列名以_id结尾
                if (columnMeta.getColumnName().endsWith("_id")) {
                    // 构建根据外键删除接口

                    buildDeleteByForeignKey(interfaceBuilder, implementBuilder, tableMeta, columnMeta, dtName, xtName);
                }
            }
            // 添加接口构建器结束符
            interfaceBuilder.append("}\n");
            // 添加实现构建器结束符
            implementBuilder.append("}\n");


            // 获取接口文件路径
            String interfaceFilePath = Paths.get(getBasePathOrCreate(basePath), dtName + "Service.java").toString();
            // 写入文件
            try (FileWriter writer = new FileWriter(interfaceFilePath)) {
                // 将接口构建器写入文件
                writer.write(interfaceBuilder.toString());
                // 打印接口文件生成完成信息
                log.info("The table's service interface class generate complete. table = '{}' filePath = {}", tableMeta.getTableName(), interfaceFilePath);
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file: " + e.getMessage());
            }

            // 获取实现文件路径
            String implementFilePath = Paths.get(getBasePathOrCreate(Paths.get(basePath, "impl").toString()), dtName + "ServiceImpl.java").toString();

            // 写入文件
            try (FileWriter writer = new FileWriter(implementFilePath)) {
                // 将实现构建器写入文件
                writer.write(implementBuilder.toString());
                // 打印实现文件生成完成信息
                log.info("The table's service class generate complete. table = '{}' filePath = {}", tableMeta.getTableName(), implementFilePath);
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file: " + e.getMessage());
            }
        }
    }

    /**
     * 初始化接口字符串构建器
     * @param tableMeta 表元数据
     * @param packageName 包名
     * @param dtName 表名
     * @return 接口字符串构建器
     */
    private StringBuilder initInterfaceStringBuilder(TableMeta tableMeta, String packageName, String dtName) {
        StringBuilder interfaceBuilder = new StringBuilder();
        // 添加包名
        interfaceBuilder.append("package ").append(packageName).append(";\n\n");
        // 添加导入类
        interfaceBuilder.append("import com.baomidou.mybatisplus.extension.plugins.pagination.Page;\n");
        interfaceBuilder.append("import com.clever.bean.model.OnlineUser;\n");
        interfaceBuilder.append("import java.util.List;\n");
        interfaceBuilder.append(String.format("import %s.%s;\n", config.getEntityPackageName(), dtName));
        // 添加类注释
        interfaceBuilder.append("\n/**\n");
        interfaceBuilder.append(String.format(" * %s服务接口\n", StringUtils.isNotBlank(tableMeta.getTableComment())?tableMeta.getTableComment():dtName));
        interfaceBuilder.append(" *\n");
        interfaceBuilder.append(" * @Author xixi\n");
        interfaceBuilder.append(String.format(" * @Date %s\n", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())));
        interfaceBuilder.append(" */\n");
        interfaceBuilder.append("public interface ").append(dtName).append("Service {\n");
        return interfaceBuilder;
    }

    /**
     * 初始化实现字符串构建器
     * @param tableMeta 表元数据
     * @param packageName 包名
     * @param dtName 大驼峰表名
     * @param xtName 小驼峰表名
     * @return 实现字符串构建器
     */
    private StringBuilder initImplementBuilder(TableMeta tableMeta,String packageName, String dtName, String xtName) {
        StringBuilder implementBuilder = new StringBuilder();
        // 添加package声明
        implementBuilder.append("package ").append(packageName).append(".impl;\n\n");
        // 添加import
        implementBuilder.append("import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;\n");
        implementBuilder.append("import com.baomidou.mybatisplus.extension.plugins.pagination.Page;\n");
        implementBuilder.append("import com.clever.bean.model.OnlineUser;\n");
        implementBuilder.append("import org.apache.commons.lang3.StringUtils;\n");
        implementBuilder.append("import org.slf4j.Logger;\n");
        implementBuilder.append("import org.slf4j.LoggerFactory;\n");
        implementBuilder.append("import org.springframework.stereotype.Service;\n");
        implementBuilder.append("import java.util.List;\n");
        implementBuilder.append(String.format("import %s.%sMapper;\n", config.getMapperPackageName(), dtName));
        implementBuilder.append(String.format("import %s.%s;\n", config.getEntityPackageName(), dtName));
        implementBuilder.append(String.format("import com.clever.service.%sService;\n", dtName));
        implementBuilder.append("import javax.annotation.Resource;\n");


        // 添加注释
        implementBuilder.append("\n/**\n");
        implementBuilder.append(String.format(" * %s服务\n", StringUtils.isNotBlank(tableMeta.getTableComment())?tableMeta.getTableComment():dtName));
        implementBuilder.append(" *\n");
        implementBuilder.append(" * @Author xixi\n");
        implementBuilder.append(String.format(" * @Date %s\n", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())));
        implementBuilder.append(" */\n");
        // 添加service声明
        implementBuilder.append("@Service\n");
        // 添加类声明
        implementBuilder.append(String.format("public class %sServiceImpl implements %sService {\n", dtName, dtName));
        implementBuilder.append(String.format("\n\tprivate final static Logger log = LoggerFactory.getLogger(%sServiceImpl.class);\n", dtName));
        // 添加属性声明
        implementBuilder.append("\n\t@Resource\n");
        implementBuilder.append(String.format("\tprivate %sMapper %sMapper;\n", dtName, xtName));
        return implementBuilder;
    }


    /**
     * 构建查询分页接口
     * @param interfaceBuilder 接口构建器
     * @param implBuilder 实现构建器
     * @param tableMeta 表元数据
     * @param dtName 大驼峰表名
     * @param xtName 小驼峰表名
     */
    private void buildSelectPage(StringBuilder interfaceBuilder, StringBuilder implBuilder, TableMeta tableMeta, String dtName, String xtName) {
        // 创建一个TreeMap对象，用于存储注释参数
        LinkedHashMap<String, String> commentParams = new LinkedHashMap<>();
        // 将页码和每页记录数放入注释参数中
        commentParams.put("pageNumber", "页码");
        commentParams.put("pageSize", "每页记录数");

        // 创建一个ArrayList对象，用于存储允许搜索的列
        List<ColumnMeta> allowSearchColumns = new ArrayList<>();
        // 遍历表中的每一列
        for (ColumnMeta columnMeta : tableMeta.getColumns()) {
            for (String allowSearchColumnName : allowSearchColumnNames) {
                // 如果允许搜索的列名列表中包含当前列名
                if (columnMeta.getColumnName().endsWith(allowSearchColumnName)) {
                    // 将当前列添加到允许搜索的列列表中
                    allowSearchColumns.add(columnMeta);
                    // 将当前列名和注释放入注释参数中
                    commentParams.put(toXTCamelCase(columnMeta.getColumnName()), columnMeta.getColumnComment());
                }
            }
        }

        // 获取函数注释
        String functionComment = getFunctionComment(String.format("分页查询%s列表", tableMeta.getTableComment()), commentParams, "Page<" + dtName + ">");


        // 将函数注释添加到接口构建器中
        interfaceBuilder.append("\n").append(functionComment);
        // 构建接口构建器中的代码
        interfaceBuilder.append(String.format("\tPage<%s> selectPage(Integer pageNumber, Integer pageSize", dtName));
        // 遍历允许搜索的列
        for (ColumnMeta columnMeta : allowSearchColumns) {
            // 将允许搜索的列名添加到接口构建器中
            interfaceBuilder.append(String.format(", String %s", toXTCamelCase(columnMeta.getColumnName())));
        }

        // 将接口构建器中的代码添加换行符
        interfaceBuilder.append(");\n");


        // 将函数注释添加到实现构建器中
        implBuilder.append("\n").append(functionComment);
        // 构建实现构建器中的代码
        implBuilder.append("\t@Override\n");
        implBuilder.append(String.format("\tpublic Page<%s> selectPage(Integer pageNumber, Integer pageSize", dtName));
        // 遍历允许搜索的列
        for (ColumnMeta columnMeta : allowSearchColumns) {
            // 将允许搜索的列名添加到实现构建器中
            implBuilder.append(String.format(", String %s", toXTCamelCase(columnMeta.getColumnName())));
        }
        implBuilder.append(") {\n");
        implBuilder.append(String.format("\t\tQueryWrapper<%s> queryWrapper = new QueryWrapper<>();\n", dtName));
        // 遍历允许搜索的列
        for (ColumnMeta columnMeta : allowSearchColumns) {
            implBuilder.append(String.format("\t\tif (StringUtils.isNotBlank(%s)) {\n", toXTCamelCase(columnMeta.getColumnName())));
            implBuilder.append(String.format("\t\t\tqueryWrapper.eq(\"%s\", %s);\n", columnMeta.getColumnName(), toXTCamelCase(columnMeta.getColumnName())));
            implBuilder.append("\t\t}\n");
        }
        implBuilder.append(String.format("\t\treturn %sMapper.selectPage(new Page<%s>(pageNumber, pageSize), queryWrapper);\n", xtName, dtName));
        implBuilder.append("\t}\n");

    }


    /**
     * 构建根据主键查询接口
     * @param interfaceBuilder 接口构建器
     * @param implBuilder 实现构建器
     * @param tableMeta 表元数据
     * @param primaryKeyColumn 主键列
     * @param dtName 大驼峰表名
     * @param xtName 小驼峰表名
     */
    private void buildSelectById(StringBuilder interfaceBuilder, StringBuilder implBuilder, TableMeta tableMeta, ColumnMeta primaryKeyColumn, String dtName, String xtName) {
        String xtColumnName = toXTCamelCase(primaryKeyColumn.getColumnName());
        LinkedHashMap<String, String> columnMap = new LinkedHashMap<>();
        columnMap.put(xtColumnName, primaryKeyColumn.getColumnComment());
        String functionComment = getFunctionComment(String.format("根据%s获取%s信息", StringUtils.isNotBlank(primaryKeyColumn.getColumnComment()) ? primaryKeyColumn.getColumnComment() : primaryKeyColumn.getColumnName(), tableMeta.getTableComment()), columnMap, String.format("List<%s> %s信息", dtName, StringUtils.isNotBlank(tableMeta.getTableComment()) ? tableMeta.getTableComment() : tableMeta.getTableName()));

        interfaceBuilder.append("\n").append(functionComment);
        interfaceBuilder.append(String.format("\t%s selectById(%s %s);\n", dtName, primaryKeyColumn.getJavaType(), xtColumnName));


        implBuilder.append("\n").append(functionComment);
        implBuilder.append("\t@Override\n");
        implBuilder.append(String.format("\tpublic %s selectById(%s %s) {\n", dtName, primaryKeyColumn.getJavaType(), xtColumnName));
        implBuilder.append(String.format("\t\treturn %sMapper.selectById(%s);\n", xtName, xtColumnName));
        implBuilder.append("\t}\n");
    }

    /**
     * 构建保存接口
     * @param interfaceBuilder 接口构建器
     * @param implBuilder 实现构建器
     * @param tableMeta 表元数据
     * @param primaryKeyColumn 主键列
     * @param dtName 大驼峰表名
     * @param xtName 小驼峰表名
     */
    private void buildSave(StringBuilder interfaceBuilder, StringBuilder implBuilder, TableMeta tableMeta, ColumnMeta primaryKeyColumn, String dtName, String xtName) {
        String tableComment = StringUtils.isNotBlank(tableMeta.getTableComment()) ? tableMeta.getTableComment() : tableMeta.getTableName();
        LinkedHashMap<String, String> saveParamMap = new LinkedHashMap<>();
        saveParamMap.put(xtName, String.format("%s实体信息", tableComment));
        saveParamMap.put("onlineUser", "当前登录用户");
        String functionComment = getFunctionComment(String.format("保存%s信息", tableComment), saveParamMap, "void");

        interfaceBuilder.append("\n").append(functionComment);
        interfaceBuilder.append(String.format("\tvoid save(%s %s, OnlineUser onlineUser);\n", dtName, xtName));


        implBuilder.append("\n").append(functionComment);
        implBuilder.append("\t@Override\n");
        implBuilder.append(String.format("\tpublic void save(%s %s, OnlineUser onlineUser) {\n", dtName, xtName));

        // 判断是否有主键
        if (tableMeta.getPrimaryKeyColumn() != null && tableMeta.getPrimaryKeyColumn().getJavaType().equals("Integer")) {
            implBuilder.append(String.format("\t\tif (%s.getId() == null) {", xtName));
        } else {
            implBuilder.append(String.format("\t\tif (StringUtils.isBlank(%s.getId())) {", xtName));
        }
        // 如果有创建者字段
        if (tableMeta.isHasColumnName(config.getCreateUserColumndName())) {
            implBuilder.append(String.format("\t\t\t%s.set%s(onlineUser.getId());\n", xtName, toDTCamelCase(config.getCreateUserColumndName())));
        }
        implBuilder.append(String.format("\n\t\t\t%sMapper.insert(%s);\n", xtName, xtName));
        implBuilder.append(String.format("\t\t\tlog.info(\"%s, %s信息创建成功: userId={}, %sId={}\", onlineUser.getId(), %s.getId());\n", tableMeta.getTableComment(), tableMeta.getTableComment(), xtName, xtName));
        implBuilder.append("\t\t} else {\n");
        // 如果有修改者字段
        if (tableMeta.isHasColumnName(config.getUpdateUserColumndName())) {
            implBuilder.append(String.format("\t\t\t%s.set%s(onlineUser.getId());\n", xtName, toDTCamelCase(config.getUpdateUserColumndName())));
        }
        implBuilder.append(String.format("\t\t\t%sMapper.updateById(%s);\n", xtName, xtName));
        implBuilder.append(String.format("\t\t\tlog.info(\"%s, %s信息修改成功: userId={}, %sId={}\", onlineUser.getId(), %s.getId());\n", tableMeta.getTableComment(), tableMeta.getTableComment(), xtName, xtName));
        implBuilder.append("\t\t}\n");
        implBuilder.append("\t}\n");
    }

    /**
     * 构建删除接口
     * @param interfaceBuilder 接口构建器
     * @param implBuilder 实现构建器
     * @param tableMeta 表元数据
     * @param primaryKeyColumn 主键列
     * @param dtName 大驼峰表名
     * @param xtName 小驼峰表名
     */
    private void buildDelete(StringBuilder interfaceBuilder, StringBuilder implBuilder, TableMeta tableMeta, ColumnMeta primaryKeyColumn, String dtName, String xtName) {

        // 构建删除参数
        LinkedHashMap<String, String> deleteParamMap = new LinkedHashMap<>();
        deleteParamMap.put(toXTCamelCase(primaryKeyColumn.getColumnName()), primaryKeyColumn.getColumnComment());
        deleteParamMap.put("onlineUser", "当前登录用户");
        String functionComment = getFunctionComment(String.format("根据%s获取%s列表", StringUtils.isNotBlank(primaryKeyColumn.getColumnComment()) ? primaryKeyColumn.getColumnComment() : primaryKeyColumn.getColumnName(), tableMeta.getTableComment()), deleteParamMap, "void");

        interfaceBuilder.append("\n").append(functionComment);
        interfaceBuilder.append(String.format("\tvoid delete(%s id, OnlineUser onlineUser);\n", primaryKeyColumn.getJavaType()));

        implBuilder.append("\n").append(functionComment);
        implBuilder.append("\t@Override\n");
        implBuilder.append(String.format("\tpublic void delete(%s id, OnlineUser onlineUser) {\n", primaryKeyColumn.getJavaType()));
        implBuilder.append(String.format("\t\t%sMapper.deleteById(id);\n", xtName));
        implBuilder.append(String.format("\t\tlog.info(\"%s, %s信息删除成功: userId={}, %sId={}\", onlineUser.getId(), id);\n", tableMeta.getTableComment(), tableMeta.getTableComment(), xtName));
        implBuilder.append("\t}\n");


        // 构建批量删除参数
        LinkedHashMap<String, String> deleteBatchParamMap = new LinkedHashMap<>();
        deleteBatchParamMap.put(toXTCamelCase(primaryKeyColumn.getColumnName()) + "s", primaryKeyColumn.getColumnComment() + "列表");
        deleteBatchParamMap.put("onlineUser", "当前登录用户");
        String functionBatchComment = getFunctionComment(String.format("根据%s列表删除%s信息", StringUtils.isNotBlank(primaryKeyColumn.getColumnComment()) ? primaryKeyColumn.getColumnComment() : primaryKeyColumn.getColumnName(), tableMeta.getTableComment()), deleteBatchParamMap, "void");

        interfaceBuilder.append("\n").append(functionBatchComment);
        interfaceBuilder.append(String.format("\tvoid deleteBatchIds(List<%s> %ss, OnlineUser onlineUser);\n", primaryKeyColumn.getJavaType(), toXTCamelCase(primaryKeyColumn.getColumnName())));

        implBuilder.append("\n").append(functionBatchComment);
        implBuilder.append("\t@Override\n");
        implBuilder.append(String.format("\tpublic void deleteBatchIds(List<%s> %ss, OnlineUser onlineUser) {\n", primaryKeyColumn.getJavaType(), toXTCamelCase(primaryKeyColumn.getColumnName())));
        implBuilder.append(String.format("\t\t%sMapper.deleteBatchIds(ids);\n", xtName));
        implBuilder.append(String.format("\t\tlog.info(\"%s, %s信息批量删除成功: userId={}, count={}, %sIds={}\", onlineUser.getId(), ids.size(), ids.toString());\n", tableMeta.getTableComment(), tableMeta.getTableComment(), xtName));
        implBuilder.append("\t}\n");
    }

    /**
     * 构建根据外键查询接口
     * @param interfaceBuilder 接口构建器
     * @param implBuilder 实现构建器
     * @param tableMeta 表元数据
     * @param columnMeta 列元数据
     * @param dtName 大驼峰表名
     * @param xtName 小驼峰表名
     */
    private void buildGetListByForeignKey(StringBuilder interfaceBuilder, StringBuilder implBuilder, TableMeta tableMeta, ColumnMeta columnMeta, String dtName, String xtName) {
        // 将列名转换为大驼峰
        String dtColumnName = toDTCamelCase(columnMeta.getColumnName());
        // 将列名转换为小驼峰
        String xtColumnName = toXTCamelCase(columnMeta.getColumnName());
        // 创建一个LinkedHashMap，用于存储列名和列注释
        LinkedHashMap<String, String> columnMap = new LinkedHashMap<>();
        // 将列名和列注释添加到LinkedHashMap中
        columnMap.put(xtColumnName, columnMeta.getColumnComment());
        // 获取函数注释
        String functionComment = getFunctionComment(String.format("根据%s获取%s列表", StringUtils.isNotBlank(columnMeta.getColumnComment()) ? columnMeta.getColumnComment() : columnMeta.getColumnName(), tableMeta.getTableComment()), columnMap, String.format("List<%s> %s列表", dtName, StringUtils.isNotBlank(tableMeta.getTableComment()) ? tableMeta.getTableComment() : tableMeta.getTableName()));

        // 将函数注释添加到接口构建器中
        interfaceBuilder.append("\n").append(functionComment);
        // 构建接口
        interfaceBuilder.append(String.format("\tList<%s> getListBy%s(%s %s);\n", dtName, dtColumnName, columnMeta.getJavaType(), xtColumnName));

        // 将函数注释添加到实现构建器中
        implBuilder.append("\n").append(functionComment);
        // 构建实现
        implBuilder.append("\t@Override\n");
        implBuilder.append(String.format("\tpublic List<%s> getListBy%s(%s %s) {\n", dtName, dtColumnName, columnMeta.getJavaType(), xtColumnName));
        implBuilder.append(String.format("\t\treturn %sMapper.selectList(new QueryWrapper<%s>().eq(\"%s\", %s)", xtName, dtName, columnMeta.getColumnName(), xtColumnName));

        // 如果表元数据中存在主键列，则按照主键列进行排序
        if (tableMeta.getPrimaryKeyColumn() != null) {
            implBuilder.append(".orderByAsc(\"").append(tableMeta.getPrimaryKeyColumn().getColumnName()).append("\"))");
        }
        implBuilder.append(";\n");
        implBuilder.append("\t}\n");
    }

    /**
     * 构建根据外键删除接口
     * @param interfaceBuilder 接口构建器
     * @param implBuilder 实现构建器
     * @param tableMeta 表元数据
     * @param columnMeta 列元数据
     * @param dtName 大驼峰表名
     * @param xtName 小驼峰表名
     */
    private void buildDeleteByForeignKey(StringBuilder interfaceBuilder, StringBuilder implBuilder, TableMeta tableMeta, ColumnMeta columnMeta, String dtName, String xtName) {
        // 根据表名和字段名生成DTCamelCase
        String dtColumnName = toDTCamelCase(columnMeta.getColumnName());
        // 根据表名和字段名生成XTCamelCase
        String xtColumnName = toXTCamelCase(columnMeta.getColumnName());
        // 创建一个LinkedHashMap，用于存储字段名和字段注释
        LinkedHashMap<String, String> columnMap = new LinkedHashMap<>();
        // 将字段名和字段注释添加到LinkedHashMap中
        columnMap.put(xtColumnName, columnMeta.getColumnComment());
        columnMap.put("onlineUser", "当前登录用户");
        // 获取函数注释
        String functionComment = getFunctionComment(String.format("根据%s删除%s", StringUtils.isNotBlank(columnMeta.getColumnComment()) ? columnMeta.getColumnComment() : columnMeta.getColumnName(), tableMeta.getTableComment()), columnMap, "void");

        // 将函数注释添加到接口中
        interfaceBuilder.append("\n").append(functionComment);
        // 添加接口中的deleteBy方法
        interfaceBuilder.append(String.format("\tvoid deleteBy%s(String %s, OnlineUser onlineUser);\n", dtColumnName, xtColumnName));

        // 将函数注释添加到实现类中
        implBuilder.append("\n").append(functionComment);
        // 添加实现类的deleteBy方法
        implBuilder.append("\t@Override\n");
        implBuilder.append(String.format("\tpublic void deleteBy%s(String %s, OnlineUser onlineUser) {\n", dtColumnName, xtColumnName));
        // 添加实现类中的delete方法
        implBuilder.append(String.format("\t\t%sMapper.delete(new QueryWrapper<%s>().eq(\"%s\", %s));\n", xtName, dtName, columnMeta.getColumnName(), xtColumnName));
        implBuilder.append(String.format("\t\tlog.info(\"%s, %s信息根据%s删除成功: userId={}, %s={}\", onlineUser.getId(), %s);\n", tableMeta.getTableComment(), tableMeta.getTableComment(), StringUtils.isNotBlank(columnMeta.getColumnComment()) ? columnMeta.getColumnComment() : columnMeta.getColumnName(), xtColumnName, xtColumnName));
        implBuilder.append("\t}\n");
    }

    /**
     * 获取函数注释
     * @param description 函数描述
     * @param params 参数列表
     * @param returnStr 返回值
     * @return 函数注释
     */
    private String getFunctionComment(String description, LinkedHashMap<String, String> params, String returnStr) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t/**\n");
        stringBuilder.append(String.format("\t * %s\n", description));
        for (Map.Entry<String, String> stringStringEntry : params.entrySet()) {
            stringBuilder.append(String.format("\t * @param %s %s\n", ((Map.Entry<?, ?>) stringStringEntry).getKey(), ((Map.Entry<?, ?>) stringStringEntry).getValue()));
        }
        if (!returnStr.equals("void")) {
            stringBuilder.append(String.format("\t * @return %s\n", returnStr));
        }
        stringBuilder.append("\t */\n");
        return stringBuilder.toString();
    }
}
