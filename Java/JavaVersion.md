# Java Version 정리

JAVA 버전은 크게 3가지로 나뉜다

### Java SE (Standard Edition)

- Java SE는 자바의 표준 버전으로서, 일반적인 데스크톱, 서버 및 엔터프라이즈 애플리케이션을 개발하는 데 사용
- 주요 버전은 Java SE 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 등이 있음

### Java EE (Enterprise Edition)

- Java EE는 대규모 엔터프라이즈 애플리케이션을 개발하기 위한 확장 기능을 포함한 Java SE의 확장판
- Java EE는 Jakarta EE로 이관되었으며, 주요 버전은 Java EE 5, 6, 7, 8 등이 있음

### Java ME (Micro Edition)

- Java ME는 모바일 및 임베디드 기기용으로 최적화된 자바 플랫폼
- 모바일 기기, 스마트폰, 휴대전화, 개인 디지털 보조기기 등에서 작은 규모의 애플리케이션을 개발할 때 사용


이 중에서, Java SE에 대해서 정리해보려고 한다

## Java SE 버전의 역사

Java Standard Edition은 말 그대로 자바의 표준 버전으로, 자바 언어의 기본적인 문법이나 기능을 포함하는 표준 문법이다.
다양한 API와 라이브러리를 제공해서 어플리케이션을 개발할 수 있도록 지원한다.

API와 라이브러리는 데이터 구조, 파일 입출력, 네트워크, 스레드 등등 다양한 영역을 지원하기 때문에 개발자에게는 꼭 필요한.. 그런 녀석이다.

1. Java SE 1.0 (1996)
    - 최초의 Java SE 버전으로 출시
    - 기본적인 자바 언어 기능 및 라이브러리 제공

2. Java SE 1.1 (1997)
    - 내부적인 개선과 새로운 API 추가
    - JDBC (Java Database Connectivity) 등의 API가 추가됨

3. Java SE 1.2 (Java 2)
    - Java 2로 알려진 버전으로 큰 변화를 가져온 버전
    - Swing GUI 라이브러리, 컬렉션 프레임워크 등이 포함된 버전
    - 자바 플랫폼의 확장성과 기능성이 크게 향상됨

4. Java SE 1.3 (2000)
    - JNDI (Java Naming and Directory Interface)와 JAXP (Java API for XML Processing) 등의 API가 추가됨
    - RMI (Java Remote Method Invocation) 및 CORBA (공통 객체 요청 브로커 아키텍처)의 향상

5. Java SE 1.4 (2002)
    - 자바 웹 스타트 (Java Web Start)와 NIO (New I/O) 패키지 추가
    - XML 처리를 위한 API 개선
    - 정규 표현식을 지원하는 java.uti.regex 추가

6. Java SE 5.0 (Java 5) / Tiger (2004)
    - 가장 혁신적인 버전
    - Generics, Enums, Autoboxing/Unboxing 기능 추가
    - Annotations 처음 도입됨

7. Java SE 6 (Java 6) / Mustang (2006)
    - 자바 컴파일러 및 런타임 성능이 향상됨
    - Pluggable Annotation Processiong API 추가

8. Java SE 7 (Java 7) / Dolphin (2011)
    - Diamond Operator와 String Switch 등의 언어적 향상
    - try-with-resources 문 추가

9. Java SE 8 (java 8) / JDK 8 (2014)
    - 람다 표현식(Lambda Expressions) 도입
    - 스트림 API(Stream API) 추가
    - Date and Time API 추가
    - 디폴트 메소드(Default Methods) 도입
    - 인터페이스의 정적 메소드(Static Methods) 추가
    - PermGen 영역 제거 및 메모리 모델 변경

10. Java SE 9 (Java 9) / JDK 9 (2017)
    - 모듈 시스템 도입(Jigsaw 프로젝트).
    - JShell 도구 추가(대화형 Java 셸).
    - 플로우 API 추가
    - 모듈화된 JDK 제공
    - Interface의 Private Method 추가

11. Java SE 10 (Java 10) / JDK 10 (2018)
    - 지역 변수 형식 추론(Local Variable Type Inference) 추가.
    - 힙 영역에 대한 G1 가비지 컬렉터 개선.

12. Java SE 11 (Java 11) / JDK 11 (2018)
    - Oracle JDK의 유료 지원 종료로 인해 OpenJDK로의 전환.
    - HTTP 클라이언트 및 로컬 변수 형식 추론의 기능적 확장.
    - Z Garbage Collector 도입.
    - 싱글 파일 실행 (Java 파일 직접 실행) 도입
    - Epsilon 가비지 컬렉터 추가

13. Java SE 12 (Java 12) / JDK 12 (2019)
    - Switch 표현식 패턴 추가.
    - 텍스트 블록 추가
    - 사용하지 않는 Nashorn JavaScript 엔진 제거

14. Java SE 13 (Java 13) / JDK 13 (2019)
    - 텍스트 블록(Text Blocks) 추가.
    - Switch 표현식 패턴 개선

15. Java SE 14 (Java 14) / JDK 14 (2020)
    - Records(레코드) 도입.
    - 패턴 매칭(Pattern Matching) 추가.
    - instanceof 패턴 검사 향상
    - 새로운 Switch 표현식 개선

16. Java SE 15 (Java 15) / JDK 15 (2020)
    - 문자열 메소드 개선.
    - Hidden 클래스 추가.
    - 더 나은 Garbage Collector 기능 추가

17. Java SE 16 (Java 16) / JDK 16 (2021)
    - 프로젝트 Panama와 프로젝트 Loom의 초기 미리보기
    - 패턴 인스턴스 생성 제한
    - 암호 해싱과 암호 레코드 업데이트

18. Java SE 17 (Java 17)
    - 패턴 매칭 및 Switch 표현식의 기능적 확장
    - 메모리 액세스 API 추가
    - 지속적인 암호화 데이터 스토리지 지원


## Java LTS 버전

장기적인 지원을 제공하는 LTS 버전도 있습니다. 기업에서 쓰기에 더 안정적이고 예측이 가능합니다.

3개의 LTS를 현재 지원합니다.

~~현재 제가 노예로 일하고 있는 회사는 JDK 8을 사용하고 있는데 저만 마음이 급한가 봅니다.~~

1. **Java SE 8 (Java 8) / JDK 8 (2014)**:
    - 2025년 3월까지 지원 🥲

2. **Java SE 11 (Java 11) / JDK 11 (2018)**:
    - L2026년 9월까지 지원 
    - Java 11은 Oracle JDK의 유료 지원이 종료된 후에도 무료로 사용 가능한 OpenJDK로의 전환과 함께 지원되는 주요 버전

3. **Java SE 17 (Java 17) / JDK 17 (2021)**:
    - 현재 LTS로 선택된 최신 버전
    - LTS로서의 지원 기간은 Oracle JDK의 경우 2029년 9월까지이며, 다른 제공 업체의 경우 이후로도 지원될 수 있음

### 지원을 안한다는 것은?

자바 지원이 종료된다는 것은 Oracle이 공식적인 업데이트와 보안 패치를 제공 안한다는 것임..

예를 들어 자바 8의 지원이 종료된다면, 이전에 릴리즈된 기존 버그나 보안 취약점이 해결이 안되는 것임.

새로운 API를 사용 못하는 거는 당연하고, 보안이나 호환성 문제가 있을 수 있답니다

그리고 자체적으로 지원을 유지하려면 추가적인 비용이 발생할 수도 있어옹 

