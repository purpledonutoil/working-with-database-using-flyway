package client;

import databaseService.Database;

import java.lang.ref.Cleaner;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ClientService {
    private final PreparedStatement createSt;
    private final PreparedStatement readSt;
    private final PreparedStatement updateSt;
    private final PreparedStatement deleteSt;
    private final PreparedStatement listAllSt;

    public ClientService(Connection connection) throws SQLException {
        createSt = connection
                .prepareStatement("INSERT INTO client (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);

        readSt = connection
                .prepareStatement("SELECT id, name FROM client WHERE id = ?");

        updateSt = connection
                .prepareStatement("UPDATE client SET name = ? WHERE id = ?");

        deleteSt = connection
                .prepareStatement("DELETE FROM client WHERE id = ?");

        listAllSt = connection
                .prepareStatement("SELECT id, name FROM client");
    }

    public long create(String name) throws SQLException {
        createSt.setString(1, name);
        createSt.executeUpdate();

        ResultSet keys = createSt.getGeneratedKeys();
        if (keys.next()) {
            return keys.getLong(1);
        } else {
            throw new SQLException();
        }
    }

    public String getById(long id) throws SQLException {
        readSt.setLong(1, id);

        ResultSet rs = readSt.executeQuery();

        if (!rs.next()) {
            return null;
        }

        return rs.getString("name");
    }

    public void setName(long id, String name) throws SQLException {
        updateSt.setString(1, name);
        updateSt.setLong(2, id);

        updateSt.executeUpdate();
    }

    public void deleteById(long id) throws SQLException {
        deleteSt.setLong(1, id);

        deleteSt.executeUpdate();
    }

    public List<Client> listAll() throws SQLException {
        List<Client> result = new LinkedList<>();
        ResultSet rs = listAllSt.executeQuery();

        while (rs.next()) {
            Long id = rs.getLong("id");
            String name = rs.getString("name");

            Client client = new Client();
            client.setName(name);
            client.setId(id);
            result.add(client);
        }
        return result;
    }
}
