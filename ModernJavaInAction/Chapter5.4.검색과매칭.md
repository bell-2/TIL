# 검색과 매칭

스트림은 특정 속성이 데이터 집합에 있는지 여부를 검색하는 데이터 처리에도 사용할 수 있다.

allMatch, anyMatch, noneMatch, findFirst, findAny 등의 유틸리티 메서드가 있음.

## 메서드 살펴보기

### anyMatch: Predicate가 적어도 한 요소와 일치하는지 확인하기

주어진 스트림에서 적어도 한 요소와 일치하는지 확인할 때 anyMatch 메서드를 쓸 수 있다.

anyMatch는 boolean을 반환하기 때문에, if 문으로 비교할 때 용이하다.

```java
    @Test
    @DisplayName("anyMatch로 적어도 한 요소와 일치하는지 확인해보기")
    void searchAndMatch() {
        if(menuList.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly");
        }
    }
```

flatMap 공부할 때 구닥다리 C언어로 바꿔보니 참 재밌더군요...? 

```java
#include <stdio.h>

struct Dish {
    int isVegetarian;
};

int anyMatch(struct Dish *menuList, int size) {
    for (int i = 0; i < size; i++) {
        if (menuList[i].isVegetarian) {
            return 1; // True, at least one element is vegetarian
        }
    }
    return 0; // False, no vegetarian element found
}

int main() {
    struct Dish menuList[] = {
        {1}, // Vegetarian
        {0}, // Non-vegetarian
        {1}, // Vegetarian
    };

    int menuSize = sizeof(menuList) / sizeof(menuList[0]);

    if (anyMatch(menuList, menuSize)) {
        printf("The menu is (somewhat) vegetarian friendly\n");
    }

    return 0;
}
```

### allMatch: Prediacte가 모든 요소와 일치하는지 검사하기

anyMatch와 다르게 스트림의 모든 요소가 주어진 Predicate와 일치하는지 검사한다.

```java
    @Test
    @DisplayName("allMatch로 모든 요소가 조건과 일치하는지 검사하기")
    void allMatchTest() {
        if(menuList.stream().allMatch(dish -> dish.getCalories() < 1000)) {
            System.out.println("All menu is healthy");
        }
    }
```

### noneMatch: 주어진 Predicate와 일치하는 요소가 없는지 확인하기

allMatch와 반대이다. 얘는 모든 요소가 주어진 Predicate와 일치하는지 검사한다.

위의 allMatch를 noneMatch로 바꿔보면 다음과 같이 쓸 수 있다.

```java
    @Test
    @DisplayName("noneMatch로 조건과 일치하는 요소가 없는지 검사하기")
    void noneMatchTest() {
        if(menuList.stream().noneMatch(dish -> dish.getCalories() >= 1000)) {
            System.out.println("All menu is healthy");
        }
    }
```

### short-circuit 기법 -> 따로 빼기

anyMatch, allMatch, noneMatch 메서드는 스트림 쇼트서킷 기법 연산을 활용한다. (&&, ||) 

쇼트서킷(short-circuit)은 주로 논리 연산에서 발생하는 개념이다.

특정 조건이 충족되면 나머지 조건을 평가하지 않고 전체 표현식의 결과를 결정하는 것을 말한다.

이는 프로그래밍 언어의 논리 연산자에서 자주 발생합니다.

가장 흔한 쇼트서킷 기법은 논리 AND(`&&`)와 OR(`||`) 연산자에서 나타납니다.

1. **쇼트서킷 AND (`&&`):** 논리 AND에서는 첫 번째 조건이 거짓인 경우 나머지 조건을 평가하지 않습니다. 왜냐하면 하나라도 거짓이면 전체 표현식의 결과는 거짓이기 때문입니다.

    ```java
    if (condition1 && condition2) {
        // code
    }
    ```

    만약 `condition1`이 거짓이면 `condition2`는 평가되지 않습니다.

2. **쇼트서킷 OR (`||`):** 논리 OR에서는 첫 번째 조건이 참인 경우 나머지 조건을 평가하지 않습니다. 왜냐하면 하나라도 참이면 전체 표현식의 결과는 참이기 때문입니다.

    ```java
    if (condition1 || condition2) {
        // code
    }
    ```

    만약 `condition1`이 참이면 `condition2`는 평가되지 않습니다.

이러한 쇼트서킷 기법은 프로그램의 성능을 향상시킬 수 있습니다. 첫 번째 조건만으로 결과를 결정할 수 있는 경우 불필요한 추가 평가를 하지 않기 때문이잖슴~

그래서 `allMatch`, `anyMatch`, `noneMatch` 메서드들도 내부적으로 쇼트서킷(short-circuit) 기법을 활용하여 성능을 최적화한다.

1. **`allMatch`:** 모든 요소가 주어진 조건을 만족하는지 확인합니다. 만약 어떤 요소라도 조건을 만족하지 않으면 뒤의 요소들을 평가하지 않고 즉시 `false`를 반환합니다.

    ```java
    boolean allMatch = stream.allMatch(element -> element > 0);
    ```

2. **`anyMatch`:** 적어도 하나의 요소가 주어진 조건을 만족하는지 확인합니다. 만약 어떤 요소라도 조건을 만족하면 뒤의 요소들을 평가하지 않고 즉시 `true`를 반환합니다.

    ```java
    boolean anyMatch = stream.anyMatch(element -> element > 0);
    ```

3. **`noneMatch`:** 모든 요소가 주어진 조건을 만족하지 않는지 확인합니다. 만약 어떤 요소라도 조건을 만족하면 뒤의 요소들을 평가하지 않고 즉시 `false`를 반환합니다.

    ```java
    boolean noneMatch = stream.noneMatch(element -> element < 0);
    ```

`allMatch`는 논리 AND와 유사하게 동작하며, `anyMatch`는 논리 OR와 유사하게 동작합니다. `noneMatch`는 조건을 부정하여 `allMatch`와 비슷한 역할을 합니다.

또한, 이 메서드들을 사용할 때는 주어진 Predicate 조건에 따라 모든 요소를 확인하지 않고도 즉시 결과를 반환하기 때문에, 쇼트서킷 기법이 적용되어 효율적으로 동작하게 되는거잖슴~

### findAny: 현재 스트림에서 임의의 요소를 반환하기

findAny 메서드를 다른 스트림 연산(filter 같은)과 연결해서 사용할 수 있다.

```java
    @Test
    @DisplayName("findAny로 아무 요소나 반환하기")
    void findAnyTest() {
        Optional<Dish> optionalDish = menuList.stream()
                .filter(Dish::isVegetarian)
                .findAny();
    }
```

#### Optional<T> (java.util.Optional)

옵셔널은 값의 존재나 부재 여부를 표현하는 컨테이너 클래스이다.

자바 8 라이브러리 설계자들은 null에 대해서도 많은 고민을 했었나보다.

null은 쉽게 에러를 일으킬 수 있기 때문에, Optional<T>라는 것이 만들어졌다.

### findFirst: 첫 번째 요소 찾기

연속되는 데이터로 만든 스트림에서 논리적인 아이템 순서가 정해져 있을 때, 첫 번째 요소만 필요할 때가 있잖슴?

그럴 때는 findFirst를 사용하면 된다.