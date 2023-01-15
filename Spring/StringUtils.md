# StringUtils

## 📕API 문서

StringUtils는 문자열을 검사할 때나 변환할 때 유용하게 쓰이는 클래스 입니다


### 1) Class
- java.lang.Object
- org.apache.commons.lang3.StringUtils
### 2) 특징
- NullPointException 발생 X
- ThreadSafe

## 🌱 String Handling
String 처리 클래스 답게 처리할 수 있는 단어들이 있는데용, 아래와 같습니다.

- null: 말 그대로 null
- empty: 빈 문자 ("')
- space: 스페이스 ('', char 형)
- whitespace: 여백, Character.isWhitespace(char를 참고)
- trim

## 🌱 Operations
이 때 아니면 언제 다 읽어보겠습니까? 😥

💡기본적으로 null-safe 동작을 합니다.

- IsEmpty/IsBlank
- Trim/Strip: 접두/접미에 있는 공백을 제거
- Equals/Compare: 두 개 문자열 비교
- startsWith : prefix 확인
- endsWith: suffix 확인
- IndexOf/LastIndexOf/Contains: 문자열 인덱스 확인
- IndexOfAny/LastIndexOfAny/IndexOfAnyBut/LastIndexOfAnyBut: 여러 문자열의 인덱스 확인
- ContainsOnly/ContainsNone/ContainsAny: 문자열에 특정 단어가 있는지 확인
- Substring/Left/Right/Mid: 부분 문자열 추출
- SubstringBefore/SubstringAfter/SubstringBetween: 검사할 문자열에 대해분리기호를 두고, 앞/뒤/사이 문자들을 추출
- Split/Join
- Remove/Delete
- Replace/Overlay
- Chomp/Chop(쩝쩝박사): 마지막에 개행문자(CRLF)가 있다면 제거해줌
- AppendIfMissing: 문자열 끝에 suffix 추가해줌 (없을 경우에)
- PrependIfMissing : 문자열 앞에 preffix 추가해줌 (없을 경우에)
- LeftPad/RightPad/Center/Repeat: 문자열 크기만큼 왼/오/가운데에 추가 단어를 생성해줌
- UpperCase/LowerCase/SwapCase/Capitalize/Uncapitalize
- CountMatches
- IsAlpha/IsNumeric/IsWhitespace/IsAsciiPrintable
- DefaultString: argument가 null이면 빈 문자열을 출력해줌
- Rotate : 문자열을 시계/반시계 방향으로 이동시켜줌
- Reverse/ReverseDelimited
- Abbreviate : 축약 기능
- Difference
- LevenshteinDistance: 문자열 A에서 문자열 B로 변경하는데 필요한 변경 횟수

## 🌱 Methods
API 문서 참고

https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/StringUtils.html