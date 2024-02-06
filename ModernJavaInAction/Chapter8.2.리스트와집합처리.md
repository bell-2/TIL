# 리스트와 집합 처리

자바 8에는 List, Set 인터페이스에 추가된 메서드가 있다

- removeIf: Predicate를 만족하는 요소를 제거. 
- replaceAll: 리스트에서 이용할 수 있는 기능. UnaryOperator 함수를 이용해 요소를 바꿈
- sort: List 인터페이스에서 제공하는 기능. 리스트를 정렬함

얘네들은 호출한 컬렉션 자체가 바뀐다

스트림은 새로운 결과를 만들지만, 얘네들은 기존 컬렉션을 바꾼다

컬렉션을 바꾸는 것은 복잡하기 때문에 추가 됐다

## removeIf 메서드

```
	@Test
    void exceptionTest() {
        try {
            List<Integer> transList = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                transList.add(i);
            }

            for (Integer trans : transList) {
                if (trans > 30) {
                    transList.remove(trans);
                }
            }
            fail("예외 발생 하지 않음");
        } catch (ConcurrentModificationException e) {
            System.out.println(e);
        }
    }
```

이 코드에는 문제가 있다

아련하게 떠오르는 추억.. 실제로 신입 사원 때 C++로 된 서버를 개발하다가 Map을 조회하면서 삭제를 해서 Core가 생겼던 적이 있었다

잘못된 메모리에 접근하였기 때문이었다

위 코드에서는 두 개의 개별 객체가 컬렉션을 관리한다

1. Iterator 객체, next(), hasNext()로 반복문 소스 질의
2. Collection 객체 자체, remove()로 요소를 삭제

문제는 두 Iterator 객체는 동기화되지 않는 각각의 개별 객체라는 것이다

해결 방법

```
    @Test
    void otherIteratorTest() {
        try {
            List<Integer> transList = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                transList.add(i);
            }

            for(Iterator<Integer> iterator = transList.iterator(); iterator.hasNext();) {
                Integer trans = iterator.next();
                if(trans > 30) {
                    iterator.remove();
                }
            }
        } catch (ConcurrentModificationException e) {
            throw new RuntimeException();
        }
    }
```

Iterator 객체를 명시적으로 사용하고 그 객체의 remove를 호출하면 해결할 수 있다

이를 자바 8의 `removeIf()`를 사용해서 단순하고 에러 없이 변경할 수 있다

```
    @Test
    void removeIfTest() {
        try {
            List<Integer> transList = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                transList.add(i);
            }
            transList.removeIf(trans -> isThirtyBigNumber(trans));
        } catch (ConcurrentModificationException e) {
            throw new RuntimeException();
        }
    }
```

Predicate 함수를 인수로 넘겨야한다

## replaceAll 메서드

List 인터페이스 replaceAll 메서드로는 값을 바꿀 수 있다

```
    @Test
    void replaceAllTest() {
        List<String> stringList = new ArrayList<>();
        stringList.add("apple");
        stringList.add("banana");
        stringList.add("grape");

        stringList.stream().map(str -> Character.toUpperCase(str.charAt(0)) +
                str.substring(1))
                        .collect(Collectors.toList())
                                .forEach(System.out::println);
    }
```

근데 이 예제는 새 문자열을 만드는 것이다

기존 문자열을 바꾸려면 ListIterator 객체를 이용해야한다

```
    @Test
    void replaceAllTest() {
        List<String> stringList = new ArrayList<>();
        stringList.add("apple");
        stringList.add("banana");
        stringList.add("grape");

        for(ListIterator<String> iterator = stringList.listIterator(); iterator.hasNext();) {
            String str = iterator.next();
            iterator.set(Character.toUpperCase(str.charAt(0)) + str.substring(1));
        }

        System.out.println(stringList);
    }

```

간단하게 바꾸기 위해서는 자바 8의 replaceAll을 사용하자

```
    @Test
    void replaceAllTest() {
        List<String> stringList = new ArrayList<>();
        stringList.add("apple");
        stringList.add("banana");
        stringList.add("grape");

        stringList.replaceAll(str -> Character.toUpperCase(str.charAt(0)) + str.substring(1));

        System.out.println(stringList);
    }
```
