# 계산 패턴

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
