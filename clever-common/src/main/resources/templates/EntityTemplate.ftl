package ${entityPackageName};

import java.io.Serializable;

<#if primaryKeyColumn??>
    <#if primaryKeyColumn.javaType == "String">
import com.baomidou.mybatisplus.annotation.IdType;
    </#if>
import com.baomidou.mybatisplus.annotation.TableId;
</#if>

<#if isHasNeedNotBlankValidate>
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
</#if>

<#if isHasDateTypeColumn>
import java.util.Date;
</#if>

/**
 * ${tableComment}
 *
 * @Author ${author}
 * @Date ${date}
 */
public class ${upperCamelCaseName} implements Serializable {

    <#list columns as column>
    <#if column.columnComment != "">
    /**
     * ${column.columnComment}
     */
    </#if>
    <#if column.isHasNeedNotBlankValidate && column.javaType == "String">
    @NotBlank(message = "${column.commentOrName}不能为空")
    </#if>
    <#if column.isHasNeedNotBlankValidate && column.javaType != "String">
    @NotNull(message = "${column.commentOrName}不能为空")
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
    <#if column.columnName == "enable" && column.javaType == "Integer">

    <#if column.columnComment != "">
    /**
     * ${column.columnComment}
     */
    </#if>
    public boolean isEnable() {
    	return enable == 1;
    }
    </#if>

    public void set${column.upperCamelCaseName}(${column.javaType} ${column.lowerCamelCaseName}) {
        this.${column.lowerCamelCaseName} = ${column.lowerCamelCaseName};
    }
    </#list>
}