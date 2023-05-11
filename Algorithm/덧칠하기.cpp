#include <string>
#include <vector>
#include <iostream>

using namespace std;

int doPaint(int m, vector<int> section)
{
    int idx = 0;
    for(int i=0; i<section.size(); i++)
    {
        if(section[i] <= section[0] + m -1)
            idx = i;
        else{
            idx = i-1;
            break;
        }
    }
  
    return idx;
}

               // 벽길이 롤러길이
int solution(int n, int m, vector<int> section) {
    int answer = 0;
    vector<int> wall = section;

    while(!wall.empty())
    {
        int idx = doPaint(m, wall);
        for(int i=0; i<=idx; i++)
            wall.erase(wall.begin()+0);
        answer++;
    }

    return answer;
}

/**
 * 2안
*/
#include <string>
#include <vector>
#include <iostream>

using namespace std;

            // 벽길이 롤러길이
int solution(int n, int m, vector<int> section) {
    // 적어도 한 번 페인트칠 (1 ≤ m ≤ n ≤ 100,000)
    int answer = 1;
    
    // first value
    int pivot = section[0];
    for(auto one : section)
    {
        if(one < pivot+m)
        {
            // can paint (no count)
            continue;
        }
        else
        {
            pivot = one;
            answer++;
        }
    }

    return answer;
}