# **Boost로 시간 값 사용해보기**


개발을 하다보면, 로그라던가 HTTP 요청을 받았을 때라던가 현재 시간을 정보를 남길 때가 종종 있습니다.

저는 C언어에서는 아래와 같이 현재 시간을 남기고 있습니다

```c
long get_current_msec(void)
{
    long            cur_msec = 0;
    struct timeval  tv_time;

    gettimeofday(&tv_time, 0);
    cur_msec = (long)(tv_time.tv_sec*1000) + (long)(tv_time.tv_usec/1000);

    return cur_msec;
}
```
Boost에서는 시간을 가져오는 라이브러리가 역시 존재합니다

(물론 위 코드를 사용해서 C++ 코드에 적용해도 괜찮습니다 😊 )     
<br>
Boost의 Posix Time 소개글에는 아래와 같이 설명 되어있습니다

<br>

>The class boost::posix_time::ptime is the primary interface for time point manipulation. In general, the ptime class is immutable once constructed although it does allow assignment. <br>Class ptime is dependent on gregorian::date for the interface to the date portion of a time point. <br>Other techniques for creating times include time iterators.


<br>
Boost의 ptime 클래스는 기본적으로 시점 조작을 위한 기본 인터페이스이니까 잘 사용하란 내용 😣

 
<br>
Header는 이렇게 적어주면 됩니다.

```C++
#include <boost/date_time.hpp> 
// posix_time만 사용하겠다면
#include "boost/date_time/posix_time/posix_time.hpp"
```
<br>

생성자는 이렇게 사용할 수 있답니다
```C++
// 1) 기본 생성자
ptime timeEx; 
// 2) date랑 offset 설정 가능
ptime timeEx2(date(2022, Nov, 10),hours(1)); 
// 3) Copy constructor도 지원
ptime timeEx(timeEx2); 
// 4) infinities, not-a-date-time, max_date_time, and min_date_time로 특별한 시간도 대입 가능
time infinTime(pos_infin); 
```
<br>

그리고 문자열로된 string 값을 time 값으로 변환해주거나 ("2022-11-29" → 2022-11-29), iso 형식의 값을 time 값으로 변환해주는 api도 제공을 한다고 합니당
<br>
<br>

## **Boost로 현재 시간 가져오기**
그러면 현재 시간 가져오는 예제를 작성해보겠습니다

```C++
struct timeInfo
{
    string YYYY;
    string MM;
    string DD;
    string HH;
    string mm;
    string ss;
};

timeInfo getBoostTimeLocal()
{
    timeInfo time;

    try
    {
        boost::posix_time::ptime timeLocal = 
            boost::posix_time::second_clock::local_time();
            // timeLocal에 현재 시간을 가져와서 string으로 바꿔서 차곡차곡 넣어줍니다
        time.YYYY = to_string(timeLocal.date().year());
        time.MM = to_string(timeLocal.date().month().as_number());
        time.DD = to_string(timeLocal.date().day());
        time.HH = to_string(timeLocal.time_of_day().hours());
        time.mm = to_string(timeLocal.time_of_day().minutes());
        time.ss = to_string(timeLocal.time_of_day().seconds());

        return time;
    }
    catch(std::exception const& e)
    {
        return time;
    }
    return time;
}
```
<br>
timeInfo라는 구조체에 time 정보를 YYYY/MM/DD/HH/mm/ss 별로 담아주고 있습니다. <br>
그러면 필요한 시간 형식에 맞게 사용할 수 있습니다 🙂
<br>
<br>

----
## 🌍 참고 자료
[Boost Posix Time](https://www.boost.org/doc/libs/1_55_0/doc/html/date_time/posix_time.html)