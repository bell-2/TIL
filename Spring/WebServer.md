# 🌱 Web Server

웹 서버의 개념은 소프트웨어와 하드웨어적으로 나눠 설명할 수 있다

<br>

### 소프트웨어 개념
웹 브라우저와 같은 클라이언트로부터 HTTP 요청을 받아, html, jpeg, css 같은 정적인 콘텐츠를 제공하는 컴퓨터 프로그램

### 하드웨어 개념
Web 서버가 설치되어 있는 컴퓨터

<br>

즉, HTTP 프로토콜을 기반으로 하여 클라이언트 (웹 브라우저 또는 웹 크롤러)의 요청을 서비스로 하는 서버이고, Apache Server, Nginx, IIS 등등이 있다

<br>

🤔 Apache Server (사용률 1등)


- 아파치 소프트웨어 재단에서 관리하는 오픈 소스, 크로스 플랫폼 HTTP 웹 서버 소프트웨어
- Unix, Linux, MS Window 등에서도 무료로 운용 가능

 <br>

😉 Nginx (사용률 2등)
- 가볍고 높은 성능을 목표로 하는 소프트웨어
- 웹 서버, 리버스 프록시, 메일 프록시 기능을 가짐

<br>

😉 IIS (Internet Information Services) (사용률 3등)
- MicroSoft Window를 사용하는 서버들을 위한 인터넷 정보 서비스
- 구 이름은 Internet Information Server

<br>

---

## (1) Web Server 기능

<br>

👀 1) Static contents 제공
- 정적인 콘텐츠 제공
- WAS (Web Application Server)를 거치지 않고 자원 제공


👀 2) Dynamic contents 제공을 위한 요청 전달
- 클라이언트의 Request를 WAS로 보내고, WAS가 처리한 결과를 클라이언트에게 Response로 전달

 
<br>

---
<br>

😂 참고) Static Contents & Dynamic Contents

**Static Contents
**
- 변하지 않는 콘텐츠. 캐시에 저장해놓고 씀
- 캐시에 정적 파일의 사본(ex. 이미지)을 저장하고 클라이언트가 동일한 내용을 제공할 때 빠르게 전달해줌

**Dynamic Contents
**
- 계속 변하는 컨텐츠. HTML 파일로 저장되지 않음
- 서버 측에서 생성되기 때문에 캐시가 아니라 원본 서버에서 만들어서 줌
- 서버에서 사용자 로그인/이벤트 대한 정보로 HTML 파일 생성해줌

예시로는 뭐가 있냐

ex 1) 뉴스 웹 사이트: 기사가 계속 업데이트되고 사용자 로그인 상태마다 페이지가 달라짐

ex 2) 페이스북 뉴스 피드: 사용자마다 다르게 보이고 댓글, 게시물 등등 변경됨