# 🙂 WAS (Web Application Server)

- DB 조회나 다양한 로직 처리를 요구하는 Dynamic Contents를 제공하기 위해 만들어진 Application Server
- HTTP 프로토콜을 사용함
- 컴퓨터나 디바이스에 애플리케이션을 수행해주는 미들웨어 (소프트웨어 엔진)
- WAS는 JSP, Servlet 구동 환경을 제공해서 Web Container, Servlet Container라고도 불림
  - 예시: Apache Tomcat, JEUS 등

 

🚚 Container

JSP, Servlet을 실행시킬 수 있는 소프트웨어

<br>

🌍🚛 Web Container

Web Server가 보낸 JSP, PHP 등 파일을 수행한 결과를 다시 Web Server로 보내줌

<br>

---

## 1) WAS 역할
 💡 WAS = Web Server + Web Container


- Web Server 기능들을 구조적으로 분리하여 처리하고자 함
- 분산 트랜잭션, 보안, 메시징, Thread 처리 등 기능을 처리하는 분산 환경에서 사용
- DB 서버와 같이 수행됨

<br>

## 2) WAS 주요 기능
- 프로그램 실행 환경과 DB 접속 기능 제공
- 여러 개의 트랜잭션 (논리적인 작업 단위) 관리 기능
  - 예) Tomcat, JBoss, Jeus, Web Sphere 등

<br>
 

#  🙂 Web Server & WAS 구분하는 이유
 
## 1) Web Server가 필요한 이유
 
<br>

[ 💡클라이언트에서 이미지 파일 (Static contents)를 보내는 과정 ] 

1) 클라이언트는 HTML 문서를 받아옴

2) HTML 문서를 받고 필요한 이미지 파일을 다시 서버로 요청해서 이미지 파일 수신

3) Web Server를 통해 Static contents를 Application Server까지 가지 않고 빠르게 보내줄 수 있음

 
→ Web Server에서는 정적인 콘텐츠만 처리하도록 기능을 분배

→ 서버의 부담을 줄여줌

<br>

## 2) WAS가 필요한 이유
- 웹 페이지는 정적 콘텐츠와 동적 콘텐츠가 모두 존재
- 사용자의 요청에 맞게 적절한 동적 콘텐츠를 만들어서 제공해야함
- Web Server만을 이용한다면 사용자가 요청에 대한 결과 값을 모~~두 미리 만들어 놓고 서비스를 해야 함

→ 자원이 부족해짐 ;;;

<br>

그럼 어떻게?

✅WAS를 통해 요청에 맞는 데이터를 DB에서 가져옴

✅비즈니스 로직에 맞게 그때 그 때 결과를 만들어서 제공함

✅자원을 효율적으로 사용 가능

 
 <br>

## 3) WAS가 Web Server 기능까지 다 하면 되는 거 아냐?

- 서버 부하 방지를 위해 기능 분리 필요
- WAS는 DB 조회, 로직 처리하느라 바쁨
- 단순 정적 콘텐츠는 Web Server에서 빠르게 클라이언트에 제공하는 게 좋음
- WAS가 정적 콘텐츠까지 처리하면 부하 너무 커짐
- 속도 느려짐

→ WAS는 동적 컨텐츠 처리, Web Server는 정적 컨텐츠 처리로 분리

 
🎈 물리적으로 분리해서 보안 강화할 수 있음

- SSL 암복호화 처리에서 Web Server를 사용함
- WAS와 Web Server를 분리하면 무중단 운영이 가능
- fail over, fail back 처리에 유리
- Web Server는 WAS를 Load Balancing을 해줌
- 접근 허용 IP 관리, 세션 관리 등도 Web Server에서 처리하면 효율적

 <br>

💡 자원 이용 효율성, 장애 처리, 배포/유지보수 편의성을 위해 분리
<br>
💡 Web Server를 앞단에, WAS를 뒷단에 두고 필요한 WAS를 Web Server에 Plug-In 형태로 설정하면 효율적인 분산 처리 가능