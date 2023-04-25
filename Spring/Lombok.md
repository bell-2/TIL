# Lombok

lombok은 메서드 작성 시에 코드의 양을 줄여주는 Java의 라이브러리 입니다 
바로 예시를 들어보겠습니다.

아래는 lombok을 사용하지 않은 코드의 예시랍니다

```java
public class LombokClass
{
    private String name;
    private Long id;

    public LombokClass() {}
    public LombokClass(String name, Long id)
    {
        this.name = name;
        this.id = id;
    }

    public String getName()
    {
        return name;
    }
    public Long getId()
    {
        return id;
    }
    // 생략
}
```

그렇다면 lombok을 사용한다면??
```java
@Getter
@Setter
@ToString
@NoArgsConstuctor
@AllArgsConstructor
public class LombokClass
{
    private String name;
    private Long id;
}
```

보이나요 이 편리함이...! 
5줄 쓰는 것도 줄일 수가 있답니다.

```java
@Data
public class LombokClass
{
    private String name;
    private Long id;
}
```

@Data 어노테이션만 써주면 된답니다.
<br>
