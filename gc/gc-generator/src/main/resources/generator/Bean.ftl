package ${package};

import com.yfc.gc.core.base.bean.BaseBean;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.*;
import java.util.*;

/**
 * 表名: ${tableClass.tableName}
 */
@Data
@Table(name = "${tableClass.tableName}")
public class ${tableClass.shortClassName} extends BaseBean {

    public static final ${tableClass.shortClassName} EMPTY = new ${tableClass.shortClassName}();

<#if tableClass.baseFields??>
    <#list tableClass.baseFields as field>
        <#if field.columnName != 'ID'
        && field.columnName != 'CREATED_BY'
        && field.columnName != 'CREATED_TIME'
        && field.columnName != 'UPDATED_BY'
        && field.columnName != 'UPDATED_TIME'>
    /**
     * 列名: ${field.columnName}
        <#if field.remarks?? && field.remarks != "">
     * 注释: ${field.remarks}
        </#if>
     */
    @Column(name = "${field.columnName}")
    private ${field.shortTypeName} ${field.fieldName};
    </#if>
    </#list>
</#if>
    @Override
    public boolean empty() {
        return EMPTY.hashCode() == this.hashCode();
    }
}
