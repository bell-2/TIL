# ConcurrentMap Interface
`ConcurrentMap`은 인터페이스이며, `ConcurrentHashMap`은 이 인터페이스를 구현한 구체적인 클래스 중 하나 입니다.
`ConcurrentMap` 인터페이스는 Java 5(Java 1.5)부터 제공되며, 동시성을 지원하는 맵을 나타냅니다.

## ConcurrentMap의 등장 이유

HashTable과 HashMap은 각각 Java 1,2 에서 Collections Framework에 있는 API 입니다.
Map 인터페이스 구현이라는 점은 같지만, HashMap도 성능을 향상시키기 위해 계속 업데이트 되고 있습니다.

그러면 HashMap와 HashTable이 있는데!! ConcurrentMap이 그럼 왜 등장했는지 궁금하지 않으신가요. 아니면 말고.

### HashMap, HashTable의 차이점

![HashMap vs HashTable](/Image/hashMap.jpg)

#### HashMap
- Key, Value에 null이 허용됨
- 동기화 보장(Thread-Safe) 되지 않음 -> Single Thread 환경에서 사용

![HashMap vs HashTable](/Image/hashMap.jpg)

#### HashTable
- Key, Value에 null이 허용 안됨
- 동기화 보장(Thread-Safe)이 됨 -> Multi Thread 환경에서 사용

큰 차이점은 `동기화 보장(Thread-Safe)` 입니다. 

Thread-Safe는 여러 스레드에서 동시에 공유 자원에 접근하더라도 의도한 대로 동작하는 것을 말합니다. 
HashTable을 사용하면 스레드 안전성을 제공하여 여러 스레드에서 맵에 안전하게 접근하고 수정할 수 있는 것이잖슴~


그러면 ConcurrentHashMap은 무슨 차이가 있냐.

HashTable보다 성능이 더 좋다고 합니다. 왜 그런지는 아래에서 설명하겠음.

--- 

# ConcurrentHashMap

ConcurrentHashMap는 Java에서 제공하는 스레드 안전한 맵 구현체로, 여러 스레드에서 동시에 안전하게 맵을 읽고 쓸 수 있도록 설계되어 있습니다

## **기본 개념과 특징:**
`ConcurrentHashMap`은 Java의 컬렉션 프레임워크에서 제공하는 맵(`Map`)의 구현 중 하나로, 여러 스레드에서 안전하게 동작하도록 설계된 클래스입니다. 

1. **구조:**
   - `ConcurrentHashMap`은 해시 테이블을 기반으로 한 맵 구현체입니다.
   - 내부적으로는 세그먼트(segment)라는 부분적인 락을 사용하여 동시성을 지원합니다.

2. **세그먼트:**
   - `ConcurrentHashMap`은 여러 개의 세그먼트로 나누어져 있습니다.
   - 각 세그먼트는 독립적으로 락을 가지고 있어, 한 세그먼트에서의 작업은 다른 세그먼트에 영향을 미치지 않습니다.

3. **동시성 제어:**
   - 여러 스레드가 동시에 `ConcurrentHashMap`에 접근하더라도 안전하게 동작합니다.
   - 세그먼트 락을 사용하여 부분적으로 락을 걸어 성능을 향상시키면서 동시성을 보장합니다.

4. **성능:**
   - `ConcurrentHashMap`은 읽기 연산에서는 거의 락이 발생하지 않아 성능이 우수합니다.
   - 쓰기 연산에서도 세그먼트 락을 사용하여 여러 스레드 간에 충돌을 최소화하고 효율적으로 동작합니다.

## 동시성과 병렬성 차이

- **동시성(Concurrency):**
  - 여러 작업이 동시에 진행되는 것을 의미합니다.
  - 동시성은 주로 여러 스레드가 서로 다른 작업을 동시에 수행하면서 전체적으로는 효과적으로 일이 처리되는 것을 목표로 합니다.

- **병렬성(Parallelism):**
  - 여러 작업이 동시에 진행되어, 실제로 여러 코어나 프로세서가 각각의 작업을 동시에 처리하는 것을 의미합니다.
  - 병렬성은 주로 하나의 작업을 여러 부분으로 나누어 각각을 병렬로 처리함으로써 성능을 향상시키는 것을 목표로 합니다.

`ConcurrentHashMap`은 동시성을 지원하면서도 내부적으로 세그먼트를 사용하여 일부 부분에서는 병렬성을 향상시키는 구조를 가지고 있습니다.


---

출처

- https://itsromiljain.medium.com/curious-case-of-concurrenthashmap-90249632d335
- https://tecoble.techcourse.co.kr/post/2021-11-26-hashmap-hashtable-concurrenthashmap/