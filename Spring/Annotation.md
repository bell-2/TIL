# Annotation
Annotation 자체는 주석이라는 뜻입니다.
C++/C 다루다가 Java 코드를 봤을 때 가장 재밌게 본 문법이었습니다.

작성자 이름이나 배포 날짜만 적는줄 알았던 주석이...! 다르게 보였기 때문인데요, 특징을 간단히 정리해보겠습니다 💫

---

## Annotation 특징
- AOP(Aspect Oriented Programming)을 편리하게 구성해주는 주석 기능
- 컴파일러에게 코드의 문법 에러를 체크할 때 쓰는 정보를 제공
- 개발 환경 빌드/배포 시 코드를 자동 생성하도록 정보 제공
- 런타임에 어떤 기능을 실행하도록 정보를 제공

<br>

# 자주 쓰는 Annotation

## 1) @Override
- 메소드를 Override 할 것이니 컴파일러에게 알려줍니다
- 상속 받은 부모 클래쓰/인터페이스가 없다!?면 컴파일러에서 오류를 발생시켜줍니다

* @Override 어노테이션 기능
- 해당 메소드가 부모 클래스에 있는 메소드를 Override 했다는 것을 명시적으로 선언
- 자식 클래스에 여러 개의 메서드가 있다면, 쉽게 Override 된 메서드를 알 수 있다
- 문법 체크를 할 수 있다

```java
@Override
public void overrideFunc()
{
    System.out.println("Override");
}
```

## 2) @Deprecated
- 사용하지 않는 메소드라는 정보를 제공
- Deprecated로 표시해놓은 메소드를 사용하는 곳이 있으면 경고해줍니다

```java
@Deprecated
public void deprecatedFunc()
{
    System.out.println("Deprecated 이제 안 써요");
}
```

## 3) @Autowired

```java
@Autowired
@Qualifier("nameAddService")
```
- 해당 변수 및 메서드에 스프링이 관리하는 Bean을 자동으로 맵핑 해줌
  - 변수 타입과 일치하는 컨텍스트 내의 Bean을 찾아줌
- 타입이 일치하는 Bean이 있으면 인스턴스 변수에 주입해줌 (DI)
  - 일반적으로 DI를 위해서는 생성자/수정자 같은 메서드가 필요하지만 @Autowired를 쓰면 메서드가 없어도 주입 가능

- @Resource는 이름으로 의존성 주입! @Autowired는 타입으로 의존성 주입
  - 동일한 Bean 타입이 있다면 @Qualifier를 같이 사용해야 함

### <component-scan> Tag
```xml
<context:component-scan base-package="test.worldTest">
```

### @Qualifier
- @Autowired로 연결한 Bean 목록에서 Bean을 구별해줌

## 4) @RequestMapping
- 요청에 대해 어떤 Controller, 어떤 메소드가 처리할지를 맵핑 해줌

```java
@RequestMapping(value="/bell/v1/name", method=RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> getOpenList(HttpServletRequest request, HttpServletResponse response)
      {
        ...
      }
```

## 5) @Transactional
- 선언적 트랜잭션
- 적용된 범위에서는 트랜잭션 기능이 포함된 프록시 객체가 생성되어 자동으로 commit 혹은 rollback을 진행해줌

```java
@Transactional (propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
public void test()
{
  ...
}
```

## 6) @Scheduled
cron 표현식 사용시에 많이 쓰는 Annotation. 스케줄 정보를 등록할 수 있다

```java
@Scheduled(cron="0 0/1 * * * ?")
```
## 7) @Component
- component-scan을 선언에 의해 특정 패키지 안의 클래스들을 스캔
- Bean Configuration 파일에 Bean을 따로 등록하지 않아도 사용할 수 있음
  - @Component Annotation이 있는 클래스에 대하여 Spring Bean 인스턴스를 생성해줌
