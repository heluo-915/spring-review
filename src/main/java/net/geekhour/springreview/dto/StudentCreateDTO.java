package net.geekhour.springreview.dto;

import jakarta.validation.constraints.*;

public class StudentCreateDTO {
    @NotNull(message = "id不能为空")
    private Integer id;
    @NotBlank(message = "姓名不能为空")
    private String name;
    @NotNull(message = "年龄不能为空")
    @Min(value = 0,message = "年龄不能小于0")
    private Integer age;
    @NotNull(message = "分数不能为空")
    @DecimalMin(value = "0",message = "分数不能小于0")
    @DecimalMax(value = "100", message = "分数不能大于100")
    private Double score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
