# SLF4J

Simple Logging Facade for Java

로깅 프레임 워크에 대한 인터페이스 역할을 하는 라이브러리

🎈라이브러리로 되어있어서, 로깅 라이브러리가 변경 되어도 코드 수정을 하지 않아도 된다

## 사용 시 필요한 것들
- application.yml 설정
- Maven나 Gradle에 Lombok 추가

```java
@Slf4j
@Controller
public class BellController {

	@Autowired
	Config config;

    @RequestMappling(valud="/", method=POST)
    public @ResponseBody void bellRes(...)
    {
        log.info("bellResponse 입니다");
    }
```