## 스트림 (Streams)

컬렉션(collections)로 데이터를 그룹화하고 처리할 수 있다. 컬렉션은 모든 자바 어플리케이션에서 처리하고, 대부분의 프로그래밍 작업에 사용되기 때문에 중요하다.하지만 아직 완벽한 컬렉션 연산을 지원하려면 부족하다.

## 1.스트림이란 무엇인가

스트림도 자바 8 API에 새로 추가된 기능이다.
데이터를 처리하는 코드를 질의로 표현하여 선언형으로 컬렉션 데이터를 처리할 수 있다.

스트림을 사용하면 멀티스레드 코드를 구현하지 않아도 데이터를 병렬로 처리할 수 있다.

```java
    @Test
    @DisplayName("요리 클래스 정렬하기")
    void sortExample() {
        List<Dish> menuList = new ArrayList<>();
        menuList.add(new Dish(100,"닭가슴살"));
        menuList.add(new Dish(1500, "치킨"));
        menuList.add(new Dish(500, "스프"));

        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish lowCaloricDish : menuList) {
            if(lowCaloricDish.getCalories() < 1000) {
                lowCaloricDishes.add(lowCaloricDish);
            }
        }

        Collections.sort(lowCaloricDishes, Comparator.comparingInt(Dish::getCalories));

        List<String> lowCalDishesName = new ArrayList<>();
        for (Dish lowCaloricDish : lowCaloricDishes) {
            lowCalDishesName.add(lowCaloricDish.getName());
        }
    }
```

위 예제에서는 `lowCaloricDishes` 라는 가비지 변수를 사용해서 컨테이너 역할만 하도록 사용하였다.

Dish 클래스 리스트에서 1000 칼로리 이하의 요리만 골라서, 칼로리로 정렬을 하여 이름을 얻고 싶을 뿐인데, 가비지 변수로 인해 비효율적으로 보인다.

스트림으로 바꾸게 되면 요렇게 할 수 있다.

```java
    @Test
    @DisplayName("요리 클래스를 스트림으로 정렬하기")
    void sortStreamExample() {
        List<String> lowCalDishesName = menuList.stream()
                .filter(dish -> dish.getCalories() < 1000)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }

```

한 줄만 바꾸기

```java
@Test
    @DisplayName("요리 클래스를 parallel 스트림으로 정렬하기")
    void parallelStreamExample() {
        List<String> lowCalDishesName = menuList.parallelStream()
                .filter(dish -> dish.getCalories() < 1000)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }
```

stream()를 parallelStream()로 바꾸면 멀티코어 아키텍쳐에서 병렬로 실행할 수 있다.

??

말을 풀어서 설명하자면, 

`stream()`와 `parallelStream()`는 Java의 스트림 API에서 제공하는 두 가지 메소드인데, 이들은 컬렉션(예: 리스트, 세트, 맵 등)에 대해 병렬 처리를 수행하는 데 사용된다.


1. **`stream()` 메소드:**
   - `stream()` 메소드는 요소를 순차적으로 처리하는 스트림을 생성
   - 순차 스트림은 단일 스레드에서 작업을 수행하며, 각 요소는 이전 요소의 처리가 끝날 때까지 기다림

   ```java
   List<Integer> myList = Arrays.asList(1, 2, 3, 4, 5);

   // 순차적으로 처리
   myList.stream().forEach(System.out::println);
   ```

2. **`parallelStream()` 메소드:**
   - `parallelStream()` 메소드는 여러 스레드에서 동시에 요소를 처리하는 병렬 스트림을 생성
   - 병렬 스트림은 멀티코어 아키텍처에서 동시에 여러 작업을 수행하여 성능을 향상시킬 수 있음

   ```java
   List<Integer> myList = Arrays.asList(1, 2, 3, 4, 5);

   // 병렬로 처리
   myList.parallelStream().forEach(System.out::println);
   ```

따라서 "stream()를 parallelStream()으로 바꾸면 멀티코어 아키텍처에서 병렬로 실행할 수 있다"는 말은, 순차적인 처리에서 병렬로 처리로 전환하면 멀티코어 시스템에서 동시에 여러 작업을 수행하여 성능을 향상시킬 수 있다는 것을 의미한다.
(병렬처리가 무조건 성능이 좋은 것을 의미하는 것은 아님)

cf) parallelStream()을 호출해서 좋아지는 점은 7장에 나온다고 한다. 

### parallelStream() 호출해서 좋은 점
1. 선언형으로 코드를 구현 가능하다.
 - if 문처럼 제어 블록을 사용하지 않고 '1000 보다 낮은 칼로리의 요리만 선택' 이라는 동작만 수행할 수 있게 할 수 있다.
2. 여러 빌딩 블록 연산을 연결해서 복자한 데이터 처리 파이프라인을 만들 수 있다.

`filter, sorted, map, collect` 연산은 고수준 빌딩 블록(high-level building block)으로 이루어져 있어서 특정 스레딩 모델에 상관 없이 투명하게 사용 가능하다. 단일 스레드에서도 사용 가능하지만 멀티 스레딩에 더 활용하는게 좋다.

그리고, 데이터 처리 과정이 병렬화된다는건!! 스레드와 락을 따로 구현할 필요가 없다... 너무 좋은데?

### 스트림 API 특징
- 선언형: 더 간결하고 가독성이 좋다
- 조립할 수 있다
- 유연하다
- 병렬화가 가능하여 성능이 좋아진다

