package osm.surveyor.sql;

public interface ImplPostgis {
	/**
	 * Postgisにテーブル作成
	 * @param db
	 * @throws Exception
	 */
    public void initTable(Postgis db) throws Exception;
    
    /**
     * テーブルにインスタンスのデータをインサート
     */
    public void insertTable(Postgis db) throws Exception;
}
