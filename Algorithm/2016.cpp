#include <string>
#include <vector>

using namespace std;

string solution(int a, int b) {
   
    string answer = "";
    string weekday[] = {"THU","FRI","SAT" ,"SUN","MON","TUE","WED"};
    int month[] = {0,31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    // 1.몇 월인지
    // 2.몇 일인지
    int totalDay = 0;
    for(int i=0; i<a; i++){
        totalDay += month[i];
    }

    answer = weekday[(totalDay+b)%7];
    return answer;
}
