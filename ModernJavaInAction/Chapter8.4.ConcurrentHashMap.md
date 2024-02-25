# 개선된 ConcurrentHashMap

ConcurrentHashMap 클래스는 스레드 간 동기화 문제를 해결하는 최신화된 HashMap이다.

내부 자료구조 특정 부분만 lock을 걸어 사용된다.

이 친구는 따로 정리하겠다.

## recude, search

ConcurrentHashMap은 스트림과 유사한 연산을 제공한다.

- forEach: 각 키, 값에 주어진 액션 수행
- reduce: 모든 키, 값을 제공된 reduce 함수를 이용해 결과로 합침
- search: 널이 아닌 값을 반환할 때까지 각 키, 값에 함수를 적용

네 가지 연산 형태를 지원한다.

1.키,값으로 연산
- forEach, reduce, search

2. 키로 연산
- forEachKey, reduceKeys, searchKeys

3. 값으로 연산
- forEachValue, reduceValues, searchValues

4. Map.Entry 객체로 연산
- forEachEntry, reduceEntries, searchEntries

주의할 점은, ConcurrentMap을 따로 lock을 걸지 않고 연산을 수행하기 때문에 연산이 되는 동안 맵의 객체, 값, 순서가 바뀔 수 있다.

그리고 병렬성 기준 값(threshold, 한계점)을 지정해야한다.

```
ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
long parallelismThreshold = 1;
Optional<Integer> maxVal = Optional.ofNullable(map.reduceValues(parallelismThreshold, Long::max));
```

기준 값을 1로 정하면, 공통 스레드 풀을 이용해 병렬성을 극대화하고,

Long.MAX_VALUE를 기준 값으로 정하면 한 개의 스레드로 연산을 실행한다.

int, long, double 등은 기본값에는 전용 each reduce 연산이 제공된다. (reduceValuesToInt, reduceKeysToLong..)

## mappingCount

맵의 매핑 개수를 반환하는 mappingCount 메서드도 제공한다.

기존 size 메서드 대신, int를 반환하는 mappingCount 메서드를 사용하는 것이 좋다.

왜냐하면, 맵핑의 개수가 int 범위를 넘어서는 상황에 대처가 가능하기 때문이다.


## keySet

ConcurrentHashMap을 집합 뷰로 반환하는 keySet 함수도 제공한다.

맵을 바꾸면 집합도 바뀌고, 집합을 바꾸면 맵도 영향을 받는다.

newKeySet으로 ConcurrentHashMap으로 유지되는 집합을 만들 수 있다.

## newKeySet

`ConcurrentHashMap` 클래스의 `newKeySet()` 메서드는 `ConcurrentHashMap`의 키 집합을 반환한다.

이 집합은 원본 `ConcurrentHashMap`의 변경 사항을 반영한다.

즉, `ConcurrentHashMap`에 새로운 요소가 추가되거나 제거되면 반환된 집합에도 반영됨..

이 메서드는 일반적으로 `ConcurrentHashMap`의 키를 반복하는 데 사용됩니다. 집합이 반환되므로 키에 대한 반복은 순서를 보장하지 않음.

```java
ConcurrentHashMap<KeyType, ValueType> map = new ConcurrentHashMap<>();
// map에 요소 추가

Set<KeyType> keySet = map.newKeySet();

// keySet을 이용한 반복
for (KeyType key : keySet) {
    // 각 키에 대한 작업 수행
}
```

ConcurrentHashMap에 새로운 요소가 추가되거나 제거될 때 반환된 집합에도 이러한 변경 사항이 반영되어서, 이는 멀티스레드 환경에서 안전하게 사용할 수 있다는 것을 보장한다.

따라서 newKeySet()으로 생성된 집합을 반복하거나 수정하는 동안에도 다른 스레드에서 동시에 ConcurrentHashMap에 요소를 추가하거나 제거해도 문제가 발생하지 않는다.

이는 ConcurrentHashMap의 내부 동기화 메커니즘이 이러한 동작을 보장하기 때문에 가능함..

이러한 동기화는 일반적으로 성능과 안정성 면에서 우수하며, 복잡한 멀티스레드 환경에서도 예기치 않은 동작을 방지하는 데 도움이 된당

## keySet vs newKeySet

`newKeySet()`이 반환하는 집합이 `ConcurrentHashMap`과 동기화된다.

즉, `newKeySet()`으로 생성된 집합은 원본 `ConcurrentHashMap`의 변경 사항을 실시간으로 반영되는 똑똑이임.

`keySet()`으로 생성된 집합은 동기화되지 않으므로 멀티스레드 환경에서 안전하게 사용하기 위해서는 별도의 동기화 처리를 해줘야함.

멀티스레드 환경에서 작업하는 경우에는 일반적으로 `newKeySet()` 사용하고, 단일 스레드 환경에서는 `keySet()`을 사용하는게 성능에 더 좋을 것 같다.