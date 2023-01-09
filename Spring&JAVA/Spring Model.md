# 🤷‍♂️ Spring 3가지 프로그래밍 모델
<br>

### Spring Container (또는 Application Context)

<br>

- 스프링은 런타임 엔진을 제공함
- 설정 정보를 참고하여 App을 구성하는 Object를 생성하고 관리함 → 이 Object가 Bean(빈)임
  - 스프링 컨테이너가 관리해주는 Object
- 의존성 갖는 객체에 DI(의존성 주입, Dependency Injection)를 통해 Object간 결합도록 낮춰줌

<br>

💡 Bean : Spring에서 Applicatiopn을 구성하는 Object
 
---

<br>

## 공통 프로그래밍 모델

<br>

- 스프링은 Bean이 생성되고 동작하는 틀을 제공해주고, 어떻게 코드를 작성해야하는지에 대한 기준도 제공함 → 이를 프로그래밍 모델이라고 함
- 스프링은 크게 3가지 프로그래밍 모델을 지원함 (권장)

<br>
 
## 1) IoC / DI 👏
오브젝트의 생명 주기와 의존 관계에 대한 프로그래밍 모델.

- 유연함
- 확장성이 뛰어난 코드 작성 가능
- 객체지향 설계 원칙디자인 패턴의 핵심 원리를 담고 있음
- 주로 환경설정을 담당하는 XML 파일에 의해 설정되고 수행됨
 
<br>

### IoC (Inversion of Control)
- 말 뜻대로 제어의 역전
- 사용자가 Object를 생성하지 않고 스프링이 진행 해줌 → 경량 컨테이너
- Service, DAO 같은 객체를 사용자가 직접 생성 (new)를 하지 않음
- @Autowired를 통해 받아 사용함

<br>

```java
public class order {
	private customer customer;
  	
  	public order() {
  		this.customer = new customer();
  	}
  }
  // Spring 프로젝트
  public class order {

  	@Autowired
	private customer customer;

  }
```

<br>

- 스프링 컨테이너 (IoC Container)는 프로젝트에서 사용되는 Object를 Bean으로 관리하고 있음
  - @Autowired를 통해 Object를 주입해줌
    - 기존 Java 에서는 new로 생성해서 파라미터로 다른 object로 보내거나 소멸함 → new & delete 등 사용자가 제어를 직접 진행함
 
<br>

💡 Spring에서는 제어를 사용자가 아니라, Spring Framework가 진행 해준다

😷 Spring Bean vs JAVA Bean

- Spring Bean
  - Spring Container가 관리하는 객체
  - 개발자가 아닌 스프링 컨테이너가 생성해 생명 주기 관리를 해줌.
  - 필요한 곳에 의존성을 주입해주는 객체들을 의미
- JAVA Bean
  - 데이터 전달을 위해 사용되는 자바 객체
  - 스프링의 DTO(VO)와 유사한 개념
  - private 변수와 Getter/Setter만을 가지고 있고 직렬화가 가능한 객체

<br>

### DI (Dependency Ingection)

- 말 그대로 의존성 주입
- 스프링에서 IoC 구조를 만드는 방식
- IoC는 다른 프레임워크와 스프링 차별점이 부족해서 만들어진 개념.

<br>

😬 DI 사용하는 이유

- 객체간의 의존성을 줄이기 위함
- 밖에서 객체를 생성해 넣어주기 때문에 재사용성 ↑, 수정 용이

<br>

기존 프로젝트는 CRUD 함수가 필요함;;;
```java
// 기존 자바 프로젝트
class concept_api implements Post(){}
class concept_Spring implements Post(){}

class Blog_log() {
    private Post post; // 블로그 글 클래스

    public Blog_log() {
  	    this.post = new concept_api();
	    this.post = new concept_Spring();
    }
}
```
<br>

의존성을 주입해 DI를 사용하면 Blog_log 하나의 클래스로 관리가 가능하다

```java
private Post post;

    public Blog_log(Post post) {
    	this.post = new Post();
    }
```

<br>


## 2) PSA

Portable Service Abstraction, 포터블 서비스 추상화

- 특정 환경이나 서버, 기술에 종속되지 않음 → 유연하게 App 개발 가능
- 스프링은 추상화 계층으로 구체적인 기술/환경에 종속되지 않도록 해줌
  - MyBatis, JPA 등 세부 기술에 대한 에러도 추상화해서 에러를 처리하도록 해줌
  - 추상화를 위해 Proxy 패턴과 같은 디자인 패턴이 자주 사용된다고 함
 
<br>

## 3) AOP
Aspect Oriented Programming, 관점 지향 프로그래밍 (OOP 보다 좀 더 발전된 개념)
- App에 공통적으로 나타나는 부가적인 기능을 독집적으로 모듈화하는 프로그래밍 모델
  - 로깅, 보안, 트랜잭션 등
- 스프링은 AOP를 지원해서 엔터프라이즈 서비스를 적용하고도 책임을 분리하여 깰끔하게 함. 객체지향 스럽게 개발하도록 해줌.
- 스프링은 @Transactional 이라는 선언적 트랜잭션 기능을 구현해서 AOP를 사용할 수 있게 해줌

<br>

🏢 Enterprise Service (엔터프라이즈 서비스)

- 외부 데이터 조회 및 외부 시스템에서 데이터를 가져오기 위한 파이프라인.
- Sync/Async 하게 데이터를 처리할 수 있음.
- Web 서비스, HTTP 같은 다중 프로토콜을 사용할 수 있음
 

## 4) POJO (Plain Old Java Object)
자바가 너무 무거워서, 스프링에서 자바 개념만 기본적인거만 가져와서 하자! 한거

--- 

<br>
 
## API

- Application Programming Interface, 응용 프로그램 프로그래밍 인터페이스
- 운영체제나 프로그래밍 언어가 제공하는 기능을 제어할 수 있게 만든 인터페이스
- 스프링은 엔터프라이즈 앱을 개발할 때, 다양한 영역에서 바로 활용할 수 있는 API를 제공함
- 👀 Layerd Architecture(계층화 아키텍쳐)에서 필요한 주요 기술을 일관된 방식으로 사용할 수 있도록 함
  - function, class, 오픈 소스 기술 등등 지원해줌
  - ex) 클라이언트의 Get request를 처리하는 @GetMapping 같은 API를 제공함
- 지니야~ 같은걸 만들고 싶다!?면 TTS 같은 UI는 구글이나 네이버꺼 API를 사용하여 개발할 수 있다