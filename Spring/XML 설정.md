# XML 및 Resources
##  📕 pom.xml
- POM(Project Object Model)
- Maven의 빌드 정보를 담고 있는 파일
- 프로젝트 내의 빌드 옵션을 설정할 수 있음
- classpath에 "mybatis-spring-*.*.*.jar" 포함 필요
- 만약 Maven을 사용한다면 아래처럼 의존성 추가를 해줘야 함
```xml
<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis-spring</artifactId>
  <version>3.0.1</version>
</dependency>
```

## 📕 web.xml
- WAS가 처음 구동될 때 web.xml을 읽어서 웹 어플리케이션 설정을 구성함
- DispatcherServlet을 등록해주면서 스프링 설정 파일을 지정함
- 크게 세가지 역할을 해줍니다
  - application context.xml 경로 설정
  - dispatch-servlet.xml 경로 설정
  - servlet-mapping 설정
    - 여기서 url pattern 같은 걸 설정해줍니다

```xml
...
   <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <url-pattern>*.form</url-pattern>
    </servlet-mapping>
    ...
```

## 📕dispatcherServlet.xml
- 초기화 과정에서 지정된 설정 파일을 이용해 스프링 컨테이너를 초기화 시킴

---

### 💡MyBatis
- 개발자가 지정한 SQL, 저장프로시저 그리고 몇가지 고급 매핑을 지원하는 퍼시스턴스 프레임워크
- JDBC로 처리하는 상당 부분의 코드, 파라미터 설정, 결과 매핑을 대신 해줌
- 데이터 베이스 레코드에 Map 인터페이스, 자바 POJO를 설정해서 맵핑하기 위해 XML과 Annotation을 사용하곤한다
- 핵심은 SQL 명령어들이 저장되는 SQL Mapper 연동 부분이다

## 📕spring-mybatis.xml
- Dependency 추가 해줌
- DataSource 설정 및 Mapper 등록

## 📕applicationContext.xml
- ApplicationContext 생성 시 필요한 설정 정보를 담고 있음 
  - Bean 설정 등등

web.xml에서 applicationContext.xml 파일 경로를 설정한다
```xml
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/applicationContext.xml</param-value>
    </context-param>
```
  
### 💡Application Context
- Web Application 최상단에 위치하고 있는 Context