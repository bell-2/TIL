# 스트림과 컬렉션

컬렉션과 스트림의 공통점은 연속된 요소 형식의 값을 저장하는 자료구조의 인터페이스를 제공한다는 점이다.

연속된다라는 건 순차적으로 값에 접근한다는 것이다.

그러면 차이점은 무엇일까나

## 스트림과 컬렉션의 차이점 1: 데이터를 언제 계산하는가?

넷플릭스나 유튜브를 볼 때 스트리밍 서비스를 본다고 얘기를 한다.

그 때 그 스트리밍이 스트림이다.

영상 데이터를 처음부터 재생할 때, 뒤에는 아직 다 내려 받지는 않았지만 미리 받은 앞쪽 프레임부터 재생이 가능하다.

만약 3시간짜리 영화를 처음부터 다운로드하고 봤다면 스트리밍 보다는 시간이 조금 더 걸릴 것이다.

#### 컬렉션

현재 자료구조가 포함하는 모든 값을 메모리에 저장하는 자료구조.

컬렉션에 추가되기 전에 모든 요소가 계산되어야 한다.

컬렉션에 요소를 추가/삭제가 가능하다.



#### 스트림

요청할 때만 요소를 계산하는 고정된 자료구조.

스트림에 요소를 추가/삭제 할 수 없다.

스트림에서는 생산자와 소비자라는 용어를 사용하는데, 사용자가 데이터를 요청할 때만 그 때 데이터 값을 계산한다. 이러한 점을 보고 게으르다고 말을 한다. 

컬렉션은 모든 걸 계산해서 생산자 중심으로 창고를 다 채워 놓는 스타일이다.

소비자는 몇개 밖에 안 필요한데도 말이다. 부지런하다. 하지만 소비자는 하염없이 창고의 내용을 봐야한다.


## 딱 한번만 탐색할 수 있다
반복자처럼 스트림도 한번만 탐색이 가능하다. 데이터 요소는 한 번 탐색되면 소비된다.

```java
	@Test
    void streamOneChance() {
        List<String> idols = Arrays.asList("아이유", "에스파", "르세라핌");
        Stream<String> stream = idols.stream();
        stream.forEach(System.out::println);
        stream.forEach(System.out::println); // Error! stream has already been operated upon or closed 
    }

```

테스트 예제를 작성해보면, 마지막 줄에서 스트림을 한번더 출력하려고 하니, `stream has already been operated upon or closed` 라는 에러가 발생한 것을 볼 수 있다.

## 스트림과 컬렉션의 차이점 2: 데이터를 어떻게 반복 처리하는가?

컬렉션 인터페이스는 사용자가 for-each 같은 걸 사용해서 직접 데이터 요소를 반복해야한다.

이를 외부 반복이라고 함.

내부 반복은, 스트림 라이브러리처럼 반복을 알아서 다 하고 어딘가에 저장도 해주는 것이다.

```java
    @Test
    void iteratorTest() {
        List<String> idols = Arrays.asList("아이유", "에스파", "르세라핌");

        // 1. 외부 반복
        List<String> photo = new ArrayList<>();
        Iterator<String> iterator = idols.iterator();
        while (iterator.hasNext()) {
            photo.add(iterator.next());
        }

        // 2. 내부 반복
        List<String> photo2 = idols.stream()
                .collect(Collectors.toList());
    }
```

내부 반복을 하면 뭐가 좋으냐.

작업을 투명하게 병렬로 처리하여 더 최적화해서 다양한 순서로 처리가 가능하다.

데이터 표현과 하드웨어를 활용한 병렬성 구현을 자동으로 선택한다.

병렬성을 알아서 해주기 때문에, 락이나 동기화 문제 등을 관리하지 않아도 된다.

책 예제가 인상 깊었는데,

아이에게 바닥에 떨어진 장난감을 하나 하나 말해주면서, 그 다음엔 이거 정리해. 이거 정리해. 하는거보다는

