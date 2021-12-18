package lk.ijse.secondSem.hibernate.dao;

import java.util.List;

public interface CrudDao<T, ID> {

    boolean add(T t);
    boolean update(T t);
    boolean delete(T t);
    List<T> getAll();
    T search(ID id);



}
