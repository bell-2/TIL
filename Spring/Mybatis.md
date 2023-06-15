# Mybatis

Mybatis는 자바 퍼시스턴스 프레임워크의 하나로 SQL 쿼리 등을 맵핑해주는 프레임워크랍니다.
<br>
맵핑 방식은 XML이나 Annotation을 사용해서 해줄 수 있는데용
객체 지향 언어인 자바에서 DB 관련 작업을 더 똑똑하게 할 수 있게 도와줍니다. <br>
JDBC를 사용하여 DB 작업을 캡슐화 해주고 코드와 파라미터 설정이랑 결과 값을 맵핑해주기도 하고 <br>
중복되는 쿼리를 제거해주기도 하고, SQL 쿼리를 xml 파일로 따로 분리하여 구성할 수 있어요 <br>
비즈니스 로직의 코드 부분과 SQL을 분리할 수 있는 것 만으로도 큰 이점이 있는 프레임워크라고 생각 됩니다 

<br>

## 🫡 특징
- 쉬운 접근성, 코드가 간결함
- JDBC API로 JDBC 기능을 대부분 제공하고 있음. (JDBC 코드를 소스 코드에서 작성 안해도 되서 깔꼼)
- SQL을 그대로 사용해서 DAO에서 Object 중심으로 개발 가능
- SQL 변경이 됐을 때, 자바 코드를 수정하거나 새로 빌드 하지 않아도 됨

<br>

## Spring에서 MyBatis 동작

![Alt text](image.png)

소스 코드에서는 SQL 쿼리를 담고 있는 Mapper 파일을 통해 MyBatis를 사용할 수 있습니다
그러면 MyBatis에서 JDBC API를 사용해서 알아서 해주는 것이지요

+ ORM (Object Relational Mapping)
객체와 관계형 데이터베이스를 맵핑해서 데이터 베이스 테이블을 객체 지향적으로 사용하기 위한 기술

<br>

![Alt text](image-1.png)


<br>

## 🗨 설치
Mybatis는 mybatis-x.x.x.jar 파일을 classpath에 구성해줘야 합니다

만약 Maven을 사용할 경우에는 pom.xml에 dependency 설정을 추가해줘야 합니다.

```xml
<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis</artifactId>
  <version>x.x.x</version>
</dependency>
```
<br>

### 💨 MyBatis 설정 파일을 등록 (mybatis-config.xml)
DB 접속 주소정보나 Mapping 되는 파일 경로를 설정해줍니다. 

- environments: DB 연결 설정 정보 설정
- settings: Mybatis 설정과 관련된 기본 세팅 설정 (log 설정 등)
- typeAliases: 매칭할 mapper의 SQL 태그의 별칭을 설정
- environments: DB 연결 설정
- mappers: mapper.xml 파일 연결

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "HTTP://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <settings>
...
        <setting name="jdbcTypeForNull" value="NULL" />

...
    </settings>
</configuration>
```

참고로 jdbcTypeForNull은 데이터가 만약 Null로 전달이 되었다면 빈칸이 아니라 null로 인식을 해라라는 뜻입니다
value에 들어가는 값은 대문자로 기입해줍니다.

<br>

### 💨 Spring Bean 설정 파일 (spring-mybatis.xml)
<br>


## 🗨 SqlSessionFactory
Mybatis는 SQL 실행하고 Transaction 관리를 해주는 클래스인 SqlSessionFactory 인스턴스를 사용합니다. <br>
SqlSessionFactory는 SqlSessionFactoryBuilder를 사용해서 만들 수 있습니다.  <br>
Sql SessionFactory에서 SqlSession을 생성 합네다

<br>

### 💨SqlSessionFactoryBean 생성
Mybatis 설정 파일로 SqlSessionFactory를 생성해줍니다. 이 친구는 Spring Bean으로 등록해주면 돼용 <br>
Mybatis-Spring을 사용하는 경우에는 SqlSessionFactory를 주입 받습니다

<br>

### 💨 SqlSessionFactoryBuilder
설정 파일과 등록해준 Bean으로 SqlSessionFactory를 생성해줘요

<br>

### SqlSession
SQL 실행이나, Transaction 관리를 실행해줍니다. 스레드마다 생성돼요

<br>

## 🗨 SQL 쿼리 Mapping 파일 작성
<br>

<<<<<<< HEAD
```SQL
SELECT user_addr FROM user_info WHERE user_name='bell';
```
MyBatis의 사용 목적 중 하나는 DAO 처리 로직과 SQL 문을 분리하는 것인데요, 위의 쿼리를 수행할 수 있는 SQL 쿼리를 SQL Mapper 파일에 작성해봅시다.

<br>

아래는 UserMapper.xml 파일 입니다

<br>

```xml
<?xml version="1.0" encoding="UTF-8"?>
<mapper namespace="com.test.dao.UserDao">

  <select id="getUserAddr" parameterType="String" resultMap="String">
    SELECT user_addr FROM user_info WHERE user_name=#{userName};
  </select>

</mapper>
```

<br>

이렇게 작성할 수 있겠네요. 하나씩 봐봅시다.

### 1. mapper

SQL Mapper xml 파일의 root 입니다. <br>
SQL 쿼리들을 <mapper></mapper> 사이에 작성해주면 됩니다.

<br>

### 2. <select/insert/update/delete>

되게 직관적이라고 생각이 든 구문입니다.
SELECT 할 쿼리는 select로 써주면 됩니다 ㅋ
id는 SQL 쿼리들을 구분해주는 값이라서, 저는 해당 쿼리를 나타내는 이름으로 사용하고 있습니당

<br>

### 3. parameterType

쿼리에 사용되는 객체의 Property 값으로, #{이름} 형식으로 쓸 수 있습니다. 조건절에 주로 사용 됩니다.
만약 조건이 여러개라면? 

<br>

Mybatis의 가장 큰 장점은 쿼리를 작성할 때 나타납니다
DAO 로직에서 쿼리문을 실행만 해주면 된답니다.
간단한 SELECT 쿼리로 예시를 들어보겠습니다.

```java
public class PersonDAO
{
  @Autowired
    private SqlSession sqlSession;
  public HashMap<String, String> getPerson(int id){
    // 
    return sqlSession.selectList("selectPerson", id);
  }
}
```

DAO 로직에서 호출하고 싶은 쿼리 id를 불러봅시다.


```java
{
  HashMap<String, Object> param = new /*생략*/;
  param.put("userName", "bell");
  param.put("userAge", 25);
  sqlSession.selectOne("getUserAddr", param);
}
```


```SQL
<select id="getUserAddr" parameterType="hashMap" resultMap="String">
    SELECT user_addr FROM user_info WHERE user_name=#{userName} AND user_age > #{userAge};
</select>
```


<br>

### 4. resultMap

쿼리를 수행하고 얻고 싶은 실행 결과의 Type 입니다.
조회할 내용이 여러 개면 Type을 hashMap이나 클래스 등으로 사용할 수 있습니다.



---

출처

https://spring.tistory.com/3

https://velog.io/@skarb4788/SPRING-MyBatis
