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

공부하면서 조사한거 계속 추가 작성할 예정 입니다 🌱

<br>

## 1) @Override
- 메소드를 Override 할 것이니 컴파일러에게 알려줍니다
- 상속 받은 부모 클래쓰/인터페이스가 없다!?면 컴파일러에서 오류를 발생시켜줍니다

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
- 주입하려고 하는 객체의 타입이 일치하는 객체를 자동으로 주입해줌
- 필드/생성자/Setter에만 붙일 수 있음
- 필트/Setter에 사용할 경우 기본 생성자가 정의되어 있어야 합니닥

```java
public class AutowiredClassTest {
    @Autowired
    private AutoWiredClass autoWire;
}
```

## 4) RunWith
- 스프링의 테스트 Context 프레임워크의 JUnit 확장 기능을 지정할 수 있츰
- 단위 테스트 작성 시 사용하세호
  
```java
@RunWith(SpringJUnit4ClassRunner.class)
public class RunClassTest {
    @Autowired
    private RunClass autoWire;
}
  ```