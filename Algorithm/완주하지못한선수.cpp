#include <string>
#include <vector>
#include <algorithm>
#include <map>

using namespace std;

string solution(vector<string> participant, vector<string> completion) {
    string answer = "";
    map<string, int> list;
    for(auto pt : participant)
        list[pt] += 1;
    
    for(auto clt : completion)
    {
        list[clt] -= 1;
    }
    
    for(auto l : list)
    {
        if(l.second != 0)
            answer = l.first;
    }
    return answer;
}