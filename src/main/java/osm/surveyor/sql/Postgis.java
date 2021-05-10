package osm.surveyor.sql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

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
        (new ElementWay(0)).initTable(this);
        
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

	@Override
	public void close() throws Exception {
        if (con != null) {
            DatabaseTool.closeDb(con);
            con = null;
        }
	}
}
