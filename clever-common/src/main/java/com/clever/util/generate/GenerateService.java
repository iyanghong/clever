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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xixi
 * @Date 2023-12-18 11:34
 **/
public class GenerateService extends BaseGenerator {
    private static final Logger log = LoggerFactory.getLogger(GenerateService.class);

    public GenerateService(GenerateConfig config) {
        super(config);
    }

    public GenerateService(GenerateConfig config, String entityPackageName, String mapperPackageName) {
        super(config);
        config.setEntityPackageName(entityPackageName);
        config.setMapperPackageName(mapperPackageName);
    }

    @Override
    protected void handler(List<TableMeta> tableMetaList, String packageName, String basePath) {
        for (TableMeta tableMeta : tableMetaList) {
            String dtName = toDTCamelCase(tableMeta.getTableName());
            String xtName = toXTCamelCase(tableMeta.getTableName());
            StringBuilder interfaceBuilder = initInterfaceStringBuilder(packageName, dtName);
            StringBuilder implementBuilder = initImplementBuilder(packageName, dtName, xtName);
            ColumnMeta primaryKeyColumn = tableMeta.getPrimaryKeyColumn();

            buildSelectPage(interfaceBuilder, implementBuilder, tableMeta, dtName, xtName);
            if (primaryKeyColumn != null) {
                buildSelectById(interfaceBuilder, implementBuilder, tableMeta, primaryKeyColumn, dtName, xtName);
            }
            for (ColumnMeta columnMeta : tableMeta.getColumns()) {
                if (columnMeta.getColumnName().endsWith("_id")) {
                    buildGetListByForeignKey(interfaceBuilder, implementBuilder, tableMeta, columnMeta, dtName, xtName);
                }
            }
            if (primaryKeyColumn != null) {
                buildSave(interfaceBuilder, implementBuilder, tableMeta, primaryKeyColumn, dtName, xtName);
                buildDelete(interfaceBuilder, implementBuilder, tableMeta, primaryKeyColumn, dtName, xtName);
            }
            for (ColumnMeta columnMeta : tableMeta.getColumns()) {
                if (columnMeta.getColumnName().endsWith("_id")) {
                    buildDeleteByForeignKey(interfaceBuilder, implementBuilder, tableMeta, columnMeta, dtName, xtName);
                }
            }
            interfaceBuilder.append("}\n");
            implementBuilder.append("}\n");


            String interfaceFilePath = Paths.get(getBasePathOrCreate(basePath), dtName + "Service.java").toString();
            // 写入文件
            try (FileWriter writer = new FileWriter(interfaceFilePath)) {
                writer.write(interfaceBuilder.toString());
                log.info("The table's service interface class generate complete. table = '{}' filePath = {}", tableMeta.getTableName(), interfaceFilePath);
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file: " + e.getMessage());
            }

            String implementFilePath = Paths.get(getBasePathOrCreate(Paths.get(basePath, "impl").toString()), dtName + "ServiceImpl.java").toString();

            // 写入文件
            try (FileWriter writer = new FileWriter(implementFilePath)) {
                writer.write(implementBuilder.toString());
                log.info("The table's service class generate complete. table = '{}' filePath = {}", tableMeta.getTableName(), implementFilePath);
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file: " + e.getMessage());
            }
        }
    }

    private StringBuilder initInterfaceStringBuilder(String packageName, String dtName) {
        StringBuilder interfaceBuilder = new StringBuilder();
        interfaceBuilder.append("package ").append(packageName).append(";\n\n");
        interfaceBuilder.append("import com.baomidou.mybatisplus.extension.plugins.pagination.Page;\n");
        interfaceBuilder.append("import com.clever.bean.model.OnlineUser;\n");
        interfaceBuilder.append("import java.util.List;\n");
        interfaceBuilder.append(String.format("import %s.%s;\n", config.getEntityPackageName(), dtName));
        interfaceBuilder.append("\npublic interface ").append(dtName).append("Service {\n");
        return interfaceBuilder;
    }

