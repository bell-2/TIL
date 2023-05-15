#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

bool compare(string str1, string str2)
{
    return str1 + str2 > str2 + str1;
}

string solution(vector<int> numbers) {
    string answer = "";
    vector<string> array;
    for(auto num :numbers)
    {
        array.push_back(to_string(num));
    }
    sort(array.begin(), array.end(), compare);

    for(auto num :array)
    {
        answer += num;
    }
    if(array[0] == "0") return "0";
    
    return answer;
}