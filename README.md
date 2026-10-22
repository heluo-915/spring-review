# Spring Boot + MyBatis 学生管理系统

## 项目简介

这是一个用于学习 Java 后端开发的学生管理练习项目，基于 Spring Boot、MyBatis 和 MySQL 实现。

通过该项目练习 RESTful API 开发、数据库 CRUD、参数校验、统一异常处理、分页查询及 Spring 声明式事务。

## 技术栈

- Java 21
- Spring Boot 4.1.1
- MyBatis
- MySQL
- Maven
- Jakarta Bean Validation

## 已实现功能

- 学生信息新增、查询、修改、删除
- 根据姓名模糊查询学生
- 学生列表分页查询
- 新增学生时的参数校验
- 统一 API 响应格式 `Result<T>`
- 全局异常处理
- 使用 `@Transactional` 实现学生分数转移
- 使用 SQL 条件限制分数范围，并在操作失败时回滚事务

## 项目结构

```text
src/main/java/net/geekhour/springreview/
├── common/          # 统一响应结果
├── controller/      # HTTP 接口
├── dto/             # 数据传输对象
├── entity/          # 实体类
├── exception/       # 业务异常及全局异常处理
├── mapper/          # MyBatis 数据访问层
├── service/         # 业务逻辑层
└── SpringReviewApplication.java
```

## 本地运行

### 1. 环境要求

- JDK 21
- MySQL
- Maven（项目包含 Maven Wrapper）

### 2. 准备数据库

创建数据库：

```sql
CREATE DATABASE spring_student;
```

创建学生表：

```sql
USE spring_student;

CREATE TABLE student (
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    age INT,
    score DOUBLE
);
```

### 3. 配置数据库连接

在 `src/main/resources/application.properties` 中配置数据库连接地址和用户名。

数据库密码使用环境变量 `DB_PASSWORD`，不要将真实密码提交到 GitHub。

### 4. 启动项目

Windows：

```powershell
.\mvnw.cmd spring-boot:run
```

启动前请确保 MySQL 服务正常运行，并已配置 `DB_PASSWORD` 环境变量。

## 学习收获

通过该项目，我练习了 Spring Boot 常见的三层架构、MyBatis 注解式 SQL、RESTful API 设计、参数校验、统一异常处理和事务回滚。

项目主要用于个人学习和技术巩固，后续将继续完善测试与业务规则。
