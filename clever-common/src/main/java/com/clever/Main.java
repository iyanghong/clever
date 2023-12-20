package com.clever;

import com.clever.util.generate.GenerateMapper;
import com.clever.util.generate.GenerateMapperXML;
import com.clever.util.generate.GenerateService;
import com.clever.util.generate.config.GenerateConfig;
import com.clever.util.generate.GenerateEntity;

public class Main {
    public static void main(String[] args) {
        GenerateConfig generateDatabaseConfig = new GenerateConfig("jdbc:mysql://localhost:3306/clever-system", "root", "Ts962464");
        GenerateEntity handler = new GenerateEntity(generateDatabaseConfig);
        handler.generate("role_permission","com.clever.bean.system", "/clever-common/src/main/java/com/clever/bean/system");
        GenerateMapperXML generateMapperXML = new GenerateMapperXML(generateDatabaseConfig);
        generateMapperXML.generate("role_permission","com.clever.mapper","/clever-system/src/main/resources/com/clever/mapper");

        GenerateMapper generateMapper = new GenerateMapper(generateDatabaseConfig, "com.clever.bean.system");
        generateMapper.generate("role_permission","com.clever.mapper","/clever-system/src/main/java/com/clever/mapper");

        GenerateService generateService = new GenerateService(generateDatabaseConfig, "com.clever.bean.system", "com.clever.mapper");
        generateService.generate("role_permission","com.clever.service", "/clever-system/src/main/java/com/clever/service");

    }
}