아이가 한번에 정리해서 장난감 상자를 주면 얼마나 좋을까에 대한 예제가 있었다.

그러면 두 손으로 한번에 정리를 하기도 하고 시간도 단축되니까, 내부 반복이 더 좋다는..


## 스트림 연산

스트림의 내부 반복을 신경을 안써도 된다는 것은, 데이터 연산 리스트가 미리 정의 되어야한다는 것을 의미한다.

아이가 장난감을 정리할 수 있는 알고리즘이랄까?

그 연산을 람다 표현식으로 인수로 받고, 동작 파라미터화도 활용이 가능하다.


```java
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

아까 봤던 예제를 한 번 더 가져왔다.

이를 두 부분으로 나눌 수 있다.

1. filter, map, limit은 서로 연결되어 파이프라인을 형성
2. collect로 파이프라인을 실행 후 스트림을 닫는다.

filter, map, limit 같은 연산을 중간 연산이라고 부른다.
collect 같이 스트림을 닫는 연산을 최종 연산이라고 부른다.

### 중간 연산 (intermediate operation)
filter, sorted 같은 중간 연산이 파이프라인으로 연결된다는 것은, 각각 다른 스트림을 반환하고 이 각각의 스트림이 연결되어 파이프라인을 형성한다는 것을 의미한다.

단말 연산이 파이프라인화 되는 것이다.

단말 연산은 스트림 파이프라인에 실행되기 전에는 아무것도 안하다가(게으름), 중간 연산을 합친 다음에 최종 연산으로 한번에 처리한다.

```java
	@Test
    @DisplayName("요리 클래스를 스트림으로 정렬하기")
    void shortCircuit() {
        List<String> lowCalDishesName = menuList.stream()
                .filter(dish -> {
                    System.out.println("filtering:"+dish.getName());
                    return dish.getCalories() < 1000;
                })
                .map( dish -> {
                    System.out.println("mapping:"+dish.getName());
                    return dish.getName();
                })
                .limit(2)
                .collect(Collectors.toList());
        System.out.println("results:"+ lowCalDishesName);
    }

```

위 예제를 실행시켜보면 아래와 같이 출력된다.

```
filtering:닭가슴살
mapping:닭가슴살
filtering:스프
mapping:스프
results:[닭가슴살, 스프]
```
이 처럼, filter와 map은 서로 다른 연산이지만, 파이프라인화가 되어 한 과정으로 병합된 것을 알 수 있다. (루프 퓨전)

#### 중간 연산 종류
1. filter
2. map
3. limit

4. sorted

5. distinct

### 최종 연산 (terminal operation)
최종 연산은 스트림 파이프라인에서 결과를 도출하는 역할이다.

List, Integer, void 같은 스트림 이외의 결과를 반환하여 사용한다.


```java
    @Test
    void streamExample() {
        menuList.stream().forEach(System.out::println);
    }
```

forEach는 System.out::println 람다를 적용해서 void로 반환하는 최종 연산으로 쓰였다.

#### 최종 연산 종류
1. forEach
- 스트림의 각 요소를 소비하면서 람다를 적용하기 위해 사용

2. count
- 스트림의 요소 개수를 반환함

3. collect
- 스트림으로 List, Map, 정수형식의 컬렉션을 생성하기 위해 사용함


### 스트림 이용 과정
- 질의를 수행할 데이터 소스 (컬렉션)
- 스트림 파이프라인을 구성할 중간 연산 연결 (filter, map 등)
- 스트림 파이프라인을 실행하고 결과를 만들 최종 연산 (collect 등)


## 정리
- 스트림은 데이터 소스에서 추출된 연속 요소로 데이터 처리 연산을 지원
- 내부 반복을 지원
- 중간 연산과 최종 연산으로 구성
- 중간 연산: 파이프라인을 구성하지만 결과를 생성할 수는 없는 연산
- 최종 연산: 스트림 결과를 반환하는 연산
