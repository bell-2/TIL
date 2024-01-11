## 리듀싱

컬렉터(Stream.collect 메서드의 인수)로 스트림의 항목을 컬렉션으로 재구성 할 수 있다.

컬렉터를 사용하여 스트림의 모든 항목을 하나의 결과로 합칠 수 있다.

### counting() 팩토리 메서드

```java
    @Test
    void countingExample(){
        long countDishes = Dish.menu.stream().collect(Collectors.counting());
        long countDishesSimple = Dish.menu.stream().count();
    }
```

`count()` 메서드만 사용해서도 같은 값을 얻을 수 있다.

### 최댓값, 최소값 검색

```java
    @Test
    void maxByExample() {
        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = Dish.menu.stream().collect(Collectors.maxBy(dishComparator));
    }
```

`Collectors.maxBy` 메서드로 스트림의 최댓값을 계산할 수 있다. (최소값은 minBy)

- Comparator 인수: 스트림의 요소를 비교하는데 사용
- Optional<Dish>: 포함/포함되지 않을 수 있는 컨테이너 Optional (자바8)


## 요약

스트림에 있는 객체의 숫자 필드의 합계나 평균을 반환하는 리듀싱 기능을 요약 연산이라고 한다.

### summingInt 요약 팩토리 메서드

객체를 int로 맵핑하는 함수를 인수로 받아서, 객체를 int로 맵핑한 컬렉터를 반환한다.

collect 메서드로 전달되면 요약 작업이 수행된다.

```java
    @Test
    void summingIntExample() {
        int totalCalories = Dish.menu.stream().collect(summingInt(Dish::getCalories));
    }
```

스트림 객체를 탐색하면서, 초깃값에 설정되어있는 누적자에 값을 더해줌.

그 밖에도 summingLong, summingDouble 메서드가 있다.

### 평균 값 요약 메서드

averagingInt, averagingLong, averagingDouble 등

```java
    @Test
    void summingIntExample() {
        IntSummaryStatistics intSummaryStatistics = Dish.menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(intSummaryStatistics);
    }
```

```
IntSummaryStatistics{count=9, sum=4300, min=120, average=477.777778, max=800}
```

IntSummaryStatistics 클래스에 개수, 합계 등 모든 정보를 수집할 수도 있다.

### 문자열 연결

컬렉터에 joining 팩토리 메서드를 이용하면 스트림의 각 객체에 toString 메서드를 호출해서 추출한 모든 문자열을 하나의 문자열로 연결해서 반환해준다.

```java
    @Test
    void joiningExample() {
        String joinMenu = Dish.menu.stream().map(Dish::getName).collect(joining(","));
        System.out.println(joinMenu);
    }
```

```
pork,beef,chicken,french fries,rice,season fruit,pizza,prawns,salmon
```

### 범용 리듀싱 요약 연산

위에서 본 컬렉터는 reducing 팩토리 메서드로 정의할 수 있다. (Collectors.reducing)

하지만 가독성이나 프로그래밍적 편의성 때문에 팩토리 메서드 대신 컬렉터를 사용하기도 한다.

```java
    @Test
    void reducingExample() {
        int totalCalories = Dish.menu.stream().collect(reducing(0, Dish::getCalories, (i,j) -> i + j));
    }
```

인수
- 첫번째: 리듀싱 연산의 시작값 또는 인수가 없을 경우 반환 값.
- 두번째: 변환 함수
- 세번째: 같은 종류의 두 항목을 하나의 값으로 더하는 BinaryOperator. 위에서는 두 개의 int를 더한다.

한 개의 인수를 가진 reducing 으로 가장 칼로리가 높은 요리를 찾을 수도 있다.
```java
    @Test
    void reducingExample() {
        int totalCalories = Dish.menu.stream().collect(reducing(0, Dish::getCalories, (i,j) -> i + j));
        Optional<Dish> mostCaloriesDish = Dish.menu.stream().collect(reducing( (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
	}
```
```
Optional[pork]
```

만약 한 개의 인수를 가진 reducing에 시작 값이 없으면, 빈 스트림이 넘거졌을 때 시작값이 설정이 되지 않는다.

그래서 한 개의 인수를 가진 reducing은 Optional<> 객체를 반환한다.

### collect와 reduce 차이

- collect: 도출하려는 결과를 누적하는 컨테이너를 바꾸는 메서드
- reduce: 두 값을 하나로 도출하는 불변형 연산

용도에 맞게 사용하자.

reducing은 IntStream을 사용해서 자동 언박싱 연산을 수행하거나 Integer를 int로 변환하는 과정을 피할 수 있어서 성능이 좋은 경우가 있다.

