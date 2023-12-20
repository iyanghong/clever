package com.clever;

import com.clever.util.generate.*;
import com.clever.util.generate.config.GenerateConfig;

public class Main {
    public static void main(String[] args) {
        GenerateConfig generateDatabaseConfig = new GenerateConfig("jdbc:mysql://localhost:3306/clever-system", "root", "Ts962464");
        generateDatabaseConfig.setAppName("clever-system");
        generateDatabaseConfig.setEntityPackageName("com.clever.bean.system");
        generateDatabaseConfig.setMapperPackageName("com.clever.mapper");
        generateDatabaseConfig.setServicePackageName("com.clever.service");

        /*GenerateEntity handler = new GenerateEntity(generateDatabaseConfig);
        handler.generate("role_permission","com.clever.bean.system", "/clever-common/src/main/java/com/clever/bean/system");
        GenerateMapperXML generateMapperXML = new GenerateMapperXML(generateDatabaseConfig);
        generateMapperXML.generate("role_permission","com.clever.mapper","/clever-system/src/main/resources/com/clever/mapper");

        GenerateMapper generateMapper = new GenerateMapper(generateDatabaseConfig, "com.clever.bean.system");
        generateMapper.generate("role_permission","com.clever.mapper","/clever-system/src/main/java/com/clever/mapper");
*/
        GenerateService generateService = new GenerateService(generateDatabaseConfig, "com.clever.bean.system", "com.clever.mapper");
        generateService.generate("","com.clever.service", "/clever-system/src/main/java/com/clever/service");


        GenerateController controller = new GenerateController(generateDatabaseConfig);
        controller.generate("", "com.clever.controller", "/clever-system/src/main/java/com/clever/controller");
    }
}