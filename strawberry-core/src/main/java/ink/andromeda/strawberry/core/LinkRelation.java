package ink.andromeda.strawberry.core;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import ink.andromeda.strawberry.tools.Pair;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ink.andromeda.strawberry.tools.GeneralTools.toJSONString;

/**
 * 原始表之间的关系
 *
 */
public class LinkRelation {

    @Setter
    @Getter
    private String sql;

    @Setter
    @Getter
    private Map<String, TableNode> virtualNodeMap;

    @Setter
    @Getter
    private List<String> tables;

    @Setter
    @Getter
    private Map<String, String> tableLabelRef;

    @Setter
    @Getter
    private List<Pair<String, String>> outputDescription;

    @Getter
    @Setter
    private List<String> outputFields;

    @Getter
    @Setter
    private List<String> outputFieldLabels;

    @Getter
    @Accessors(fluent = true)
    @ToString
    public static class TableNode {

        private final String tableName;

        public TableNode(String tableName){
            this.tableName = tableName;
        }

        private final Map<String, JoinProfile> prev = new HashMap<>();

        private final Map<String, JoinProfile> next = new HashMap<>();

    }

    @Override
    public String toString() {
        return toJSONString(this);
    }
}
