# StringUtils

๐API ๋ฌธ์


StringUtils๋ ๋ฌธ์์ด์ ๊ฒ์ฌํ  ๋๋ ๋ณํํ  ๋ ์ ์ฉํ๊ฒ ์ฐ์ด๋ ํด๋์ค ์๋๋ค

<br>

### 1) Class

   - java.lang.Object <br>
   - org.apache.commons.lang3.StringUtils

### 2) ํน์ง
- NullPointException ๋ฐ์ X
- ThreadSafe

<br>

---

## ๐ฑ String Handling

 String ์ฒ๋ฆฌ ํด๋์ค ๋ต๊ฒ ์ฒ๋ฆฌํ  ์ ์๋ ๋จ์ด๋ค์ด ์๋๋ฐ์ฉ, ์๋์ ๊ฐ์ต๋๋ค.

 - null: ๋ง ๊ทธ๋๋ก null 
 - empty: ๋น ๋ฌธ์ ("')
 - space: ์คํ์ด์ค ('', char ํ)
 - whitespace: ์ฌ๋ฐฑ, Character.isWhitespace(char๋ฅผ ์ฐธ๊ณ )
 - trim


<br>

## ๐ฑ Operations

์ด ๋ ์๋๋ฉด ์ธ์  ๋ค ์ฝ์ด๋ณด๊ฒ ์ต๋๊น? ๐ฅ

๐ก๊ธฐ๋ณธ์ ์ผ๋ก null-safe ๋์์ ํฉ๋๋ค.

- IsEmpty/IsBlank
- Trim/Strip: ์ ๋/์ ๋ฏธ์ ์๋ ๊ณต๋ฐฑ์ ์ ๊ฑฐ
- Equals/Compare: ๋ ๊ฐ ๋ฌธ์์ด ๋น๊ต 
- startsWith : prefix ํ์ธ
- endsWith: suffix ํ์ธ
- IndexOf/LastIndexOf/Contains: ๋ฌธ์์ด ์ธ๋ฑ์ค ํ์ธ
- IndexOfAny/LastIndexOfAny/IndexOfAnyBut/LastIndexOfAnyBut: ์ฌ๋ฌ ๋ฌธ์์ด์ ์ธ๋ฑ์ค ํ์ธ
- ContainsOnly/ContainsNone/ContainsAny: ๋ฌธ์์ด์ ํน์  ๋จ์ด๊ฐ ์๋์ง ํ์ธ
- Substring/Left/Right/Mid: ๋ถ๋ถ ๋ฌธ์์ด ์ถ์ถ
- SubstringBefore/SubstringAfter/SubstringBetween: ๊ฒ์ฌํ  ๋ฌธ์์ด์ ๋ํด ๋ถ๋ฆฌ๊ธฐํธ๋ฅผ ๋๊ณ , ์/๋ค/์ฌ์ด ๋ฌธ์๋ค์ ์ถ์ถ
- Split/Join
- Remove/Delete 
- Replace/Overlay
- Chomp/Chop(์ฉ์ฉ๋ฐ์ฌ): ๋ง์ง๋ง์ ๊ฐํ๋ฌธ์(CRLF)๊ฐ ์๋ค๋ฉด ์ ๊ฑฐํด์ค
- AppendIfMissing: ๋ฌธ์์ด ๋์ suffix ์ถ๊ฐํด์ค (์์ ๊ฒฝ์ฐ์)
- PrependIfMissing : ๋ฌธ์์ด ์์ preffix ์ถ๊ฐํด์ค (์์ ๊ฒฝ์ฐ์)
- LeftPad/RightPad/Center/Repeat: ๋ฌธ์์ด ํฌ๊ธฐ๋งํผ ์ผ/์ค/๊ฐ์ด๋ฐ์ ์ถ๊ฐ ๋จ์ด๋ฅผ ์์ฑํด์ค
- UpperCase/LowerCase/SwapCase/Capitalize/Uncapitalize
- CountMatches 
- IsAlpha/IsNumeric/IsWhitespace/IsAsciiPrintable
- DefaultString: argument๊ฐ null์ด๋ฉด ๋น ๋ฌธ์์ด์ ์ถ๋ ฅํด์ค
- Rotate : ๋ฌธ์์ด์ ์๊ณ/๋ฐ์๊ณ ๋ฐฉํฅ์ผ๋ก ์ด๋์์ผ์ค
- Reverse/ReverseDelimited
- Abbreviate : ์ถ์ฝ ๊ธฐ๋ฅ
- Difference 
- LevenshteinDistance: ๋ฌธ์์ด A์์ ๋ฌธ์์ด B๋ก ๋ณ๊ฒฝํ๋๋ฐ ํ์ํ ๋ณ๊ฒฝ ํ์


##  ๐ฑ Methods

API ๋ฌธ์ ์ฐธ๊ณ 

https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/StringUtils.html