    private StringBuilder initImplementBuilder(String packageName, String dtName, String xtName) {
        StringBuilder implementBuilder = new StringBuilder();
        implementBuilder.append("package ").append(packageName).append(".impl;\n\n");
        implementBuilder.append("import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;\n");
        implementBuilder.append("import com.baomidou.mybatisplus.extension.plugins.pagination.Page;\n");
        implementBuilder.append("import com.clever.bean.model.OnlineUser;\n");
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
        implementBuilder.append("\n@Service\n");
        implementBuilder.append(String.format("public class %sServiceImpl implements %sService {\n", dtName, dtName));
        implementBuilder.append(String.format("\n\tprivate final static Logger log = LoggerFactory.getLogger(%sServiceImpl.class);\n", dtName));
        implementBuilder.append("\n\t@Resource\n");
        implementBuilder.append(String.format("\tprivate %sMapper %sMapper;\n", dtName, xtName));
        return implementBuilder;
    }


    private void buildSelectPage(StringBuilder interfaceBuilder, StringBuilder implBuilder, TableMeta tableMeta, String dtName, String xtName) {
        List<ColumnMeta> allowSearchColumns = new ArrayList<>();
        List<String> allowSearchColumnNames = Arrays.asList("_id", "name", "email", "phone","status","sex","gender");
        for (ColumnMeta columnMeta : tableMeta.getColumns()) {
            if (allowSearchColumnNames.contains(columnMeta.getColumnName())) {
                allowSearchColumns.add(columnMeta);
            }
        }


        interfaceBuilder.append(String.format("\n\tPage<%s> selectPage(Integer page, Integer limit", dtName));
        for (ColumnMeta columnMeta:allowSearchColumns){
            interfaceBuilder.append(String.format(", String %s", toXTCamelCase(columnMeta.getColumnName())));
        }

        interfaceBuilder.append(");\n");


        implBuilder.append("\n\t@Override\n");
        implBuilder.append(String.format("\tpublic Page<%s> selectPage(Integer page, Integer limit", dtName));
        for (ColumnMeta columnMeta:allowSearchColumns){
            implBuilder.append(String.format(", String %s", toXTCamelCase(columnMeta.getColumnName())));
        }
        implBuilder.append(") {\n");
        implBuilder.append(String.format("\t\tQueryWrapper<%s> queryWrapper = new QueryWrapper<>();\n", dtName));
        for (ColumnMeta columnMeta:allowSearchColumns){
            implBuilder.append(String.format("\t\tif (StringUtils.isNotBlank(%s)) {\n", toXTCamelCase(columnMeta.getColumnName())));
            implBuilder.append(String.format("\t\t\tqueryWrapper.eq(\"%s\", %s);\n", columnMeta.getColumnName(), toXTCamelCase(columnMeta.getColumnName())));
            implBuilder.append("\t\t}\n");
        }
        implBuilder.append(String.format("\t\treturn %sMapper.selectPage(new Page<%s>(page, limit), queryWrapper);\n", xtName, dtName));
        implBuilder.append("\t}\n");

    }


    private void buildSelectById(StringBuilder interfaceBuilder, StringBuilder implBuilder, TableMeta tableMeta, ColumnMeta primaryKeyColumn, String dtName, String xtName) {
        String xtColumnName = toXTCamelCase(primaryKeyColumn.getColumnName());
        interfaceBuilder.append(String.format("\n\t%s selectById(%s %s);\n", dtName, primaryKeyColumn.getJavaType(), xtColumnName));

        implBuilder.append("\n\t@Override\n");
        implBuilder.append(String.format("\tpublic %s selectById(%s %s) {\n", dtName, primaryKeyColumn.getJavaType(), xtColumnName));
        implBuilder.append(String.format("\t\treturn %sMapper.selectById(%s);\n", xtName, xtColumnName));
        implBuilder.append("\t}\n");
    }

