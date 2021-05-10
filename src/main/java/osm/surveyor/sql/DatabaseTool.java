package osm.surveyor.sql;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class DatabaseTool
{
    /**
     * 設定ファイルに従って、データベースコネクションをオープンしてコネクションを得る。
     * 指定のデータベースが存在しないときは新に作成する。
     * @param	propertiesFileName	プロパティファイル
     * @throws ClassNotFoundException 指定のデータベースドライバが見つからなかった
     * @throws SQLException データベース障害
     * @throws FileNotFoundException error
     * @throws IOException error
     * @return データベースコネクション
     */    
    public static Connection openDb(String propertiesFileName) throws ClassNotFoundException,SQLException,FileNotFoundException,IOException {
        Properties properties;

        String driver = null;
        String database = null;
        String user = null;
        String passwd = null;

        properties = new Properties();
        properties.load(new FileInputStream(propertiesFileName +".properties"));
        driver = properties.getProperty("db_driver");
        database = properties.getProperty("db_url");
        user = properties.getProperty("db_user");
        passwd = properties.getProperty("db_passwd");
        
        // dbnameで指定されたＤＢが存在しないときには自動的にＤＢが新たに作成される
        Connection conn = null;
        Class.forName(driver);
        conn = DriverManager.getConnection(database, user, passwd);
        return conn;
    }
    
    /**
     * 設定ファイル("kind.properties")に従って、データベースコネクションをオープンしてコネクションを得る。
     * 指定のデータベースが存在しないときは新に作成する。
     * @throws ClassNotFoundException 指定のデータベースドライバが見つからなかった
     * @throws SQLException データベース障害
     * @throws FileNotFoundException error
     * @throws IOException error
     * @return データベースコネクション
     */    
    public static Connection openDb() throws ClassNotFoundException,SQLException,FileNotFoundException,IOException {
        return openDb("database");
    }
    
    /**
     * データベースコネクションをクローズする。
     * 処理中に例外が発生しても報告しない。
     * @param conn データベースコネクション
     */    
    public static void closeDb(Connection conn) {
        try {
            // Finally, close the connection
            conn.close();
        }
        catch(Exception e) {
            // Print out the error message
            System.out.println(e);
        }
    }

    /**
     * 全角スペースもトリミングするトリムメソッド
     * @param souce トリムする文字列
     * @return トリム処理後の文字列
     */
    public static String trim(String souce) {
        String result = souce.replace('　', ' ');
        return result.trim();
    }
}

