# Java의 Hash 알고리즘 지원 클래스 MessageDigest

요즘 핫한 보안 문제 ❗

이번에 신규 개발하는 기능에도 보안을 위해 사용자의 정보가 담긴 내용은 해시 값을 사용하여 암호화를 하기로 했답니다.

Java에서 해시 생성 할 때 많이 쓰는 클래스는 MessageDigest인데, 오늘은 이 친구를 정리해보겠습니다.

<br>

## 🔐MessageDigest
<br>

https://docs.oracle.com/javase/7/docs/api/java/security/MessageDigest.html

공식 API 문서 내용을 인용하자면~

MessageDigest는 어플리케이션에서 SHA-1이나 SHA-256 같은 digest 알고리즘으로 메시지를 만드는 기능을 제공해 줍니다. <br>
(그래서 클래스 이름이 이랬군요)

임의 크기(arbitrary-sized)를 사용하고, one-way 해시 함수를 구현해서, 고정 길이의 해시 값을 출력해줍니다.

<br>

```java
MessageDigest md = MessageDigest.getInstance("SHA-256");
```

선언은 위에 예시처럼 해줄 수 있는데, MessageDigest object는 초기화된 상태로 시작이 됩니다.

<br>

결과를 출력할 때 중요한 함수가 몇 개 있답니다.

<br>

### 🔑 getInstance("SHA-256")

<br>

```java
md.getInstancd("SHA-256");
```

getInstance() 파라미터에 사용하고자 하는 알고리즘 이름을 적어주면, MessageDigest Object가 생성 됩니다.
만약 알고리즘 이름이 유효하지 않은 방식이라면 Exception으로 NoSuchAlgorithmException가 오기 때문에, try-catch로 처리해줍시다.
구현 가능한 알고리즘 종류는 아래 사이트에서 확인할 수 있습니다.

https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#MessageDigest

<br>

### 🔑 update()

<br>

```java
md.update(("문자열").getBytes())
```

update()를 사용해서 해시를 생성하고자하는 문자열을 MessageDigest에 update 해줍니다.
update()를 호출할 때마다 MessageDigest object에 저장된 digest 값이 갱신이 됩니다
(reset() 함수를 호출해서 초기화 해줄 수도 있음)

<br>

### 🔑 digest()

<br>

```java
byte[] hashValue = md.digest();
```

update된 데이터를 해시 값으로 완성해줍니다. 결과 값은 toString()으로 받을 수도 있답니다.