    private void buildSave(StringBuilder interfaceBuilder, StringBuilder implBuilder, TableMeta tableMeta, ColumnMeta primaryKeyColumn, String dtName, String xtName) {
        interfaceBuilder.append(String.format("\n\tvoid save(%s %s, OnlineUser onlineUser);\n", dtName, xtName));

        implBuilder.append("\n\t@Override\n");
        implBuilder.append(String.format("\tpublic void save(%s %s, OnlineUser onlineUser) {\n", dtName, xtName));

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

    private void buildDelete(StringBuilder interfaceBuilder, StringBuilder implBuilder, TableMeta tableMeta, ColumnMeta primaryKeyColumn, String dtName, String xtName) {
        interfaceBuilder.append(String.format("\n\tvoid delete(%s id, OnlineUser onlineUser);\n", primaryKeyColumn.getJavaType()));

        implBuilder.append("\n\t@Override\n");
        implBuilder.append(String.format("\tpublic void delete(%s id, OnlineUser onlineUser) {\n", primaryKeyColumn.getJavaType()));
        implBuilder.append(String.format("\t\t%sMapper.deleteById(id);\n", xtName));
        implBuilder.append(String.format("\t\tlog.info(\"%s, %s信息删除成功: userId={}, %sId={}\", onlineUser.getId(), id);\n", tableMeta.getTableComment(), tableMeta.getTableComment(), xtName));
        implBuilder.append("\t}\n");


        interfaceBuilder.append(String.format("\n\tvoid deleteBatchIds(List<%s> id, OnlineUser onlineUser);\n", primaryKeyColumn.getJavaType()));
        implBuilder.append("\n\t@Override\n");
        implBuilder.append(String.format("\tpublic void deleteBatchIds(List<%s> ids, OnlineUser onlineUser) {\n", primaryKeyColumn.getJavaType()));
        implBuilder.append(String.format("\t\t%sMapper.deleteBatchIds(ids);\n", xtName));
        implBuilder.append(String.format("\t\tlog.info(\"%s, %s信息批量删除成功: userId={}, count={}, %sIds={}\", onlineUser.getId(), ids.size(), ids.toString());\n", tableMeta.getTableComment(), tableMeta.getTableComment(), xtName));
        implBuilder.append("\t}\n");
    }

    private void buildGetListByForeignKey(StringBuilder interfaceBuilder, StringBuilder implBuilder, TableMeta tableMeta, ColumnMeta columnMeta, String dtName, String xtName) {
        String dtColumnName = toDTCamelCase(columnMeta.getColumnName());
        String xtColumnName = toXTCamelCase(columnMeta.getColumnName());
        interfaceBuilder.append(String.format("\n\tList<%s> getListBy%s(%s %s);\n", dtName, dtColumnName, columnMeta.getJavaType(), xtColumnName));

        implBuilder.append("\n\t@Override\n");
        implBuilder.append(String.format("\tpublic List<%s> getListBy%s(%s %s) {\n", dtName, dtColumnName, columnMeta.getJavaType(), xtColumnName));
        implBuilder.append(String.format("\t\treturn %sMapper.selectList(new QueryWrapper<%s>().eq(\"%s\", %s)", xtName, dtName, columnMeta.getColumnName(), xtColumnName));

        if (tableMeta.getPrimaryKeyColumn() != null) {
            implBuilder.append(".orderByAsc(\"").append(tableMeta.getPrimaryKeyColumn().getColumnName()).append("\"))");
        }
        implBuilder.append(";\n");
        implBuilder.append("\t}\n");
    }

    private void buildDeleteByForeignKey(StringBuilder interfaceBuilder, StringBuilder implBuilder, TableMeta tableMeta, ColumnMeta columnMeta, String dtName, String xtName) {
        String dtColumnName = toDTCamelCase(columnMeta.getColumnName());
        String xtColumnName = toXTCamelCase(columnMeta.getColumnName());
        interfaceBuilder.append(String.format("\n\tvoid deleteBy%s(String %s, OnlineUser onlineUser);\n", dtColumnName, xtColumnName));

        implBuilder.append("\n\t@Override\n");
        implBuilder.append(String.format("\tpublic void deleteBy%s(String %s, OnlineUser onlineUser) {\n", dtColumnName, xtColumnName));
        implBuilder.append(String.format("\t\t%sMapper.delete(new QueryWrapper<%s>().eq(\"%s\", %s));\n", xtName, dtName, columnMeta.getColumnName(), xtColumnName));
        implBuilder.append(String.format("\t\tlog.info(\"%s, %s信息根据%s删除成功: userId={}, %s={}\", onlineUser.getId(), %s);\n", tableMeta.getTableComment(), tableMeta.getTableComment(), StringUtils.isNotBlank(columnMeta.getColumnComment()) ? columnMeta.getColumnComment() : columnMeta.getColumnName(), xtColumnName, xtColumnName));
        implBuilder.append("\t}\n");
    }
}
