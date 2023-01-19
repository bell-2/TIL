# ⭐ WebApplicationContext

- Spring App에서 가장 많이 사용되는 웹 환경에서 사용하는 Application Context
- ApplicationContext을 확장한 인터페이스
  
- XmlWebApplicationContext (default)
- AnnotationConfigWebApplicationContext

<br>

## 🌱 IoC 컨테이너와 Application 기동

- 스프링 IoC 컨테이너는 Bean 설정 메타 정보를 이용해 Object를 생성함
- 생성된 Object로 DI 작업을 수행 -> App이 동작하는 것은 아님
- IoC 컨테이너에 요청하여 Bean Object를 가져와야 App이 동작함 

```java
ApplicationContext appContext = new ...;
Bell bell = appContext.getBean("bell", Bell.class);
// IoC 컨테이너가 만들어준 Bean Object를 가져옴
```

## 🌱 Spring Web Application 동작
- main 서블릿, Application Context를 미리 생성
- Request가 Servlet으로 들어올 때마다 getBean()으로 Bean 가져오기
- 가져온 Bean으로 정해진 메소드 실행

### Servlet Contatiner
- 클라이언트로부터 받은 Request를 받아서 Servlet을 동작시켜 줌
- Servlet은 Web Application이 시작될 때 만들어둔 Bean Object 실행Bean을 Application Context에게 요청해서 받아옴
- 미리 지정해둔 메서드를 호출 -> Spring 컨테이너가 DI 방식으로 Application을 시작해줌

<br>
💡Servlet Container 역할을 해주는 것이 DispathcerServlet

<br>

---

참고 자료: 토비의 스프링 3.1 Vol.2 📗


