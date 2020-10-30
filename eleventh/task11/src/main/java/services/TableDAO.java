package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class TableDAO {

    private Connection conn;

    public TableDAO() {
        this.conn = (new DBWorker()).getDBConnection();
    }

    public void destroy() throws SQLException {
        conn.close();
    }

    public HashMap<String, String> makeTable() throws SQLException {
        String command = "SELECT\n" +
                "    a.name,\n" +
                "    STRING_AGG (\n" +
                "                    b.name,\n" +
                "                    ','\n" +
                "                    ORDER BY\n" +
                "                        b.name\n" +
                "        ) books\n" +
                "FROM\n" +
                "    author a\n" +
                "        INNER JOIN books b on b.author_id = a.id\n" +
                "GROUP BY\n" +
                "    a.name;";

        PreparedStatement st = conn.prepareStatement(command);
        ResultSet set = st.executeQuery();

        HashMap<String, String> map = new HashMap<>();

        while (set.next()) {
            String name = set.getString("name");
            String authors = set.getString("books");
            map.put(name, authors);
        }
        return map;
    }
}
