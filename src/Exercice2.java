/**
 * Created by 201250541 on 2015-04-09.
 */
package Exercice2;
import java.sql.*;
import oracle.jdbc.pool.*;

public class Exercice2 {
    public static void main(String[] args) {
        Connection conn = null;
        String user="lemairef";
        String mapasse = "ORACLE1";
        String url = "jdbc:oracle:thin:@205.237.244.251:1521:orcl";
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL(url);
            ods.setUser(user);
            ods.setPassword(mapasse);
            conn = ods.getConnection();
            System.out.println("vous etes connectés ");
            try {
                String sql = "Select NOMEMP, PRENOMEMP, SALAIRE from EMPLOYESBIDON";
                Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stm.executeQuery(sql);
                rs.first();
                System.out.println(rs.getString("NOMEMP") + "  " + rs.getString("PRENOMEMP") + "  " + rs.getInt("SALAIRE"));
                rs.last();
                System.out.println(rs.getString("NOMEMP") + "  " + rs.getString("PRENOMEMP") + "  " + rs.getInt("SALAIRE"));
                rs.absolute(3);
                System.out.println(rs.getString("NOMEMP")+"  "+rs.getString("PRENOMEMP")+"  "+rs.getInt("SALAIRE"));
                rs.relative(3);
                System.out.println(rs.getString("NOMEMP")+"  "+rs.getString("PRENOMEMP")+"  "+rs.getInt("SALAIRE"));

            }catch (SQLException sqlinsert)
            {
                System.out.println(sqlinsert.getMessage());
            }


        }
        catch(SQLException sqlods)
        {
            System.out.println("connexion impossible");
        }
        finally
        {
            try
            {
                if (conn!=null)
                    conn.close();
                System.out.println("connexion fermée");
            }
            catch(SQLException se){}
        }

    }
}
