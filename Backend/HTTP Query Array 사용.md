# HTTP Query Parameter에서 Arrary List 사용

API 설계 중에, GET을 사용하여 서버의 정보를 조회하고 싶은데, 범위가 필요한 값들이 있다.
<br>

Query Paramter에 Array도 가능하고 Json Type도 쓸 수 있다는 것을 모른다면 POST 메소드를 사용하여 Body 메지시에 범위 값을 사용하는 걸로 설계할 수 있다.

예를 들면,

```http
POST /bell/v2/list HTTP/1.1
...
{
    "list":[0, 5]
}
```

<br>
물론 이 방식도 할 수 있다. 근데 Resource URI를 더 고민해야하는;; 상황에 놓이게 된다
<br>
GET에도 body 메시지를 물론 보낼 수 있다.
근데 GET 메소드에 대해서는 body를 처리하지 않는 서버가 있을 수 있기 때문에 통상적인? 표현은 아닌 듯 하다.

<br>
그래서 아래와 같이 보내면 간단히 해결된다. 

```http
GET /bell/v2/list?type=0&type=5&name=test HTTP/1.1
...
```
<br>
Spring에서는 Query에 들어온 Array에 대해서도 알아서 처리를 해주기 때문에 서버 입장에서도 어려울게 없다.