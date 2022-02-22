package com.atguigu.book.utils;

/**
 * @Author 雷神 2020/8/24 18:08
 */


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.dbutils.QueryRunner;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

/**
 * 数据库工具类v2.0
 * 将访问数据库的公共代码抽取
 * 1.加载驱动
 * 2.连接获取
 * 6.关闭资源
 * @author 雷神
 *
 */
@SuppressWarnings("ALL")
public class DBUtils {


    /**驱动类路径*/
    private static  String DRIVER_CLASS;
    /**url地址*/
    private static  String URL;
    private static  String USER;
    private static  String PASSWORD;

    private static int MAX_ACTIVE;
    //最长等待时间
    private static long MAX_WAIT;
    //初始连接个数965
    private static int INIT_SIZE;

//    private QueryRunner queryRunner = new QueryRunner();
/**    获取存放当前线程连接对象的ThreadLocal对象 */
    private static  ThreadLocal<Connection> conns = new ThreadLocal<>();
    private static DruidDataSource ds = new DruidDataSource();
    static {
        try {
            //读取属性文件获取连接数据库相关的字符串
            InputStream is = DBUtils.class.getResourceAsStream("/jdbc.properties");
            Properties p = new Properties();
            //用于读properties文件内容
            p.load(is);

            //根据属性名获取属性值
            DRIVER_CLASS = p.getProperty("driver");
            URL = p.getProperty("url");
            USER = p.getProperty("user");
            PASSWORD = p.getProperty("password");

            //获得连接池相关参数
            MAX_ACTIVE = Integer.parseInt(p.getProperty("pool.maxActive"));
            INIT_SIZE = Integer.parseInt(p.getProperty("pool.initSize"));
            MAX_WAIT = Long.parseLong(p.getProperty("pool.maxWait"));

            //初始化连接池
            init();
        }
        catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void init(){
        if(ds==null || ds.isClosed()){
            ds = new DruidDataSource();
        }
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ds.setDriverClassName(DRIVER_CLASS);
        ds.setUrl(URL);
        ds.setUsername(USER);
        ds.setPassword(PASSWORD);
        ds.setMaxActive(MAX_ACTIVE);
        ds.setInitialSize(INIT_SIZE);
        ds.setMaxWait(MAX_WAIT);
    }
    /**
     *
     * @return 获取连接
     */
    public static synchronized Connection getConn() {
            Connection conn = conns.get();
        try {
            if(ds==null || ds.isClosed()){
                init();
            }
            if (conn == null){
                conn = ds.getConnection();
//                开启手动提交（事务）
                conn.setAutoCommit(false);
//                保存到ThreadLocal中
                conns.set(conn);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
            return conn;

    }

    /**
     * 提交事务并且关闭连接
     */
    public static void commitAndClose(){
        Connection connection = conns.get();
        if (connection != null){
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
//      一定要执行remove操作
        conns.remove();
    }

    public static void rollbackAndClose(){
        Connection connection = conns.get();
        if (connection != null){
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
//      一定要执行remove操作
        conns.remove();
    }


    /**
     * 封装通用的更新操作 INSERT UPDATE DELETE
     * @param sql  sql命令
     * @param params  sql语句需要传进去的参数 因为不确定到底传几个参数  所以参数列表中定义一个动态参数数组 可以多个 也可以零个
     * @return
     */
    public static boolean exeUpdate( Connection conn, String sql,Object...params){


        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            if(params != null){
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i+1, params[i]);
                }
            }
            return ps.executeUpdate()>0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw  new RuntimeException();
        }


    }




    /**
     * 封装查询方法
     * @param t
     * @param sql
     * @param params
     * @return
     */
    public static<T> T queryOne(Class<T> t,String sql,Object...params){
        List<Map<String,Object>> list = queryMaps(sql, params);
        if(list.size() > 0) {

            Map<String,Object> map = list.get(0);
            return mapToBean(map, t);

        }

        return null;
    }
    /**
     * 将map集合转换为特定的类型(利用反射)
     * @param map
     * @return
     */
    public static <T> T mapToBean(Map<String,Object>map,Class<T> t){
        T obj = null;
        try {
            //根据我们提供的类型,我们创建一个对应的对象
            obj = t.newInstance();
            //获取Class中所有属性字段
            Field[] fields = t.getDeclaredFields();
            //遍历获取每一个对象
            for (Field f : fields) {
                String fname = f.getName();
                //获取属性值
                Object value = map.get(fname);
                if(f != null){
                    //设置属性对象的可访问性
                    f.setAccessible(true);
                    //设置值
                    f.set(obj, value);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }



        return obj;
    }

    public static<T> List<T> queryList(Class<T> t,String sql,Object...param){

        List<T> list = new ArrayList<T>();
        List<Map<String,Object>> maps = queryMaps(sql,param);
        //遍历集合中每一条数据(map)
        maps.forEach(m->{
            //将javabean装入list
            T obj = mapToBean(m, t);
            list.add(obj);
        });

        return list;
    }
    /**
     *
     * 执行相关查询结果 并将结果返回为一个List<Map>集合
     * @param sql
     * @param params
     * @return
     */
    public static List<Map<String,Object>> queryMaps(String sql,Object...params){
        List<Map<String,Object>> list = new ArrayList<>();
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
                conn = getConn();
                ps = conn.prepareStatement(sql);

            if(params != null){
                for (int i = 0; i < params.length; i++) {
                    //对ps进行预处理
                    ps.setObject(i+1, params[i]);
                }
            }
            //取到结果集
            rs = ps.executeQuery();
            //获取结果集的元数据对象ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取总列数
            int columnCount = rsmd.getColumnCount();
            //遍历结果集
            while(rs.next()){
                //定义一个map，用于装数据
                Map<String,Object> map = new HashMap<String, Object>();
                //遍历获取每一列的信息
                for (int i = 1; i <= columnCount; i++) {
                    //获取列名称,作为map集合的键
                    String key = rsmd.getColumnName(i);
                    //获取列标签(用于取得列表中的值,因为并不知道用户有没有设置别名 所以统一用label)
                    String label = rsmd.getColumnLabel(i);
                    //获得列值
                    Object value = rs.getObject(label);
                    if(Objects.nonNull(value))
                    {
                        map.put(key, value);
                    }
                }
                list.add(map);
            }

        } catch (SQLException e) {

            e.printStackTrace();
            throw new RuntimeException();
        }

        return list;
    }


    public static int queryCount(String sql,Object...params)  {

        Connection conn = getConn();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /**如果有动态参数数组的话就要对sql进行预处理*/
        if(params != null){
            for (int i = 0; i < params.length; i++) {
                try {
                    ps.setObject(i+1, params[i]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        /**没有的话,直接执行查询语句就行了*/
        ResultSet rs = null;
        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        try {
            if(rs.next()){
                return rs.getInt(1);

            }else{

                return 0;

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }



}

