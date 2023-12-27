# 스트림 슬라이싱

## Predicate를 이용한 슬라이싱

자바 9에는 스트림의 요소를 효과적으로 선택할 수 있도록 하는 메서드인, takeWhile과 dropWhile이 있다고 한다.

### takeWhile

```java

    @Test
    @DisplayName("takeWhile")
    void takeWhileTest() {
        // 100 칼로리 이하 요리 선택하기 위해 filter() 사용
        List<Dish> filteredMenu = menuList.stream()
                .filter(dish -> dish.getCalories() < 100)
                .collect(Collectors.toList());
        
    }

```
만약 100 칼로리 이하인 요리를 선택하려면 위와 같이 filter 함수를 써서 할 수 있다.

filter 연산을 이용하면 전체 스트림을 반복하면서 각 요소에 Predicate를 적용하게 된다.

칼로리 순으로 이미 정렬이 되어있음. 이미 리스트가 칼로리 순으로 정렬되어있다면 똑같은 Predicate를 반복할 필요는 없는 것임.

만약 스트림에 백만개의 요소가 있다면 성능에 차이가 날 수 밖에 없음.

이를 해결하기 위한게 takeWhile이란 말씀.

```java

	.stream()
	.takeWhile(dish -> dish.getCaloried() < 100)

```

### dropWhile

그러면 만약 100 칼로리보다 큰 요리를 탐색하려면? dropWhile을 이용해서 할 수 있다.

```java
.stream()
.dropWhile(dish -> dish.getCalories() < 100)
```

takeWhile과 정반대의 작업을 수행함. Predicate가 처음으로 거짓이되는 지점까지 발견되는 요소들을 버려준다.

무한한 남은 요소를 가진 무한 스트림에서도 동작한다고 함.

## 스트림 축소

값을 필터링 했다면, 요소의 개수에도 제한을 걸 수 있다. limit(n) 메서드를 사용하면 된다.

스트림이 정렬되어있다면 최대 요소 n개를 반환해준다.

```java
	List<Dish> limit3Menu = menuList.stream()
                .filter(dish -> dish.getCalories() < 100)
                .limit(2)
                .collect(Collectors.toList());

```

## 스트림 요소 건너뛰기
limit도 그렇고 skip도 filter 같은 다른 연산과 상호 보완적인 연산을 수행해준다.

```java
	List<Dish> skip2Menu = menuList.stream()
                .filter(dish -> dish.getCalories() < 100)
                .skip(2)
                .collect(Collectors.toList());
```










