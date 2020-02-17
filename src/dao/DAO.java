package dao;

import java.util.List;

public interface DAO<F> {
    F get(long id);

    List<F> getAll();

    void save(F x);

}
