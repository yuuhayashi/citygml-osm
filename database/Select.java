package jp.co.areaweb.tools.database;

import java.io.*;
import java.sql.*;
import jp.co.areaweb.tools.core.JapaneseString;

/**
 * SQL文が記述されたファイルを読み込んで実行する。
 *
 * ・SELECTコマンドは実行できない。
 */
public class Select
{
    /** コマンドラインラインからdoSQLを実行する。
     * java DoSQL [ＳＱＬファイル名]
     * exp) java dbtool.DoSQL  sqlfile.sql
     * @param args パラメータ
     */
    static public void main(String[] args) {
        if (args.length < 2) {
            System.out.println("exp: java jp.co.areaweb.tools.database.Select [propertieFile] [sqlfile]");
        }
        else {
            Select.doSQL(args[0], args[1]);
        }
    }

    /**
     * sqlfileの中のＳＱＬ文を１行づつ実行する。
     * ・SELECT文は実行できない。
     * ・一つのＳＱＬ文は１行で記述すること。（途中改行はだめ）
     * @param propertieFile		プロパティファイル
     * @param sqlfile SQL文が記述されたファイル。
     */
    public static void doSQL(String propertieFile, String sqlfile) {
        try (Connection conn = DatabaseTool.openDb(propertieFile)) {
            BufferedReader ds = new BufferedReader(new InputStreamReader(new FileInputStream(sqlfile)));
            String sqlStr = "";
            while ((sqlStr = ds.readLine()) != null) {
            	sqlStr = JapaneseString.trim(sqlStr);
            	if (sqlStr.endsWith(";")) {
            		sqlStr = sqlStr.substring(0, sqlStr.length() - 1);
            	}
                System.out.println(sqlStr);
                sqlExecute(conn, sqlStr);
            }
            System.out.println("SQL Finished");
        }
        catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    /**
     * sqlStrを実行する
     * @param conn データベースコネクション
     * @param sqlStr 実行するＳＱＬ文
     * @throws SQLException ＳＱＬ実行エラー
     */
    public static void sqlExecute(Connection conn, String sqlStr) throws SQLException {
        try (Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlStr))
        {
            ResultSetMetaData rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();
            while (rs.next()) {
                for (int column = 1; column <= count; column++) {
                    System.out.print("'"+ rs.getString(column) +"'");
                    if (column < count) {
                        System.out.print(",");
                    }
                }
                System.out.print("\n");
            }
        }
    }
}
