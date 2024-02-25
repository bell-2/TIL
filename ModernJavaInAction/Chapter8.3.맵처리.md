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


## 계산 패턴

맵에 키 존재 여부로 로직은 달라질 수 있다

아래 연산들로 키를 이용해서 얻은 결과를 캐시할 수 있다

- computeIfAbsent: 제공된 키의 값이 없거나 NULL이면, 새 값을 계산하고 맵에 추가
- computeIfPredent: 제공된 키가 존재하면 새 값 계산하고 맵에 추가
- compute: 제공된 키로 새 값을 계산하고 맵에 저장

정보를 캐시할 때 `computeIfAbsent` 활용이 가능하다

파일 집합의 각 행을 파싱해 SHA-256을 계산하는 예시이다

```
public static class hashTest {
        private MessageDigest messageDigest;

        private byte[] calculateDigest(String key) {
            // 키의 해시를 계산한다
            return messageDigest.digest(key.getBytes(StandardCharsets.UTF_8));
        }

        @Test
        public void hashTestBySha256() {
            Map<String, byte[]> dataToHash = new HashMap<>();
            try {
                messageDigest = MessageDigest.getInstance("SHA-256");
                List<String> lines = Arrays.asList("Dearest, darling, my universe",
                        "Love is all, love is all",
                        "Our love wins all, love wins all");
                lines.forEach(line ->
                        dataToHash.computeIfAbsent(line, this::calculateDigest));

                dataToHash.forEach((line, hash) ->
                        System.out.printf("%s -> %s%n", line,
                                new String(hash).chars().map(i -> i & 0xff).mapToObj(String::valueOf).collect(Collectors.joining(", ", "[", "]"))));

            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }
```

`computeIfAbsent` 함수는 맵에 키가 존재 하지 않으면 맵에 추가하고, 키가 존재하면 기존 값을 반환한다

```
  @Test
    public void computeIfAbsentExample() {
        Map<String, List<String>> singerAndSongs = new HashMap<>();

        singerAndSongs.computeIfAbsent("IU", singer -> new ArrayList<>())
                .add("Love is All");

        singerAndSongs.forEach((singer, songs) -> System.out.println(singer + songs));
    }
```

현재 키와 관련된 값이 맵에 존재하고 NULL이 아닐 때, 새 값을 계산한다

만약 값을 만드는 함수가 NULL을 반환하면 맵에서 맵핑을 제거한다

## 삭제 패턴

remove 함수를 사용해서 삭제 패턴 구현이 가능하다

```
singerAndSongs(key, value);
```

## 교체 패턴

아래 두개의 메서드가 맵에 추가 되었다

1. replaceAll
- BiFunction을 적용한 결과 값으로 각 항목의 값을 교체
- List의 replaceAll과 비슷한 동작을 수행함

2. Replace
- 키가 존재하면 맵의 값을 바꿈
- 키가 특정 값으로 맵핑 되었을 때만 값을 교체하는 오버로드 버전도 있음

```
Map<String, String> stringMap = new HashMap<>(); // 교체할 거니까 바꿀 수 있는 맵 사용!!
stringMap.put("key1", "key2");
stringMap.replaceAll( (key, val) -> val.toUpperCase()));
// key1=KEY2
```

replace 패턴은 한 개의 맵에만 적용이 가능하다. 두 개 이상은 merge 메서드를 사용해야 한다.

## 합침

`putAll` 함수를 사용해서 두 개의 맵을 합치는 것은 어렵지 않다.

```
Map<String, String> map1 = Map.ofEntries(entry("key1", "val1"), entry("key2", "val2"));
Map<String, String> map2 = Map.ofEntries(entry("key3", "val3"), entry("key4", "val4"));

Map<String, String> mergeMap = new HashMap<>(map1);
mergeMap.putAll(map2);
```

만약 중복된 키가 있다면 BiFunction을 인수로 받아서 해결할 수 있다.

```
Map<String, String> map1 = Map.ofEntries(entry("key1", "val1"), entry("key2", "val2"));
Map<String, String> map2 = Map.ofEntries(entry("key3", "val3"), entry("key1", "val4"));

Map<String, String> mergeMap = new HashMap<>(map1);
map2.forEach( (k, v) -> mergeMap.merge(k, v, (m1, m2) -> m1 + "&" + m2));

// 중복된 값은 이렇게 결과가 나올 것이다. key1=val1&val4
```

merge 메서드는 null값과 관련된 상황도 처리한다.

javadoc

지정된 키와 연관된 값이 없거나 값이 널이면, `merge`는 키를 null이 아닌 값과 연결한다.
아니면 `merge`는 연결된 값을 주어진 맵핑 함수의 결과 값으로 대치하거나 결과가 null이면 항목을 제거한다.

초기화 검사를 merge 함수로 구현할 수도 있다. 

```
Map<String, Long> map1 = new HashMap<>();
Long value = map1.get("key1");
if(value == null) {
	map1.put(...);
} else {
	map1.put(...);
}
```
위와 같이, 해당 값이 있는지 없는지 체크하는 등의 경우에 merge 함수로 바꾸면 아래와 같다.

```
map1.merge("key1", 1L, (key, count) -> count + 1L);
```

두 번째 인수인 1L는, 키와 연관된 기존 값이 합쳐질 널이 아닌 값 또는 값이 없거나 키에 널 값이 연관되어 있다면 이 값을 키와 연결할 때 사용된다.

키의 반환 값이 처음에는 널이니까 1로 사용되고, 그 다음부터는 값이 1로 초기화 되어있어서, BiFunction을 적용해 값이 증가된다.
