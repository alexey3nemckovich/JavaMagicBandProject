package main.com.bsuir.autoservice.dao.database.map.impl;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.exception.BeanException;
import main.com.bsuir.autoservice.binding.annotation.Cached;
import main.com.bsuir.autoservice.config.exception.ConfigException;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.database.map.beanhelper.ColumnMap;
import main.com.bsuir.autoservice.dao.database.map.beanhelper.DependencyMap;
import main.com.bsuir.autoservice.dao.database.map.beanhelper.TableMap;
import main.com.bsuir.autoservice.dao.impl.ICrudDao;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
    private static final String ATTRIBUTE_DATABASE_NAME = "database_table_name";
    private static final String ATTRIBUTE_BEAN_CLASS = "bean_class";
    private static final String ATTRIBUTE_SHOW_NAME = "show_name";
    private static final String ATTRIBUTE_DAO_CRUD_NAME = "dao_crud";

    //table subnodes
    private static final String TAG_COLUMN = "column";
    private static final String ATTRIBUTE_COLUMN_DATABASE_FIELD = "database_field";
    private static final String ATTRIBUTE_COLUMN_BEAN_FIELD = "bean_field";

    //column subnodes
    private static final String TAG_DEPENDENCIES = "dependencies";
    private static final String ATTRIBUTE_DEPENDENCY_SHOW_NAME = "dependency_show_name";
    private static final String ATTRIBUTE_DEPENDENCY_FIELD = "dependency_field";

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
            throw new IllegalArgumentException(String.format("Not found attribute name '%s' in '%s'",
                    attributeName, namedNodeMap.toString()));
        }
    }

    private TableMap getTableMap(Node tableNode) {
        final NamedNodeMap namedNodeMap = tableNode.getAttributes();
        return new TableMap(
                getLoadBeanClass(getExistAttributeValue(namedNodeMap, ATTRIBUTE_BEAN_CLASS)),
                getExistAttributeValue(namedNodeMap, ATTRIBUTE_DATABASE_NAME),
                getTableColumns(tableNode),
                getDependencies(tableNode));
    }

    private List<DependencyMap> getDependencies(Node tableNode) {
        assert tableNode.getNodeType() == Node.ELEMENT_NODE : String.format("'%s' isn't element node", tableNode);
        assert ((Element)tableNode).getElementsByTagName(TAG_DEPENDENCIES).getLength() <= 1 : String.format("'%s' is too big dependencies (>1)", tableNode);

        final List<DependencyMap> dependencyMaps = new ArrayList<>();
        final List<Node> dependencyTagNodes = asList(((Element)tableNode).getElementsByTagName(TAG_DEPENDENCIES));
        if (dependencyTagNodes.size() == 1) {
            final List<Node> dependencyNodes = asList(dependencyTagNodes.get(0).getChildNodes());

            dependencyNodes.forEach(dependencyNode -> {
                if (dependencyNode.getNodeType() == Node.ELEMENT_NODE && ((Element) dependencyNode).getTagName().equals(TAG_COLUMN)) {
                    NamedNodeMap namedNodeMap = dependencyNode.getAttributes();
                    dependencyMaps.add(new DependencyMap(
                            getExistAttributeValue(namedNodeMap, ATTRIBUTE_DEPENDENCY_SHOW_NAME),
                            getExistAttributeValue(namedNodeMap, ATTRIBUTE_DEPENDENCY_FIELD)
                    ));
                }
            });
        }
        return dependencyMaps;
    }

    private List<ColumnMap> getTableColumns(Node tableNode) {
        final List<ColumnMap> columnMaps = new ArrayList<>();
        final List<Node> columnNodes = asList(tableNode.getChildNodes());
        columnNodes.forEach(columnNode -> {
            if (columnNode.getNodeType() == Node.ELEMENT_NODE && ((Element)columnNode).getTagName().equals(TAG_COLUMN)) {
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

    private static Class<? extends ICrudDao> getLoadDaoCrudClass(String daoCrudClassName) {
        return getLoadClass(daoCrudClassName, ICrudDao.class);
    }

    private static <T> Class<? extends T> getLoadClass(String classFullName, Class<T> classType) {
        try {
            return Class.forName(classFullName).asSubclass(classType);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Not found class '%s' as type '%s'", classFullName, classType), e);
        }
    }

    private Node getShowTableNode(String showTableName) {
        return getTableNodes().get(getMapShowTableNameIndex().get(showTableName));
    }

    //call 2 times, not need to cached
    @Override
    public Map<String, Class<? extends ICrudDao>> getShowTableNameDaoCrud() {
        final Map<String, Class<? extends ICrudDao>> showTableDaoCrudMap = new HashMap<>();
        getTableNodes().forEach(node -> {
            Node tableName = node.getAttributes().getNamedItem(ATTRIBUTE_SHOW_NAME);
            Node daoName = node.getAttributes().getNamedItem(ATTRIBUTE_DAO_CRUD_NAME);
            if (tableName != null && daoName != null) {
                showTableDaoCrudMap.put(tableName.getNodeValue(), getLoadDaoCrudClass(daoName.getNodeValue()));
            }
        });
        return showTableDaoCrudMap;
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

    private Class<? extends Bean> getBeanClass(String showTableName) {
        return getShowTableNameBean().get(showTableName);
    }

    @Override
    public <P> Bean<P> getBeanInstance(String showTableName, Map<String, String> fields) throws BeanException {
        return Bean.getBeanObject(getBeanClass(showTableName), fields);
    }

    @Override
    public <P> Bean<P> getBeanInstance(String showTableName) throws BeanException {
        return Bean.getBeanObject(getBeanClass(showTableName));
    }

    private Node getDaoClassNode(Class<? extends ICrudDao> daoClass) {
        assert getCrudClassNodes().containsKey(daoClass) : String.format("'%s' didn't contains in databaseMap", daoClass);
        return getCrudClassNodes().get(daoClass);
    }

    @Cached
    protected Map<Class<? extends ICrudDao>, Node> getCrudClassNodes(){
        Map<Class<? extends ICrudDao>, Node> crudClassMap = new HashMap<>();
        getTableNodes().forEach(tableNode -> {
            Node daoCrudName = tableNode.getAttributes().getNamedItem(ATTRIBUTE_DAO_CRUD_NAME);
            if (daoCrudName != null) {
                crudClassMap.put(getLoadDaoCrudClass(daoCrudName.getNodeValue()), tableNode);
            }
        });
        return crudClassMap;
    }

    @Cached
    @Override
    public List<String> getShowTableNames() {
        return new ArrayList<>(getShowTableNameDaoCrud().keySet());
    }

    @Override
    public TableMap getTableMap(Class<? extends ICrudDao> crudDaoClass) {
        return getTableMap(getDaoClassNode(crudDaoClass));
    }
}
