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
 * @Date 2023-12-18 11:33
 **/
public class GenerateMapperXML extends BaseGenerator {
    private static final Logger log = LoggerFactory.getLogger(GenerateMapperXML.class);

    public GenerateMapperXML(GenerateConfig config) {
        super(config);
    }

    @Override
    protected void handler(List<TableMeta> tableMetaList, String packageName, String basePath) {
        log.info("开始生成MapperXML文件,count = {}", tableMetaList.size());
        for (TableMeta tableMeta : tableMetaList) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
            stringBuilder.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\n");
            stringBuilder.append("        \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n");
            stringBuilder.append("<mapper namespace=\"").append(packageName).append(".").append(toDTCamelCase(tableMeta.getTableName())).append("Mapper\">\n\n");
            stringBuilder.append("</mapper>");
            String filePath = Paths.get(getBasePathOrCreate(basePath), toDTCamelCase(tableMeta.getTableName()) + "Mapper.xml").toString();
            // 写入文件
            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(stringBuilder.toString());
                log.info("The table's mapper xml generate complete. table = '{}' filePath = {}", tableMeta.getTableName(), filePath);
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file: " + e.getMessage());
            }
        }
    }
}
