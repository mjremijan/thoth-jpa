package org.thoth.jdbc;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class DerbyJdbcTest {

    @Test
    public void createNewDatabase() throws Exception {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";

        String dbLocation
            = String.format(".\\target\\junit\\DerbyJdbcTest\\%d.db", System.currentTimeMillis());

        String connectionUrl
            = String.format("jdbc:derby:%s;create=true;user=waytogo;password=ogotyaw", dbLocation);

        Class.forName(driver);
        Connection conn = DriverManager.getConnection(connectionUrl);
        Statement stmt = conn.createStatement();
        stmt.execute("create schema waytogo");
        conn.commit();
        conn.close();

        File f = new File(dbLocation);
        assertTrue(f.exists());
        assertTrue(f.isDirectory());
    }

}
