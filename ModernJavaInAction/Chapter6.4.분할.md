# 분할 함수 (Partitioning Function)

분할은 분할 함수라 불리는 Predicate를 분류 함수로 사용하는 그룹화 기능이다.

맵의 키 형식이 Boolean 이다.

그룹화 맵은 참 또는 거짓의 두 개의 그룹으로 분류 된다.

`partitioningBy`는 Collectors 클래스의 정적 메서드 중 하나인데, 이는 스트림의 요소를 주어진 조건에 따라 두 그룹으로 나누어 맵에 저장하는 컬렉터이다.

```java
    @Test
    public void partionExample() {
        Map<Boolean, List<Dish>> partitionedMenu =
                Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println(partitionedMenu);
    }
```
```
{false=[pork, beef, chicken, prawns, salmon], true=[french fries, rice, season fruit, pizza]}
```

분류된 그룹에서, 키 값인 true 또는 false로 분류한 결과를 얻을 수도 있다.

```java
List<Dish> vegetarianDishes = partitionedMenu.get(true);
```

참 또는 거짓이라는 두 가지 요소의 스트림 리스트를 모두 유지할 수 있다는 장점이 있다.

```java
    public boolean isPrime(int candidate) { // 소수인지 비소수인지 판단하는 함수
        int candidateRoot = (int) Math.sqrt((double) candidate); // 제곱근 이하의 수로 효율적으로 찾기
        return IntStream.range(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0); // candidate를 정수로 나눌 수 없으면 참을 반환
    }

    public Map<Boolean, List<Integer>> partitionPrimes(int n) {
        // 2부터 n까지의 숫자를 포함하는 스트림을 생성하여, 소수와 비소수 분류하기
        return IntStream.rangeClosed(2, n).boxed()
                .collect(partitioningBy(candidate -> isPrime(candidate)));
    }

    @Test
    public void isPrimeTest() {
        System.out.println(partitionPrimes(10));
    }
```
```
{false=[10], true=[2, 3, 4, 5, 6, 7, 8, 9]}
```

