package net.geekhour.springreview.mapper;

import net.geekhour.springreview.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("SELECT id, name, age, score FROM student WHERE id = #{id}")
    Student findById(@Param("id") Integer id);

    @Insert("INSERT INTO student (id, name, age, score) VALUES (#{id}, #{name}, #{age}, #{score})")
    int insertStudent(Student student);

    @Update("UPDATE student SET name = #{student.name}, age = #{student.age}, score = #{student.score} WHERE id = #{id}")
    int updateStudent(@Param("id") Integer id, @Param("student") Student student);

    @Delete("DELETE FROM student WHERE id = #{id}")
    int deleteById(@Param("id")Integer id);

    @Select("SELECT * FROM student WHERE name LIKE CONCAT('%', #{name}, '%')")
    List<Student> searchByName(String name);

    @Select("SELECT * FROM student ORDER BY id LIMIT #{pageSize} OFFSET #{offset} ;")
    List<Student> findPage(@Param("offset") Integer offset,@Param("pageSize") Integer pageSize);

    @Update("UPDATE student set score=score-#{amount} WHERE id = #{id} AND score >= #{amount}")
    int decreaseScore(@Param("id") Integer id,@Param("amount") Double amount);

    @Update("UPDATE student set score=score+#{amount} WHERE id=#{id} AND score + #{amount} <= 100")
    int increaseScore(@Param("id") Integer id,@Param("amount") Double amount);
}
