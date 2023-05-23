#include <string>
#include <vector>
#include <map>

using namespace std;

int solution(vector<vector<string>> clothes) {
    int answer = 1;
    map<string, int> cloth_list;
    
    for(auto cloth : clothes)
    {
        auto it = cloth_list.find(cloth[1]);
        if(it != cloth_list.end())
            it->second++;
        else{
            cloth_list.insert(make_pair(cloth[1], 1));
        }
    }
    
    for(auto cloth : cloth_list)
    {
        answer *= (cloth.second + 1);
    }
    answer--;
   
    return answer;
}