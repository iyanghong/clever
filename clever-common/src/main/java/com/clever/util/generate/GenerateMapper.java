package com.clever.util.generate;

import com.clever.util.generate.config.GenerateConfig;
import com.clever.util.generate.entity.TableMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * @Author xixi
 * @Date 2023-12-18 11:34
 **/
public class GenerateMapper extends BaseGenerator {
    private static final Logger log = LoggerFactory.getLogger(GenerateMapper.class);

    public GenerateMapper(GenerateConfig config) {
        super(config);
    }

    public GenerateMapper(GenerateConfig config, String entityPageName) {
        super(config);
        config.setEntityPackageName(entityPageName);
    }

    @Override
    protected void handler(List<TableMeta> tableMetaList, String packageName, String basePath) {
        for (TableMeta tableMeta : tableMetaList) {
            String dtTableName = toDTCamelCase(tableMeta.getTableName());
            StringBuilder stringBuilder = new StringBuilder();
            // 添加包名
            stringBuilder.append("package ").append(packageName).append(";\n\n");
            stringBuilder.append("import com.baomidou.mybatisplus.core.mapper.BaseMapper;\n");
            stringBuilder.append("import org.apache.ibatis.annotations.Mapper;\n");
            stringBuilder.append("import ").append(config.getEntityPackageName()).append(".").append(dtTableName).append(";\n\n");
            stringBuilder.append("@Mapper\n");
            stringBuilder.append(String.format("public interface %sMapper extends BaseMapper<%s> {", dtTableName, dtTableName));
            stringBuilder.append("\n}\n");
            String filePath = Paths.get(getBasePathOrCreate(basePath), dtTableName + "Mapper.java").toString();
            // 写入文件
            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(stringBuilder.toString());
                log.info("The table's mapper class generate complete. table = '{}' filePath = {}", tableMeta.getTableName(), filePath);
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file: " + e.getMessage());
            }

        }
    }
}
