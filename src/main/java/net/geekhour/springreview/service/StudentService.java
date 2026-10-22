package net.geekhour.springreview.service;

import net.geekhour.springreview.entity.Student;
import net.geekhour.springreview.exception.BusinessException;
import net.geekhour.springreview.exception.InvalidParameterException;
import net.geekhour.springreview.mapper.StudentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    private final StudentMapper studentMapper;
    public StudentService(StudentMapper studentMapper){
        this.studentMapper=studentMapper;
    }
    public String sayHello(){
        return "Hello from Service";
    }

    public Student findById(Integer id) {
        Student student = studentMapper.findById(id);
        if (student == null) {
            throw new BusinessException("学生不存在");
        }
        return student;
    }

    public int addStudent(Student student){
        return studentMapper.insertStudent(student);
    }

    public int updateStudent(Integer id, Student student){
        int rows= studentMapper.updateStudent(id,student);
        if(rows==0) throw new BusinessException("学生不存在");
        return rows;
    }

    public int deleteById(Integer id){
        int rows= studentMapper.deleteById(id);
        if(rows==0) throw new BusinessException("学生不存在");
        return  rows;
    }

    public List<Student> searchByName(String name){
        return studentMapper.searchByName(name);
    }

    public List<Student> findPage(Integer page, Integer pageSize){
        if(page<=0||pageSize<=0) throw new InvalidParameterException("分页参数不合法");
        Integer offset= (page-1)*pageSize;
        return studentMapper.findPage(offset,pageSize);
    }

    @Transactional
    public void transferScore(Integer fromId, Integer toId, Double amount){
        if(amount==null||amount <= 0)throw new InvalidParameterException("转移金额不合法");
        int row1=studentMapper.decreaseScore(fromId,amount);
        if(row1==0) throw new BusinessException("转出学生不存在或分数不足");
        int row2=studentMapper.increaseScore(toId,amount);
        if(row2==0) throw new BusinessException("转入学生不存在或分数超过上限");
    }

}
