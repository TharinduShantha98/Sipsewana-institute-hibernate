package lk.ijse.secondSem.hibernate.dao.Custom.Impl;

import lk.ijse.secondSem.hibernate.dao.Custom.StudentDAO;
import lk.ijse.secondSem.hibernate.entity.Student;

import java.util.List;

public class StudentDAOImpl  implements StudentDAO {
    @Override
    public boolean add(Student student) {
        return false;
    }

    @Override
    public boolean update(Student student) {
        return false;
    }

    @Override
    public boolean delete(Student student) {
        return false;
    }

    @Override
    public List<Student> getAll() {
        return null;
    }

    @Override
    public Student search(String s) {
        return null;
    }
}
