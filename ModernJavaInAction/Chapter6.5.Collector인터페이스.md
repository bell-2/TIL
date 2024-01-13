# Collector 인터페이스

```java
public interface Collector<T, A, R> {
    Supplier<A> supplier();
    BiConsumer<A, T> accumulator();
    BinaryOperator<A> combiner();
    Function<A, R> finisher();
    Set<Characteristics> characteristics();
}
```

`Collector` 인터페이스는 스트림 요소를 어떻게 수집할지 정의하는 인터페이스이다.

Collector 인터페이스는 스트림 요소를 어떻게 누적할지를 결정하는 메서드를 제공하고, 이를 통해 다양한 작업을 수행할 수 있다.

- T: 스트림의 요소 타입
- A: 누적자(accumulator)의 타입으로, 중간 결과를 저장하는 역할
- R: 최종 결과의 타입

`Collector` 인터페이스의 주요 메서드는 다음과 같다.

1. **supplier()**: 빈 누적자를 생성하는 메서드. 누적자는 중간 결과를 저장하는 컨테이너 역할

2. **accumulator()**: 누적자에 요소를 추가하는 메서드. 스트림의 각 요소가 이 메서드를 통해 누적자에 추가

3. **combiner()**: 두 누적자를 병합하는 메서드. 병렬 처리 시에 각각의 서브스트림에서 생성된 누적자를 하나로 합치는 역할

4. **finisher()**: 최종 결과를 생성하는 메서드. 누적자의 최종 상태를 최종 결과로 변환

5. **characteristics()**: 컬렉터의 특성을 반환하는 메서드. 컬렉터의 동작 방식을 설명하는 상수 집합을 반환

## characteristic 상수

`Characteristics`는 `Collector` 인터페이스에서 제공하는 특성(Characteristic)을 설명하는 열거형 상수입니다. 

각각의 특성은 `Collectors.collectingAndThen`, `Collectors.toList`, `Collectors.toSet` 등과 같은 메서드에 의해 반환되는 컬렉터의 동작 방식을 결정한다. 

아래와 같은 상수가 있다.

1. **CONCURRENT (`CONCURRENT`)**: 병렬 스트림에서 컬렉터를 안전하게 사용할 수 있도록 지원하는 특성. 다중 스레드에서 동시에 누적자를 갱신할 수 있음.

2. **UNORDERED (`UNORDERED`)**: 컬렉터가 요소의 순서에 의존하지 않고, 요소의 순서를 유지하지 않는 특성. 병렬 처리에서 이 특성을 가진 컬렉터를 사용하면 성능이 향상될 수 있음

3. **IDENTITY_FINISH (`IDENTITY_FINISH`)**: 누적자를 최종 결과로 변환할 때 추가적인 변환이 필요하지 않음을 나타내는 특성. 즉, 누적자의 타입과 최종 결과의 타입이 동일하다는 뜻.

4. **SEQUENTIAL (`SEQUENTIAL`)**: 스트림이 병렬로 처리되지 않을 것임을 나타내는 특성. 즉, 스트림이 `parallel()` 메서드를 통해 병렬로 전환되지 않는 한 컬렉터는 순차적으로 적용됨.

`CONCURRENT`와 `UNORDERED` 특성을 가진 컬렉터는 병렬로 안전하게 동작하면서도 요소의 순서에 의존하지 않는다.



