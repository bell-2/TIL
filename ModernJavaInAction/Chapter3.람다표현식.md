# 람다 표현식

람다표현식
익명클래스처럼 이름이 없는 함수. 메서드를 인수로 전달할 수 있다

람다 용어 자체는 미적분학 학계에서 유래됨.


## 1. 람다란 무엇인가?

람다 표현식: 메서드로 전달할 수 있는 익명 함수를 단순화한 것.<b>

### 람다 특징
- 익명: 메서드와 다르게 이름이 없음
- 함수: 특정 클래스에 종속되지 않음. 그래서 함수라고 불림. 하지만 메서드의 특징 또한 가짐.

cf) 메서드의 특징은? 파라미터 리스트, 바디, 반환 형식, 예외 리스트 포함.

- 전달: 람다 표현식을 메서드 인수로 전달하거나 변수로 저장 가능.
- 간결성: 익명 클래스처럼 중복되거나 클래스 생성을 위한 코드를 구현할 필요 없음. 판에 박힌 코드 ㅋㅋ



```

    @Test
    @DisplayName("Comparator 람다로 변환")
    void comparatorTest() {
        Comparator<Apple> appleComparator = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight()> o2.getWeight()?o1.getWeight():o2.getWeight();
            }
        };
        
        Comparator<Apple> lamdaComparator = (o1, o2) -> o1.getWeight()> o2.getWeight()?o1.getWeight():o2.getWeight();
    }
```



### 람다 너 뭔데 어떻게 쓰는건데.

람다 표현식 스타일 특징 1)

람다 표현식 구성: 파라미터, 화살표, 바디로 이뤄짐.

```
	(o1, o2) -> o1.getWeight()> o2.getWeight() ?o1.getWeight():o2.getWeight();
```


- (o1, o2) : 람다 파라미터. 파라미터 리스트. Comparator의 compare 메서드 파라미터
- -> : 화살표는 왼쪽좌는 파라미터 리스트, 오른좌는 바디를 구분함. 구분좌임.
- 오른쪽: 람다 바디. 반환 값에 해당하는 표현식.



return 구문이 사라져있다. 그렇다. 람다 표현식에서는 return을 사용하지 않아도 된다.


람다 표현식 스타일 특징 2)


```

    @Test
    @DisplayName("람다 표현식 예제")
    void lamdaExample() {
        Comparator<Integer> integerComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println("Lamda.compare. 여러 줄이 가능하다!");
                System.out.println("Lamda.compare. 여러 줄이 가능하다!");
                return 0;
            }
        };

        Comparator<Integer> integerComparator2 = (o1, o2) -> {
            System.out.println("Lamda.compare. 여러 줄이 가능하다!");
            System.out.println("Lamda.compare. 여러 줄이 가능하다!");
            return 0;
        };
    }

```

그렇다. 람다 표현식에서는 여러 줄 표현도 가능하다.


람다 표현식 스타일 특징 3)

``` java
(parameter) -> expression
(parameters) -> { statements; }
```

블록 스타일로 표현할 수 있다


cf) 흐름 제어문 return

```
(Integer i) -> return i; // 틀림!
(Integer i) -> { return i; } // 맞음!
```


### 2. 람다 사용법

람다 표현식은 함수형 인터페이스/함수 디스크립터로 사용할 수 있다


1) 함수형 인터페이스

```java
public interface Predicate<T> {
	boolean test (T t);
}
```

Predicate<T>는 오직 하나의 추상 메서드만 지정하는 함수형 인터페이스이다.

함수형 인터페이스?는 하나의 추상메서드를 지정하는 인터페이스다.
자바 API의 함수형 인터페이스는 Comparator, Runnale 등이 있다.

왜 함수 인터페이스 얘기가 나왔을까.

람다 표현식을 함수형 인터페이스의 인스턴스로 만들 수 있다.
정확히는 함수형 인터페이스를 구현한 클래스의 인스턴스.

익명 내부 클래스도 구현 가능하다.

```
public static void process(Runnable runnable) {
        runnable.run();
    }

    @Test
    @DisplayName("람다 표현식으로 함수형 인터페이스 전달")
    void functionalInterface() {
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Lamda.run");
            }
        };
        Runnable runnable2 = () -> System.out.println("Lamda.functionalInterface");

        process(runnable1);
        process(runnable2);
        process(()-> System.out.println("Lamda.functionalInterface 직접 람다로 전달"));
    }
```

Runnable은 함수형 인터페이스기 때문에, 람다 표현식으로 구현이 가능하다.


2) 함수 디스크립터

함수 디스크립터가 무엇이냐.

람다 표현식의 시그니처를 서술하는 메서드이다.

람다 표현식의 시그니처는 뭐냐.

함수형 인터페이스의 추상 메서드 시그니처이다.

함수형 인터페이스의 추상 메서드 시그니처를 알면 그 인터페이스를 람다 표현식으로 표현할 때 필요한 매개변수 타입 및 반환 타입을 이해할 수 있다능.

시그니처는 즉, 함수의 타입이다.

`() -> void` 표기는 파라미터 리스트가 없으며 void를 반환하는 함수를 의미함.

위에 있는 예제 중에서 Runnable이 이거잖슴~

`(Apple, Apple) -> int`: 두 개의 Apple를 인수로 받아 int를 반환하는 함수란 뜻이잖슴~


=> 람다 표현식은 변수에 할당하거나 함수형 인터페이스를 인수로 받는 메서드로 전달할 수 있음.

이쯤되면, 궁금해진다. 왜 함수형 인터페이스를 인수로 받는 메서드에만 람다 표현식이 사용 가능한지.

이유는 언어 설계자들이 복잡하지 않고 더 익숙한 방법을 사용할 수 있도록 한다고 함.


3. 람다 활용법: 실행 어라운드 패턴

실행 어라운드 패턴

-> 실제 자원을 처리하는 코드를 설정과 정리 두 과정이 둘러싼 형태를 말함

실행 어라운드 패턴을 활용한 예제를 봐보자.


```java
public String processFile() throws Exception {
        try(
            BufferedReader bufferedReader = new BufferedReader(new FileReader("data.txt"))) {
            return bufferedReader.readLine();
        }
    }

```

이를 함수형 인터페이스와 람다를 이용해서 전달해보자.


```java

@FunctionalInterface
    public interface BufferReaderProcessor {
        String process(BufferedReader bufferedReader) throws IOException;
    }

@Test
    @DisplayName("실행 어라운드 패턴")
    void excuteAroundPattern() throws FileNotFoundException {
        // processFile() 함수의 동작을 파라미터화
        //String result = processFile(new BufferedReader(new FileReader("example.txt")));

        // 함수형 인터페이스로 전달
        String result2 = processFile((BufferedReader br) -> {
            try {
                return br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        });
    }
```

함수형 인터페이스로 바꿔서 해봤다.

그러면 두 줄을 처리하는 코드도 작성 가능하다.

```java
        String twoLineResult = processFile((BufferedReader bufferReader) -> bufferReader.readLine() + bufferReader.readLine());


```

