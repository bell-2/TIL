# 4.2 스트림 시작하기

자바 8 컬렉션에 추가된 스트림을 반환하는 stream()

## 스트림 정의
데이터 처리 연산을 지원하도록 소스에서 추출된 연속된 요소

1. 연속된 요소?
특정 요소 형식으로 이뤄진 연속된 값 집합의 인터페이스 제공.

- 컬렉션: 데이터를 주제로, 시간과 공간의 복잡성과 관련된 연산 시 주로 사용한다. 

- 스트림: 계산을 주제로, 계산식에 주로 사용된다. (filter, sorted, map 등)

2. 소스
컬렉션, 배열, I/O 데이터 제공 소스로부터 데이터를 소비한다.

정렬된 소스로 스트림을 만들면 같은 데이터 순서를 유지한다.

예를 들어 리스트로 스트림을 만들면 요소의 순서는 리스트의 순서와 같다.

3. 데이터 처리 연산
함수형 프로그래밍이나 데이터베이스와 비슷한 연산을 지원한다.

순차적/병렬로 스트림 연산을 실행할 수 있다.

## 스트림 주요 특징

1. Pipelining
데이터베이스에 질의를 하는 것 처럼, 스트림 연산을 연결해서 커다란 파이프 형태를 구성하기 위해 스트림 자신을 반환한다.

laziness, short-circuiting 같은 최적화를 얻을 수 있다 함.

2. 내부 반복
- 컬렉션: 반복자를 이용해서 명시적인 반복
- 스트림 내부 반복을 지원

## 스트림 예제

```java
	List<Dish> menuList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        menuList.add(new Dish(100, "닭가슴살", false, Type.MEAT));
        menuList.add(new Dish(1500, "치킨", false, Type.MEAT));
        menuList.add(new Dish(500, "스프", false, Type.OTHER));
        menuList.add(new Dish(50, "야채볶음", true, Type.OTHER));
    }
//
	@Test
    @DisplayName("요리 클래스를 스트림으로 정렬하기")
    void sortStreamExample() {
        List<String> lowCalDishesName = menuList.stream()// menuList에서 스트림을 얻어옴
                .filter(dish -> dish.getCalories() < 1000)// 파이프라인 연산을 만듦. 1000칼로리보다 적은 요리를 필터링 한다.
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)// 요리 이름을 가져온다
                .limit(2)
                .collect(Collectors.toList()); // 스트림 결과를 다른 리스트로 저장한다는 뜻이잖슴~
    }
```
1. 데이터 소스
위에서, 스트림의 정의에서 '소스'라는 용어가 나왔는데, 이는 데이터 소스를 의미한다.

예제에서의 데이터 소스는 menuList이다.

데이터 소스는 연속된 요소를 스트림에게 제공한다. 

2. 데이터 처리 연산
스트림에 filter, map, limit, collect 같은 데이터 처리 연산을 적용할 수 있다.

1) filter
람다를 인수로 받아, 스트림에서 특정 요소를 필터링한다. 예를 들어 1000칼로리보다 적은 데이터만 선택하고, 나머지는 제외시켰다.

2) map
람다를 이용해서 다른 요소로 바꾸거나 정보를 가져온다. 예를 들어 메뉴의 이름을 가져오는 식.

3) limit
정해진 개수 이상의 요소가 스트림에 저장 안되도록 스트림 크기를 줄였다.

4) collect
다른 형식으로 변환한다. 예를 들어 리스트로 반환하는 식.

filter, map, limit 연산은 서로 파이프라인을 형성 할 수 있도록 스트림을 반환하지만, collect는 파이프라인을 처리해서 다른 형태릐 결과로 반환한다.

collect를 호출해야 출력 결과가 있다.

limit로 요소를 제외 시켜도, 스트림 출력 결과에 영향은 없다.


스트림 라이브러리를 사용하면, 필터링/추출/축소 기능을 직접 구현하지 않고도 파이프라인을 더 최적화 할 수 있는 유연성을 가질 수 있다.
