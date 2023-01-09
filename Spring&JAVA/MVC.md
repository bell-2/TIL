# 🌱 Spring MVC 구조
스프링 프레임워크는 MVC 구조로 이루어짐

💡 View - Controller - Service - Serviceimpl - DAO - DAOimpl - DTO
 
 <br>

## 1) View
- 사용자에게 보이는 화면
- 스프링은 JSP를 통해 화면을 구성함
- Controller를 통해 Backend 서버와 연결함
 
 <br>

## 2) Controlloer
- View와 Service 사이를 연결해줌.
- 클라이언트에서 입력한 URL에 맞게 View를 보여주고, 데이터를 Service로 전달해줌
```java
@RequestMapping(value = "/") 
public String home()
{
	service.method();
  return "index"; 
} // localhost:port/로 접속한 클라이언트에게 index.jsp를 반환한다.
 ```

<br>

## 3) Service
- 실제 로직을 처리하는 역할
- 모든 기능은 Service에서 만들어짐.
- Controller를 통해 View에 연결되고, DAO를 통해 DB와 연결됨
 
 <br>

## 4) DAO
- Data Access Object
- 프로젝트와 DB를 연결해줌 
- Mapper에 SQL을 명시하고 Mapper와 함께 DB 데이터를 주고 받음
- JDBC, iBATIS(MyBatis), 하이버네이트 등 지원 기능 제공. 
- 데이터베이스 프로그래밍 쉽게 가능

<br>
😬 Service의 Serviceimpl 와 DAO의 DAOimpl: interface를 구현한 구조
 
 <br>

## 5) DTO
- Data Transfer Object, VO(Value Object)라고도 불림
- MVC 구조 사이사이에서 데이터 교환을 위한 오브젝트.
- getter/setter 함수가 이에 해당됨
- 주로 DB 테이블과 매칭됨
 
```java
public class temp {

    private String id;
    private String pwd;

    public String getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getPwd() {return pwd;}
    public void setPwd(String pwd) {this.pwd = pwd;}
}
```