# 스트림 활용

명시적 반복 대신 filter와 collect 연산을 지원하는 스트림 API를 이용해서 데이터 컬렉션 반복을 내부적으로 처리할 수 있다.

```java
    @Test
    void streamSample() {
        List<String> nameList = new ArrayList<>();
        nameList.add("럭스");
        nameList.add("룰루");
        nameList.add("애쉬");
        nameList.add("트린다미어");

        // 외부 반복
        List<String> longNameList = new ArrayList<>();
        for (String s : nameList) {
            if(s.length() > 5) {
                longNameList.add(s);
            }
        }
        // 내부 반복
        List<String> longNameStreamList = nameList.stream()
                .filter(s -> s.length() >5)
                .collect(Collectors.toList());
    }
```

스트림 API가 데이터를 어떻게 처리할지 관리해주기 때문에 데이터를 편하게 처리할 수 있다.

다양한 최적화가 이루어지고, 병렬로 실행할지 여부도 결정해준다.

순차적인 반복을 단일 스레드로 구현하는 외부 반복은 이를 할 수 없으셈.

## 필터링

필터링은 스트림의 요소를 선택하는 방법인 Predicate 필터링 방법과 고유 요소만 필터링 하는 방법이 있다.

### Predicate를 이용한 필터링

```java
	// Dish 클래스의 Predicate 메서드
     	public boolean isVegetarian() {
            return vegetarian;
        }

   @Test
    @DisplayName("Predicate filtering")
    void predicateFilter() {
        List<Dish> vegetarianMenu = menuList.stream()
                .filter(Dish::isVegetarian) // Predicate
                .collect(Collectors.toList());
    }
```

스트림 인터페이스에서 지원하는 filter 메서드에서 Predicate 함수를 인수로 받아서 Predicate 조건에 맞는 데이터 요소를 포함하는 스트림을 반환한다.

### 고유 요소 필터링

스트림은 고유 요소로 이루어진 스트림을 반환하는 distinct 메서드도 지원한다.

스트림에서 객체들의 고유성을 판단할 때, 해당 객체들의 hashCode와 equals 메서드가 호출한다.


#### hashCode와 equals 메서드
Java 프로그래밍 언어에서 객체의 고유성을 결정하는 데 `hashCode` 메서드와 `equals` 메서드가 사용된다는 것을 나타냅니다.

1. **hashCode 메서드:**
   - `hashCode` 메서드는 객체의 해시 코드를 반환합니다. 해시 코드는 객체를 나타내는 정수 값이며, 일반적으로 서로 다른 객체에 대해 서로 다른 해시 코드가 반환됩니다. 그러나 두 개의 다른 객체가 같은 해시 코드를 가질 수도 있습니다. 따라서 해시 코드는 객체를 식별하는 데 완전히 신뢰할 수 있는 수단은 아닙니다.

2. **equals 메서드:**
   - `equals` 메서드는 두 객체가 동등한지 여부를 확인합니다. 즉, 두 객체가 서로 같은 내용을 가지고 있는지를 판단하는 데 사용됩니다. 이 메서드를 적절히 재정의하여 객체 간의 내용적인 비교를 수행할 수 있도록 해야 합니다.

일반적으로 이 두 메서드는 함께 사용되며, 객체가 같은지 여부를 결정하는 데 사용된다.

예를 들어, Java의 컬렉션 프레임워크에서 객체를 저장하거나 검색할 때, `hashCode`와 `equals`가 제대로 구현되지 않으면 원하는 동작을 얻을 수 없을 수 있습니다.

자바에서는 `hashCode`와 `equals`를 적절히 오버라이딩하여 객체의 동등성(equality)을 정의하는 것이 중요합니다. 만약 이를 제대로 처리하지 않으면 객체의 동등성을 검사하는데 문제가 발생할 수 있습니다.


아무튼.. distinct 단어 뜻처럼, 데이터의 요소에서 중복을 필터링한다.