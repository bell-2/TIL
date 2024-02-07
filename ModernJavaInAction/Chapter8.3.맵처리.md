# 맵 처리

자바 8에서는 Map 인터페이스에 몇가지 디폴트 메서드가 추가되어서, 기본적인 구현을 인터페이스에서 제공을 한다.

디폴트 메서드를 사용하면 개발자가 직접 구현하지 않아도 된다

## forEach 메서드

Map.Entry<K,V> 반복자를 이용하여 맵의 항목 집합을 반복할 수 있다

 ```
    @Test
    void forEachTest() {
        Map<String, Integer> ageFriends = new HashMap<>();
        ageFriends.put("짱구", 5);
        ageFriends.put("코난", 9);

        for(Map.Entry<String, Integer> entry : ageFriends.entrySet()) {
            String friend = entry.getKey();
            Integer age = entry.getValue();
            System.out.println(friend + "," + age);
        }
    }
 ```

이를, 자바 8부터 지원하는 forEach 메서드를 사용하면 좀더 간단해진다 

BiConsumer 인터페이스를 인수로 받는다

```
ageFriends.forEach((friend, age) -> System.out.println(friend + "," + age));
```

## 정렬 메서드

- Entry.comparingByValue
- Entry.comparingByKey

위 두 개의 유틸리티를 사용하면 맵의 항목을 값/키를 기준으로 정렬이 가능하다

```
    @Test
    void sortExample() {
        Map<String, String> jobFriends = new HashMap<>();
        jobFriends.put("짱구", "빌런");
        jobFriends.put("코난", "탐정");
        jobFriends.put("김씨", "부자");

        jobFriends.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey()) // 친구들 이름을 정렬하여 스트림 요소를 처리한다
                .forEachOrdered(System.out::println);
    }
```

## getOrDefault 메서드

Map에서 찾으려는 키가 존재하지 않는다면 null 처리를 따로 해줘야한다

잘못 처리하면 NullPointerException이 발생할 수도 있다

첫 번째 인수로 키, 두번째 인수로 기본값을 받아서 맵에 키가 존재하지 않으면 두번째 인수로 받은 기본값을 반환한다

SQL에서 IFNULL()과 비슷하다.

```
jobFriends.getOrDefault("없지롱", "없는 친구");
```

만약 키는 있는데 값은 없다면?

```
Map<String, String> jobFriends = new HashMap<>();
jobFriends.put("NULL씨", null);

jobFriends.getOrDefault("NULL씨", "NULL 입니다"); // 결과는 null로 나옴
```

getOrDefault는 키의 값이 존재하는지를 확인 후, 기본 값을 반환해준다

키는 있지만 값은 없다면 (null 이라면) null을 반환해준다