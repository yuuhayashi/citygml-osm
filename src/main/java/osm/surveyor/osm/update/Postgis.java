package osm.surveyor.osm.update;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import jp.co.areaweb.tools.database.DatabaseTool;
import osm.surveyor.osm.ElementWay;

public class Postgis implements AutoCloseable
{
    public static void main(String[] args) throws Exception {
    	try (Postgis db = new Postgis("osmdb")) {
        	db.initTable();
    	}
    	// db.close()   .... AutoClosable
    }
    
    public Connection con = null;
    
    public Postgis(String dbname) throws ClassNotFoundException, SQLException, IOException {
        con = DatabaseTool.openDb(dbname);
    }

	@Override
	public void close() throws Exception {
        if (con != null) {
            DatabaseTool.closeDb(con);
            con = null;
        }
	}
    
    public void initTable() throws Exception {
    	// Relation
    	String tableName = "tRelation";
        sql("DROP TABLE IF EXISTS "+ tableName +" CASCADE;");
        sql("CREATE TABLE "+ tableName 
            +" ("
                + "id SERIAL PRIMARY KEY, "
                + "action varchar(24), "
                + "timestamp varchar(36), "
                + "uid integer, "
                + "username varchar(64), "
                + "visible boolean, "
                + "version smallint, "
                + "changeset integer, "
                + "orignal boolean);");
        sql("CREATE INDEX ix_"+ tableName +" ON "+ tableName +" (id);");
        
        // Way ElementWay.initTable(db)
        (new ElementWay()).initTable(this);
        
        // Node
        tableName = "tNode";
        sql("DROP TABLE IF EXISTS "+ tableName +" CASCADE;");
        sql("CREATE TABLE "+ tableName 
            +" ("
                + "id SERIAL PRIMARY KEY, "
                + "action varchar(24), "
                + "timestamp varchar(36), "
                + "uid integer, "
                + "username varchar(64), "
                + "visible boolean, "
                + "version smallint, "
                + "changeset integer, "
                + "orignal boolean);");
        sql("CREATE INDEX ix_"+ tableName +"_geom ON "+ tableName +" (id);");
        
        // Member
        tableName = "tMember";
        sql("DROP TABLE IF EXISTS "+ tableName +" CASCADE;");
        sql("CREATE TABLE "+ tableName 
            +" ("
                + "relationid SERIAL PRIMARY KEY, "
                + "ref integer, "
                + "type varchar(24), "
                + "role varchar(24));");
        sql("CREATE INDEX ix_"+ tableName +"_geom ON "+ tableName +" (relationid);");
        
        // Nd
        tableName = "tNd";
        sql("DROP TABLE IF EXISTS "+ tableName +" CASCADE;");
        sql("CREATE TABLE "+ tableName 
            +" ("
                + "wayid SERIAL PRIMARY KEY, "
                + "ref integer);");
        
        // Tag
        tableName = "tTag";
        sql("DROP TABLE IF EXISTS "+ tableName +" CASCADE;");
        sql("CREATE TABLE "+ tableName 
            +" ("
                + "id SERIAL PRIMARY KEY, "
                + "key varchar(64), "
                + "value varchar(2048));");
        sql("CREATE INDEX ix_"+ tableName +"_geom ON "+ tableName +" (id);");
    }

    /*
     * 
    public void toInsert(String tableName, ArrayList<String> header, CsvRecord record) 
        throws Exception 
    {
        String gmlidStr = getStr(header, record, "gmlid");
        String idrefStr = getStr(header, record, "idref");
        int area = getInt(header, record, "area");
        int fixed = getInt(header, record, "fixed");
        int code = getInt(header, record, "code");
        String latStr = record.get(header.indexOf("lat"));
        String lonStr = record.get(header.indexOf("lon"));
        String latlon = String.format(
            "ST_GeomFromText('POINT(%.7f %.7f)',4612)", 
            Double.parseDouble(lonStr),
            Double.parseDouble(latStr)
        );
        String sqlStr = "INSERT INTO "+ tableName 
            +" (gmlid,idref,area,fixed,code,geom) "
            + "VALUES (?,?,?,?,?, "+ latlon +")";
        try (PreparedStatement ps = this.con.prepareStatement(sqlStr)) {
            ps.setString(1, gmlidStr);   // gmlid
            ps.setString(2, idrefStr);   // idref
            ps.setInt(3, area);       // area
            ps.setInt(4, fixed);      // fixed
            ps.setInt(5, code);       // code
            ps.executeUpdate();
        }
        catch (SQLSyntaxErrorException e) {
            System.out.println("107:"+ e.toString());
            if (!(e.toString().startsWith("java.sql.SQLSyntaxErrorException: user lacks privilege or object not found:"))) {
                throw e;
            }
        }
    }
    
    String getStr(ArrayList<String> header, CsvRecord record, String key) {
        int i = header.indexOf(key);
        if (i < 0) {
            return "";
        }
        else {
            return record.get(i);
        }
    }
        
    int getInt(ArrayList<String> header, CsvRecord record, String key) {
        int i = header.indexOf(key);
        if (i < 0) {
            return 0;
        }
        else {
            return Integer.parseInt(record.get(i));
        }
    }
     */

    public void sql(String sql) throws SQLException {
        System.out.println(sql);
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.executeUpdate();
        }
        catch (SQLSyntaxErrorException e) {
            System.out.println("107:"+ e.toString());
            if (!(e.toString().startsWith("java.sql.SQLSyntaxErrorException: user lacks privilege or object not found:"))) {
                throw e;
            }
        }
    }
}
