# Mapping

스트림에서는 특정 데이터를 선택하는 작업을 수행하는 연산인 map과 flatMap을 지원한다.

## 스트림의 각 요소에 함수 적용하기

map 메서드는 함수를 인수로 받는다. 인수로 제공된 함수는 각 요소에 적용되고 적용한 결과가 새로운 요소로 맵핑된다.

기존의 값을 새로운 버전으로 만드는 개념이다.

```java
	@Test
    @DisplayName("Mapping")
    void mappingTest() {
        List<String> dishNameList = menuList.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
    }

```

요리의 이름을 가져오는 getName을 사용하여 스트림을 생성하였기 때문에, 출력 스트림은 Stream<String> 형식이다.

```java
        List<Integer> dishNameLengthList = dishNameList.stream()
                .map(String::length)
                .collect(Collectors.toList());
```

요리의 이름을 가져오는 리스트도 쉽게 만들 수 있다.

요리의 이름을 인수로 받아서, 문자열의 길이를 반환하는 String::length를 map 메서드에 전달하면 해결할 수 있다.

```java
List<Integer> dishNameLengthList = menuList.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());
```

요리 리스트에서 한번에 요리의 이름의 길이 리스트를 가져올 수도 있다.

이를 Chaining이라고 한다.

## 스트림 평면화

리스트로 얻어온 문자열을 쪼개서 고유 문자로 이루어진 리스트가 반환되도록 할 수 있다.

```java
    @Test
    @DisplayName("중복된 문자가 제거된 문자열 리스트")
    void splitWordsTest() {
        List<String> words = Arrays.asList("apple", "peach", "banana");

        List<String> wordList = words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());

        System.out.println(wordList);
    } 
```

Stream<String> 형식을 갖는 스트림을 기대했지만.. 아쉽게도 아래와 같은 에러가 발생한다.

```
타입 변수의 인스턴스가 없으므로 String[]이(가) String을(를) 준수합니다 추론 변수 T에 호환되지 않는 바운드가 있습니다. equality constraints: String lower bounds: String[]
```

위와 같이 하였을 경우에는 Stream<String[]> 형식으로 반환되는 것이다.

split이 배열을 반환하기 때문이다.

```java
@NotNull  
@Contract(pure = true)  
public String[] split(     @NotNull  String regex,
    int limit )
```

하지만 Stream<String>을 원한다구...! 이럴 때 사용하는 것이 flatMap 메서드이다.

만약 위의 예제를 Stream<String[]>로 변경하면 아래와 같이 결과가 출력된다. :sleepy: 

```java
[[Ljava.lang.String;@7fb95505, [Ljava.lang.String;@58be6e8, [Ljava.lang.String;@7331196b]
```

### map과 Arrays.stream

flatMap을 사용하기 전에, 배열 스트림 대신에 문자열 스트림을 봐보자.

```java
        /**
         String[] words = {"apple", "peach"};
         Stream<String> stream = Arrays.stream(words);
         */

        List<Stream<String>> wordList = words.stream()
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

```

Arrays::stream을 사용해서 문자열을 받아 스트림을 받을 수는 있지만, 스트림 리스트가 만들어졌기 때문에 원래 목표인 Stream<String>은 만들어지지 않았다.

각 단어를 개별 문자열로 이루어진 배열로 만든 다음에, 각 배열을 별도의 스트림으로 만들어야한다.

```java
        List<String> wordStrings = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
//[a, p, l, e, c, h, b, n]

```

이렇게 말이다. 

flatMap은 각 배열을 스트림이 아니라, 스트림의 콘텐츠로 맵핑해준다.

이름처럼 flat하게~ 평면화된 스트림을 반환해준다. 즉, flatMap 메서드는 스트림의 각 값을 다른 스트림으로 알아서 바꾸고, 모든 스트림을 하나의 스트림으로 연결까지 해주는 것이다... 


## 예제

flatMap 예제가 마음에 들어서 추가로 작성해본다.

만약에 두 개의 숫자 리스트가 있는데, 모든 숫자 쌍의 리스트를 구하는데 그 중 숫자 쌍의 합이 3으로 나누어 떨어지는 숫자 쌍을 구하고 싶다면?

이를 구닥다리 c언어로 만들면 이렇게 할 수 있다.

```c
#include <stdio.h>

int main() {
    int numberList1[] = {1, 2, 3};
    int numberList2[] = {3, 4};
    
    int pairs[6][2];
    int count = 0;

    for (int i = 0; i < sizeof(numberList1) / sizeof(numberList1[0]); i++) {
        for (int j = 0; j < sizeof(numberList2) / sizeof(numberList2[0]); j++) {
            if ((numberList1[i] + numberList2[j]) % 3 == 0) {
                pairs[count][0] = numberList1[i];
                pairs[count][1] = numberList2[j];
                count++;
            }
        }
    }

    // Displaying the pairs
    for (int i = 0; i < count; i++) {
        printf("[%d, %d]\n", pairs[i][0], pairs[i][1]);
    }

    return 0;
}
```

너무 싫은 이중 for문.. 가독성도 안 좋고 단순한 예제이지만 i와 j가 헷갈릴 수 밖에 없는 그런... (이름을 잘 정하면 되지만.. 그치만..)

이를 flatMap을 사용한 스트림을 사용해서 작성해보면 아래와 같다.

```java
    @Test
    @DisplayName("숫자 리스트의 모든 숫자의 쌍에서 합이 3으로 나누어지는 예제")
    void numberListTest() {
        List<Integer> numberList1 = Arrays.asList(1,2,3);
        List<Integer> numberList2 = Arrays.asList(3,4);

        List<int[]> pairs = numberList1.stream()
                .flatMap(i -> numberList2.stream()
                        .filter(j->(i+j) % 3 == 0 )
                        .map(j-> new int[] {i, j}))
                .collect(Collectors.toList());
    }

```

