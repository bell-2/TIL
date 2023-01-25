# Spring  Cron Expression 
크론 표현식은 Spring에서 특정 시간 마다 반복되는 작업을 수행하고 싶을 때 사용하는 표현식 입니다
스케줄러는 DB 작업이나 반복되는 작업 등등에서 많이 쓰이는데용,
Spring의 cron을 이용해서 시간 주기를 설정할 수가 있답니다.

<br>

## 🌱 servlet-context.xml 파일에 task namespace 추가하기

```xml
<task:scheduled-tasks>
    	<task:scheduled ref="className" method="excuteFunction" cron="0 0/1 * * * ?" />  
 </task:scheduled-tasks>
```
servlet-context.xml에 task namespace를 추가한 모습 입니다

<task:scheduled-tasks>로 시작해주고, ref에는 스케줄이 동작될 클래스를 작성해주면 됩니다.

-  ref에는 bean을 등록해주는 것이라서, @Component, @Controller 등의 Annotation으로 등록된 이름을 추가

method에는 실행될 메서드를 입력해주면 됩니다

<br>
<br>

## 🌱  cron 표현식 설정해주기
크론 표현식은 앞에서부터 [초-분-시-일-월-요일-년도] 로 구성이 되고,
년도는 생략 가능합니다

표현식은 공식 사이트에서 확인할 수 있습니당
https://spring.io/blog/2020/11/10/new-in-spring-5-3-improved-cron-expressions

예를 들어서 위 예제처럼 
``` xml 
 cron="0 0/1 * * * ?" /> 
 ```
 
 이렇게 정의 했다면, 1분에 한번씩 excuteFunction 함수가 실행 됩니다.
 
 만약 2023년에 12시 45분에 동작하도록 하고 싶다면?
 ``` xml 
 cron="0 45 12 * * * 2023" /> 
 ```
 이렇게 작성할 수 있습니다. 

 이걸 언제 작성하나;; 하시고 계시다면 자동으로 만들어주는 사이트도 있습니닥 🫠

 http://www.cronmaker.com/;jsessionid=node0nxisjbagfffd2bfs6g3qqv7h847086.node0?0


## 🌱 @Scheduled Annotation으로 설정하기
servlet-context.xml 말고 annotation을 붙여서도 실행할 수 있습니닥

```java
/*...*/
@Scheduled(cron="0 0/1 * * * ?")
public void excuteFunction()
{
    ...
}
/*...*/
```