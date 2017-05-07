package main.com.bsuir.autoservice.dao.database.map.impl;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.BeanException;
import main.com.bsuir.autoservice.binding.annotation.Cached;
import main.com.bsuir.autoservice.config.exception.ConfigException;
import main.com.bsuir.autoservice.dao.database.map.ColumnMap;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.database.map.TableMap;
import main.com.bsuir.autoservice.service.crud.IServiceCrud;
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

public class DataMapConfig implements IDatabaseMap {
    private static final String TAG_TABLE = "table";

    //tables subnodes
    private static final String ATTRIBUTE_DATABASE_NAME = "database_name";
    private static final String ATTRIBUTE_BEAN_CLASS = "bean_class";
    private static final String ATTRIBUTE_SHOW_NAME = "show_name";
    private static final String ATTRIBUTE_SERVICE_CRUD_NAME = "service_crud";

    //table subnodes
    private static final String ATTRIBUTE_COLUMN_DATABASE_FIELD = "database_field";
    private static final String ATTRIBUTE_COLUMN_BEAN_FIELD = "bean_field";

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
                getLoadBeanClass(getExistAttributeValue(namedNodeMap, ATTRIBUTE_BEAN_CLASS)),
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
                        getExistAttributeValue(namedNodeMap, ATTRIBUTE_COLUMN_BEAN_FIELD)
                ));
            }
        });
        return columnMaps;
    }

    private static Class<? extends Bean> getLoadBeanClass(String beanClassName) {
        return getLoadClass(beanClassName, Bean.class);
    }

    private static Class<? extends IServiceCrud> getLoadServiceCrudClass(String serviceCrudClassName) {
        return getLoadClass(serviceCrudClassName, IServiceCrud.class);
    }

    private static <T> Class<? extends T> getLoadClass(String className, Class<T> classType) {
        try {
            return Class.forName(String.format("%s.%s", classType.getPackage().getName(), className))
                    .asSubclass(classType);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Not found class '%s' as type '%s'", className, classType), e);
        }
    }

    @Override
    public TableMap getTableMap(String showTableName) {
        return getTableMap(getShowTableNode(showTableName));
    }

    private Node getShowTableNode(String showTableName) {
        return getTableNodes().get(getMapShowTableNameIndex().get(showTableName));
    }

    //call 2 times, not need to cached
    @Override
    public Map<String, Class<? extends IServiceCrud>> getShowTableNameServiceCrud() {
        final Map<String, Class<? extends IServiceCrud>> showTableServiceCrudMap = new HashMap<>();
        getTableNodes().forEach(node -> {
            Node tableName = node.getAttributes().getNamedItem(ATTRIBUTE_SHOW_NAME);
            Node serviceName = node.getAttributes().getNamedItem(ATTRIBUTE_SERVICE_CRUD_NAME);
            if (tableName != null && serviceName != null) {
                showTableServiceCrudMap.put(tableName.getNodeValue(), getLoadServiceCrudClass(serviceName.getNodeValue()));
            }
        });
        return showTableServiceCrudMap;
    }

    @Cached
    protected Map<String, Class<? extends Bean>> getShowTableNameBean() {
        Map<String, Class<? extends Bean>> showTableNamesBean = new HashMap<>(getMapShowTableNameIndex().size());
        for (String showTableName : getMapShowTableNameIndex().keySet()) {
            showTableNamesBean.put(showTableName, getLoadBeanClass(getExistAttributeValue(
                    getShowTableNode(showTableName).getAttributes(), ATTRIBUTE_BEAN_CLASS)));
        }
        return showTableNamesBean;
    }

    @Override
    public Class<? extends Bean> getBeanClass(String showTableName) {
        return getShowTableNameBean().get(showTableName);
    }

    @Override
    public Bean getBeanInstance(String showTableName, HashMap<String, String> fields) throws BeanException {
        return Bean.getBeanObject(getBeanClass(showTableName), fields);
    }

    @Cached
    @Override
    public List<String> getShowTableNames() {
        return new ArrayList<>(getShowTableNameServiceCrud().keySet());
    }
}
