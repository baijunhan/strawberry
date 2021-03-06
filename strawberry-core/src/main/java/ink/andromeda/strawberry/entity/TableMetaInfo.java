package ink.andromeda.strawberry.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.sql.DataSource;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static ink.andromeda.strawberry.tools.GeneralTools.simpleQuery;
import static ink.andromeda.strawberry.tools.SQLTemplate.previewTableDataSql;

/**
 * 表结构定义
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(fluent = true)
public class TableMetaInfo {

    private long id;

    private String schemaName;

    private String name;

    private String sourceName;

    /**
     * 列信息:
     * <p>列名<->列信息</p>
     */
    private Map<String, TableField> fields;

    /**
     * 字段列表(按数据库顺序排列)
     */
    private List<String> fieldList;

    /**
     * 该表对应的数据源
     */
    private DataSource dataSource;

    private DataSource dataSource(){
        return Objects.requireNonNull(dataSource);
    }

    public String fullName(){
        return schemaName + "." + name;
    }

    /**
     * 获取预览数据
     * @param limit 预览数据的长度
     * @return 预览数据
     */
    public List<Map<String, Object>> previewData(int limit){
        String sql = previewTableDataSql(fullName(), limit);
        return simpleQuery(dataSource(), sql);
    }

    public List<Map<String, Object>> previewData(){
        return previewData(20);
    }
}

