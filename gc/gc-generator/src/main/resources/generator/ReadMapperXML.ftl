<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${targetPackage}.${tableClass.shortClassName}${mapperSuffix}">
    <resultMap id="BaseResultMap" type="${tableClass.fullClassName}">
        <#if tableClass.pkFields??>
            <#list tableClass.pkFields as field>
        <id column="${field.columnName}" jdbcType="${field.jdbcType}" property="${field.fieldName}" /><#if field.remarks?? && field.remarks != ""><!-- ${field.remarks} --></#if>
            </#list>
        </#if>
        <#if tableClass.baseFields??>
            <#list tableClass.baseFields as field>
        <result column="${field.columnName}" jdbcType="${field.jdbcType}" property="${field.fieldName}" /><#if field.remarks?? && field.remarks != ""><!-- ${field.remarks} --></#if>
            </#list>
        </#if>
    </resultMap>

</mapper>
