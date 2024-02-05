# 컬렉션 팩토리

자바 9에서는 컬렉션 객체를 만드는 방법이 몇 가지 있다

그 중 하나는 `Arrays.asList()` 이다.

```
List<String> friends = Arrays.asList("아이유", "뷔");
Set<String> friends = new HashSet<>(Arrays.asList("태연", "키"));
Set<String> friends = Stream.of("현무", "나래", "장우")
							.collect(Collectors.toSet());
```

고정 크기 리스트이기 때문에 새 요소를 추가하게 되면, 고정된 크기의 변환할 수 있는 배열로 구현되어 UnsupportedOperationException 예외가 발생한다 (갱신은 가능)


자바 9에서는 리스트/집합/맵을 쉽게 만들 수 있는 팩토리 메서드를 제공한다

## 리스트 팩토리

List.of 팩토리 메소드로 리스트를 만들 수 있다

```
List<String> friends = List.of("a", "b", "c");
friends.add("d"); // java.lang.UnsupportedOperationException 예외 발생!
```

컬렉션이 의도치 않게 변하는 것을 막기 위한 예외이다

### 오버로딩 vs 가변인수

```
static <E> List<E> of (E... elements)
```

자바 API에서는 가변 인수로 다중 요소를 받으면, 내부적으로 추가 배열을 할당해서 리스트로 감싼당

배열을 할당하면 초기화도 필요하고 가비지 컬렉션 비용도 들기 때문에, 고정된 요소를 사용하면 이런 비용이 없어진다. (최대 10개까지)

List.of로 열 개 이상 요소를 가진 리스트를 만들면 가변 인수를 이용하는 메서드가 사용된다.

작은 리스트에서만 사용하자


새로운 팩토리 메서드랑 스트림 API를 사용해서 리스트를 만드는 상황을 이해하자

팩토리 메서드 구현은 더 단순하다

데이터 처리 형식을 설정하거나 변환할 필요가 없다면 팩토리 메서드를 이용하자


## 집합 팩토리

```
Set<String> friends = Set.of("a", "b", "b"); // IllegalArgumentException 예외 발생!
```

중복된 요소를 집합에 넣으려고 하면 예외가 발생한다

집합은 고유의 요소만 포함할 수 있다

## 맵 팩토리

맵은 키와 값이 있기 때문에 리스트나 집합보다 복잡하다

두 가지 방법으로 바꿀 수 없는 맵을 초기화할 수 있다

1. Map.of 팩토리 메서드

```
Map<String, Integer> ageFriends = Map.of("코난", 9, "짱구", 5);
```

키 값을 번갈아서 만들 수 있다

10개 이하 요소일 때 사용한다

2. Map.ofEntires 팩토리 메서드

가변 인수로 구현된 팩토리 메서드다

키와 값을 감쌀 추가 객체 할당이 필요하다

```
Map<String, Integer> ageFriends = Map.ofEntries(entry("유리", 5), entry("맹구", 5));
```