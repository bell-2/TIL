# Wrapper Class (래퍼 클래스)


``` java
 int id;
List<Integer> idList;
```
int와 Integer의 차이를 아십니까? 🫥

우선 Integer는 Wrapper class 입니다.
기본 타입, 예를 들어 int 같은 데이터의 타입을 객체로 다룰 때 사용합니다.

즉, 기본 타입을 객체로 표현해야할 경우 Wrapper Class를 쓴답니다.

<br>

## Wrapper Class를 왜 써야할까? 😐
사실 쓰고는 있는데, c나 c++에서도 wrapper class 없이 잘(?) 살았는데 왜 사용하는 것인지 이해가 가지 않았다.
여러 글을 읽어보고 정리한건 아래와 같다.

<br>

### 1. 기본 타입 값을 보호하기
Class나 구조체로 만들고, public/private/protect... 등의 접근 권한을 주는 이유와 비슷하다고 생각한다.
외부에서 값을 변경하지 못하게 하기 위해서인데, 초콜릿 포장하듯이 감싸 놓은 것이다 ㅋ
<br>
<br>

#### 박싱
- 기본 타입 (int) 값을 포장하여 Wrapper Class화 하는 것
#### 언박싱
- Wrapper Class에서 포장을 풀러 기본 타입의 값을 가져오는 것
  - 이 때는 자동으로 값이 매핑이 됩니다
  - 
<br>

### 2. 자바의 모든 클래스의 조상님은 Object
자바의 최상위 클래스는 Object 이죠 ㅋ 
그리고  Object 클래스 아래에는 숫자를 다루는 부모 클래스가 있는데, 얘는 Number라는 클래스로 칭합니다.

아무튼! 객체화 시키기 위해 한번 더 감쌌다고 생각합니다.

자주 사용하는 java.util 같은 패키지도 다루는 기본 단위가 Object가 되겠죠?
그래서 아래와 같이 ArrayList를 예를 들어본다면, 에러가 나는 이유가 그 때문으로 추정 됩니다.

<br>

```java
List<Integer> idList; // compile 성공
List<integer> idList; // 실패!
```

---
<br>

찾다보니 멀티 스레드 관련 얘기가 나와서 좀 더 찾아본 후 추가하겠다.