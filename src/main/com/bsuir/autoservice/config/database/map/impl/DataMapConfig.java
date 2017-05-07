package main.com.bsuir.autoservice.config.database.map.impl;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.binding.annotation.Cached;
import main.com.bsuir.autoservice.config.database.map.ColumnMap;
import main.com.bsuir.autoservice.config.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.config.database.map.TableMap;
import main.com.bsuir.autoservice.config.exception.ConfigException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static main.com.bsuir.autoservice.library.XmlUtil.asList;

public class DataMapConfig implements IDatabaseMap{
    private static final String TAG_TABLE = "table";

    //tables subnodes
    private static final String ATTRIBUTE_DATABASE_NAME = "database_name";
    private static final String ATTRIBUTE_APPLICATION_CLASS = "application_class";
    private static final String ATTRIBUTE_SHOW_NAME = "show_name";

    //table subnodes
    private static final String ATTRIBUTE_COLUMN_DATABASE_FIELD = "database_field";
    private static final String ATTRIBUTE_COLUMN_APPLICATION_FIELD = "application_field";

    //column subnodes
    private static final String TAG_DEPENDENCY = "dependency";
    private static final String ATTRIBUTE_DEPENDENCY_TABLE_NAME = "dependency_table_name";
    private static final String ATTRIBUTE_DEPENDENCY_FIELD_NAME = "dependency_field_name";

    private final Document mapDocument;

    @Inject
    public DataMapConfig(@Named("dataMapConfig") String dataMapConfigResources) throws ConfigException {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            mapDocument = dBuilder.parse(
                    Thread.currentThread().getContextClassLoader().getResourceAsStream(dataMapConfigResources));

            mapDocument.normalize();
        } catch (Exception e) {
            throw new ConfigException(e);
        }
    }

    @Cached
    protected Map<String, Integer> getMapShowTableNameIndex() {
        final Map<String, Integer> mapTableNamesIndex = new HashMap<>();
        int currentIndex = 0;
        for (Node node : getTableNodes()) {
            Node tableName = node.getAttributes().getNamedItem(ATTRIBUTE_SHOW_NAME);
            if (tableName != null) {
                mapTableNamesIndex.put(tableName.getNodeValue(), currentIndex);
            }
            currentIndex++;
        }
        return mapTableNamesIndex;
    }

    @Cached
    protected List<Node> getTableNodes() {
        return asList(mapDocument.getElementsByTagName(TAG_TABLE));
    }

    @Cached
    public List<String> getShowTableNames() {
        final List<String> tableNames = new ArrayList<>();
        getTableNodes().forEach(node -> {
            Node tableName = node.getAttributes().getNamedItem(ATTRIBUTE_SHOW_NAME);
            if (tableName != null) {
                tableNames.add(tableName.getNodeValue());
            }
        });
        return tableNames;
    }

    private static String getExistAttributeValue(NamedNodeMap namedNodeMap, String attributeName) {
        final Node namedAttribute = namedNodeMap.getNamedItem(attributeName);
        if (namedAttribute != null) {
            return namedAttribute.getNodeValue();
        } else {
            throw new RuntimeException(String.format("Not found attribute name %s in %s",
                    attributeName, namedNodeMap.toString()));
        }
    }

    private TableMap getTableMap(Node tableNode) {
        final NamedNodeMap namedNodeMap = tableNode.getAttributes();
        return new TableMap(
                getBeanClass(getExistAttributeValue(namedNodeMap, ATTRIBUTE_APPLICATION_CLASS)),
                getExistAttributeValue(namedNodeMap, ATTRIBUTE_DATABASE_NAME),
                getExistAttributeValue(namedNodeMap, ATTRIBUTE_SHOW_NAME),
                getTableColums(tableNode));
    }

    private List<ColumnMap> getTableColums(Node tableNode) {
        final List<ColumnMap> columnMaps = new ArrayList<>();
        final List<Node> columnNodes = asList(tableNode.getChildNodes());
        columnNodes.forEach(columnNode -> {
            if (columnNode.getNodeType() == Node.ELEMENT_NODE) {
                NamedNodeMap namedNodeMap = columnNode.getAttributes();
                columnMaps.add(new ColumnMap(
                        getExistAttributeValue(namedNodeMap, ATTRIBUTE_COLUMN_DATABASE_FIELD),
                        getExistAttributeValue(namedNodeMap, ATTRIBUTE_COLUMN_APPLICATION_FIELD)
                ));
            }
        });
        return columnMaps;
    }

    private static Class<? extends Bean> getBeanClass(String beanClassName) {
        try {
            return Class.forName(String.format("%s.%s", Bean.class.getPackage().getName(), beanClassName))
                    .asSubclass(Bean.class);
        } catch (Exception e) {
            throw new RuntimeException("Not found Bean class", e);
        }
    }

    public TableMap getTableMap(String showTableName) {
        return getTableMap(getTableNodes().get(getMapShowTableNameIndex().get(showTableName)));
    }
}
