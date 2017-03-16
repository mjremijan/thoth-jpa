package org.thoth.jdbc;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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


    @Test
    public void execute_statement_without_ending_semicolon() throws Exception {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";

        String dbLocation
            = String.format(".\\target\\junit\\DerbyJdbcTest\\%d.db", System.currentTimeMillis());

        String connectionUrl
            = String.format("jdbc:derby:%s;create=true;user=waytogo;password=ogotyaw", dbLocation);

        Class.forName(driver);
        Connection conn = DriverManager.getConnection(connectionUrl);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from sys.sysaliases");
        assertTrue(rs.next());
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void execute_statement_with_ending_semicolon() throws Exception {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";

        String dbLocation
            = String.format(".\\target\\junit\\DerbyJdbcTest\\%d.db", System.currentTimeMillis());

        String connectionUrl
            = String.format("jdbc:derby:%s;create=true;user=waytogo;password=ogotyaw", dbLocation);

        Class.forName(driver);
        Connection conn = DriverManager.getConnection(connectionUrl);
        Statement stmt = conn.createStatement();

        expectedEx.expect(SQLSyntaxErrorException.class);
        ResultSet rs = stmt.executeQuery("select * from sys.sysschemas;");
    }


    @Test
    public void execute_multi_line_statement() throws Exception {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";

        String dbLocation
            = String.format(".\\target\\junit\\DerbyJdbcTest\\%d.db", System.currentTimeMillis());

        String connectionUrl
            = String.format("jdbc:derby:%s;create=true;user=waytogo;password=ogotyaw", dbLocation);

        Class.forName(driver);
        Connection conn = DriverManager.getConnection(connectionUrl);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select *\nfrom sys.sysaliases");
        assertTrue(rs.next());
    }

    @Test
    public void can_a_statement_have_an_ending_comment_NOPE() throws Exception {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";

        String dbLocation
            = String.format(".\\target\\junit\\DerbyJdbcTest\\%d.db", System.currentTimeMillis());

        String connectionUrl
            = String.format("jdbc:derby:%s;create=true;user=waytogo;password=ogotyaw", dbLocation);

        Class.forName(driver);
        Connection conn = DriverManager.getConnection(connectionUrl);
        Statement stmt = conn.createStatement();

        expectedEx.expect(SQLSyntaxErrorException.class);
        stmt.executeQuery("select from sys.sysaliases -- ending comment ");
    }


    @Test
    public void can_a_statement_have_an_inline_comment_NOPE() throws Exception {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";

        String dbLocation
            = String.format(".\\target\\junit\\DerbyJdbcTest\\%d.db", System.currentTimeMillis());

        String connectionUrl
            = String.format("jdbc:derby:%s;create=true;user=waytogo;password=ogotyaw", dbLocation);

        Class.forName(driver);
        Connection conn = DriverManager.getConnection(connectionUrl);
        Statement stmt = conn.createStatement();

        expectedEx.expect(SQLSyntaxErrorException.class);
        stmt.executeQuery("select from \n -- cool comment \n sys.sysaliases ");
    }

}
