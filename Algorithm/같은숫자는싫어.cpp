#include <vector>
#include <iostream>

using namespace std;

vector<int> solution(vector<int> arr) 
{
    vector<int> answer;
    
    int pre = -1;
    for(auto num : arr)
    {
        if( pre != num)
            answer.push_back(num);
        pre = num;
    }
    return answer;
}