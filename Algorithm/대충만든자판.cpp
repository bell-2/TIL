#include <string>
#include <vector>
#include <iostream>
#include <map>
#include <algorithm>

using namespace std;
    
    /*
    * 문자열이 여러번 할당된 경우 (같은 자판/ 다른 자판)
    * 문자열이 비어있는 경우
    * 문자열을 작성할 수 없을 때는 -1
    * 최소 몇번
    */

vector<int> solution(vector<string> keymap, vector<string> targets) {
    vector<int> answer;
    
    map<char, int> key;
    for(int i=0; i<keymap.size(); i++)
    {
        for(int j=0; j<keymap[i].size(); j++)
        {
            if(key.find(keymap[i][j]) == key.end()){
                 key[keymap[i][j]] = j+1;
                 cout << keymap[i][j] << endl;
            }else{
                key[keymap[i][j]] = min(key[keymap[i][j]], j+1);
            }
        }
    }
    
    for(auto target : targets)
    {
        int cnt = 0;
        bool chk = true;
        // ABCD 
        for(int i=0; i<target.size(); i++)
        {
            if(key.find(target[i]) == key.end()){
                chk = false;
            }
            else{
                //cout << target[i] << endl;
                cnt += key[target[i]];
            }
        }
        if(!chk) cnt = -1;
        answer.push_back(cnt);
    }
    
    return answer;
}