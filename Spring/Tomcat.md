# Tomcat

톰캣 내부 구조 처리
- 클라이언트에서 URL 요청 (예를 들어, http://localhost:8080/hello)
- 톰캣 Thread Pool은 이 요청을 수신
- URL 처리
  - Connector: HTTP 버전에 맞는 요청 연결
  - Engine: URL의 Host로 접근 (www.naver.com)
  - Context: 호스트의 Context로 접근 (/hello)

- Context 내부에는 각각의 서블릿으로 구성, URL에 매칭되는 서블릿에 접근
- 매칭된 서블릿이 실행됨
- 서블릿이 수행한 결과가 클라이언트로 전송됨


톰캣 설정 파일
- server.xml
- web.xml


---

<br>

출처
- https://byungmin.tistory.com/61