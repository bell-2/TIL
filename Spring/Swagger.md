# Swagger UI

스웨거(Swagger)는 개발자가 REST API를 제공하는 서비스를 제공할 때 설계, 빌드, 문서화 등을 제공하는 오픈 소스 소프트웨어 프레임워크 입니다 <br>
(OAS(Open Api Specification)를 위한 오픈 소스 프레임워크)
<br>

문서화를 자동으로 해주는게 아주 편리하고, 테스트 케이스도 제공한다고 하네요 <br>
문서 만들기 싫은 개발자로서 정말 괜찮다고 생각합니다

그리고 문서를 만들다보면, 휴먼에러가 많기 마련인데 코드를 그대로 문서로 만들어주니 좋더군요

<br>

---

### 라이브러리
Spring에서 사용하는 Swagger는 라이브버리가 2개가 있습니다. (Springdoc, Springfox) 
<br>
저는 Springfox를 사용해서 적용해봤답니다
Springfox를 좀 더 많이 사용한다고 해요

- build.gradle
```yaml
dependencies {
		implementation group: 'io.springfox', name: 'springfox-swagger2', version: '2.6.1'
        implementation group: 'io.springfox', name: 'springfox-swagger-ui', version: '2.6.1'
}
```
- servlet 설정 파일.xml
```xml
<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-boot-starter</artifactId>
			<version>3.0.0</version>
		</dependency>

		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>3.0.0</version>
		</dependency>
        ...
```
<br>

### Config 추가
Swagger에게 설정 파일임을 알려주고, Docket이라는 Bean을 생성해서 설정을 해줘야 합니다

```java
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.swagger.test")).paths(PathSelectors.any())
                .build();
    }
}
```

- select(): ApiSelectorBuilder 클래스의 인스턴스를 반환
- apis(): api 설정 추가

<br>

### 접속 URL
설정을 다 해주고 빌드가 되었다면, 아래의 주소고 접속해서 확인해볼 수 있습니당
버전마다 주소는 조금 다를 수 있습니다

```java
http://localhost:8080/swagger-ui.html
```
<br>

### Annotaion으로 설명 추가
Annotation들을 사용해서 명세를 추가할 수가 있는데요, 해당 API의 정의나 예시, 파라미터의 각 타입 등을 설정할 수 있습니다.

1. @Api
- Controller의 이름을 정해줄 수 있습니다

```java
@Api(tags = "로그인/인증")
@Controller
public class AuthController {
```


2. @ApiOperation
- Controller에서 처리하는 api의 설명을 추가할 수 있습니다
  
```java
	@RequestMapping(value="/api/v1/login", method=RequestMethod.POST)
	@ApiOperation(httpMethod = "POST",
			value = "로그인",
			notes = "로그인 시 생성되는 authKey를 페이지 상단의 'Authorize' 버튼을 클릭 후 입력하면 전역으로 사용할 수 있습니다.")
	public @ResponseBody ResponseVo login ( Request req, Response res, @RequestBody LoginVo lv)
```
3. @ApiParam
- Api의 파라미터에 설명을 추가해줄 수 있습니다.
  
```java
	@RequestMapping(value="/api/v1/login", method=RequestMethod.POST)
	@ApiOperation(httpMethod = "POST",
			value = "로그인",
			notes = "로그인 시 생성되는 authKey를 페이지 상단의 'Authorize' 버튼을 클릭 후 입력하면 전역으로 사용할 수 있습니다.")
	public @ResponseBody ResponseVo login ( Request req, Response res, 
    @ApiParam(value="로그인시 필요한 사용자 ID/PWD")  @RequestBody LoginVO lv)
```

4.  @ApiModelProperty
- 요청/응답의 파라미터가 여러 개가 되는 경우, 클래쓰로 정의할 때 각 프로퍼티의 설명을 추가할 수 있습니다.
  
```java
@Data
public class LoginVo {
	@ApiModelProperty(example = "bell2")
	private String 		userId;
	@ApiModelProperty(example = "1234")
	private String 		userPwd;
}
```

### 인증 키, 헤더 등 추가하기
로그인을 하고 나면, 각 서버에서 인증키 같은 정보를 줄텐데요, 로그인 후 요청을 보낼 때마다 이 인증키를 담아서 같이 보내서 신뢰할 수 있는 클라이언트임을 알려주기도 합니다.

```java
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {
    @Bean
    public Docket api() {

        Parameter parameterBuilder1 = new ParameterBuilder().name("My-Authorize").description("Access Token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();

        List global = new ArrayList();
        global.add(parameterBuilder1);

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.swagger.test"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Arrays.asList(apiKey()));
    }

    private ApiKey apiKey() {
        return new ApiKey("My-Authorize", "Authorization", "header");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
```


---

개발환경
- Swagger 3.0.0, Gradle