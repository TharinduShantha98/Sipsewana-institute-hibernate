package lk.ijse.secondSem.hibernate.bo.custom.Impl;

import lk.ijse.secondSem.hibernate.bo.custom.CourseBo;
import lk.ijse.secondSem.hibernate.dao.Custom.CourseDAO;
import lk.ijse.secondSem.hibernate.dao.Custom.Impl.CourseDAOImpl;
import lk.ijse.secondSem.hibernate.dto.CourseDTO;
import lk.ijse.secondSem.hibernate.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseBoImpl  implements CourseBo {

    CourseDAO courseDAO = new CourseDAOImpl();


    @Override
    public boolean addCourse(CourseDTO courseDTO) {
       return  courseDAO.add(new Course(courseDTO.getProgramId(),courseDTO.getProgram(), courseDTO.getDuration(),
               courseDTO.getFee()));
    }

    @Override
    public CourseDTO searchCourse(String courseId) {
       Course course = courseDAO.search(courseId);
       return new CourseDTO(course.getProgramId(),course.getProgram(),course.getDuration(),
               course.getFee());
    }

    @Override
    public boolean deleteCourse(CourseDTO courseDTO) {
        boolean delete = courseDAO.delete(new Course(courseDTO.getProgramId(), courseDTO.getProgram(), courseDTO.getDuration(),
                courseDTO.getFee()));
        return delete;
    }

    @Override
    public List<CourseDTO> getAll() {
        ArrayList<CourseDTO> allCourse = new ArrayList<>();

        List<Course> all = courseDAO.getAll();
        for (Course c: all
             ) {
            allCourse.add(new CourseDTO(c.getProgramId(),c.getProgram(),c.getDuration()
                    ,c.getFee()));
        }
        return allCourse;
    }

    @Override
    public boolean updateCourse(CourseDTO courseDTO) {
        boolean update = courseDAO.update(new Course(courseDTO.getProgramId(), courseDTO.getProgram(),
                courseDTO.getDuration(), courseDTO.getFee()));

        return update;
    }
}
