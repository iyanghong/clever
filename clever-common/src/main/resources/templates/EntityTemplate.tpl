package ${packageName};

import java.io.Serializable;

<#if primaryKeyColumn??>
    <#if primaryKeyColumn.javaType == "String">
import com.baomidou.mybatisplus.annotation.IdType;
    </#if>
import com.baomidou.mybatisplus.annotation.TableId;
</#if>

<#if ifHasDateTypeColumn>
import java.util.Date;
</#if>
public class ${upperCamelCaseName} implements Serializable {

    <#list columns as column>
    <#if column.columnComment != "">
    /**
    * ${column.columnComment}
    */
    </#if>
    <#if column.columnKey == "PRI">
        <#if column.javaType == "String">
    @TableId(type = IdType.ASSIGN_ID)
        <#else>
    @TableId
        </#if>
    </#if>
    private ${column.javaType} ${column.lowerCamelCaseName};
    </#list>



    <#list columns as column>
    <#if column.columnComment != "">
    /**
    * ${column.columnComment}
    */
    </#if>
    public ${column.javaType} get${column.upperCamelCaseName}() {
        return ${column.lowerCamelCaseName};
    }

    public void set${column.upperCamelCaseName}(${column.javaType} ${column.lowerCamelCaseName}) {
        this.${column.lowerCamelCaseName} = ${column.lowerCamelCaseName};
    }
    </#list>
}