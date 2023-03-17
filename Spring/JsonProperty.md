# Json  명명규칙 상관 하지 않긔

클라이언트와 연동 규격을 정의하며, JSON 형식을 변경하게 되었습니다.
<br>

원래 사용하던 연동 방식은 카멜 케이스 (Camel Case) 방식이었는데, 
<br>
연동에 있어 최대한 빠르게 서비스를 하기 위해서 다 소문자로 변경해달라는 요구사항이 있어서 @JsonProperty를 사용해서 변경했습니다.
<br>

기존에는 REST API 메시지를 받을 때 @RequestBody를 사용해서 처리하고 있었어서 변수 이름을 바꾸는건 비효율적이었다 💦

<br>

## 🕒 JSON 명명 규칙 3가지
표준은 아니지만 아래의 3가지 스타일로 많이 사용한다.

- Pascal
- camelCase
- snake_case

만약 내 이름을 표현하기 위한 네이밍을 하고 있다면 

```java
// Pascal
MyName
// camelCase
myName
// snake_case
my_name
```
이렇게 사용할 수 있답니다.

<br>

서버/ 클라이언트에서 사용하는 JSON 형식이 다르다면 처리하고자하는 데이터의 Key가 달라질 수 있기 때문에
<br>

@JsonProperty나 @JsonNaming를 사용하여 Key 네이밍 형식만 바꿔주는게 매우 효율적이라는 생각이 들었습니다

<br>

아래의 예시처럼 오는 JSON body가 있다고 가정을 해봅시다.
```JSON
{
    "my_name": "name",
    "my_phone": "010-1234-5678"
}
```
<br>
위 형식에 맞춰서 아래처럼 변수를 선언해줄 수 있습니다.

<br>

``` java
// 변수 생성 규칙을 같게 해도 되고
@Data
public class JsonExample {
    private String my_name;
    private String my_phone;
}
// @JsonProperty 형식으로 해도 됩니다
@Data
public class JsonExample {
    @JsonProperty("my_name") private String myName;
    @JsonProperty("my_phone") private String myPhone;
}
// @JsonNaming을 사용해도 됩니다
@Data
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class JsonExample {
    private String myName;
    private String myPhone;
}
```

<br>

이 프로퍼티는 변수 일부에 적용할 수 있는 방법입니다. 위의 예시처럼 둘다 같은 네이밍 규칙으로 온다면 @JsonNaming으로 해주는게 더 편할 듯 하지만 ❗

<br>

간혹 이런 경우가 있습니다.

<br>

```JSON
{
    "myName": "name",
    "my_phone": "010-1234-5678",
    "MyHome": "서울시 어딘가"
}
```

<br>

이럴 때는 @JsonProperty로 변수마다 네이밍을 맞춰서 선언해주는게 좋습니다.

<br>

``` java
@Data
public class JsonExample {
    @JsonProperty("myName") private String myName;
    @JsonProperty("my_phone") private String myPhone;
    @JsonProperty("MyHome") private String myHome;
}
```

<br>

그러면 내 코드에서 사용하는 변수 형식은 일정한 형태를 유지할 수 있습니다  👍

<br>

사실 단순히 어노테이션만 사용해서 하면 되는거 아님? 할 수 있지만 씨쟁이는 너무 신기했습니다;