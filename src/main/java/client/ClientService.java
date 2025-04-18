package client;

import java.sql.SQLException;
import java.util.List;

public class ClientService {
    private final ClientDao clientDao;

    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public long create(String name) throws SQLException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        return clientDao.create(name);
    }

    public String getById(long id) throws SQLException {
        return clientDao.getById(id);
    }

    public void setName(long id, String name) throws SQLException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        clientDao.setName(id, name);
    }

    public void deleteById(long id) throws SQLException {
        clientDao.deleteById(id);
    }

    public List<Client> listAll() throws SQLException {
        return clientDao.listAll();
    }
}
