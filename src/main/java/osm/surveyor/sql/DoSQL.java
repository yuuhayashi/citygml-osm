package osm.surveyor.sql;

import java.io.*;
import java.sql.*;


/**
 * SQL文が記述されたファイルを読み込んで実行する。
 *
 * ・SELECTコマンドは実行できない。
 */
public class DoSQL
{
    /** コマンドラインラインからdoSQLを実行する。
     * java DoSQL [ＳＱＬファイル名]
     * exp) java dbtool.DoSQL  sqlfile.sql
     * @param args パラメータ
     */
    static public void main(String[] args) {
        if (args.length < 2) {
            System.out.println("exp: java dbtool.DoSQL [propertieFile] [sqlfile]");
        }
        else {
            DoSQL.doSQL(args[0], args[1]);
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
            try (BufferedReader ds = new BufferedReader(new InputStreamReader(new FileInputStream(sqlfile)))) {
                String sqlStr = "";
                while ((sqlStr = ds.readLine()) != null) {
                    sqlStr = JapaneseString.trim(sqlStr);
                    if (sqlStr.endsWith(";")) {
                        sqlStr = sqlStr.substring(0, sqlStr.length() - 1);
                    }
                    System.out.println(sqlStr);
                    sqlExecute(conn, sqlStr);
                }
            }
            System.out.println("SQL Finished");
        }
        catch(IOException | ClassNotFoundException | SQLException e) {
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
        // Create a statement object
        Statement stat = conn.createStatement();

        // For compatibility to other database, use varchar(255)
        // In Hypersonic SQL, length is unlimited, like Java Strings
        stat.execute(sqlStr);

        // Close the Statement object, it is no longer used
        stat.close();
    }
}
