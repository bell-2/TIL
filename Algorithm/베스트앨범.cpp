#include <string>
#include <vector>
#include <map>
#include <algorithm>
#include <iostream>
using namespace std;
struct Music
{
    int idx=0;
    string genre;  
    int play = 0;
    Music(int _idx, string _g, int _p):idx(_idx), genre(_g), play(_p){}
};
map<string, int> maxGenre;

bool desc(pair<string, int> &aa, pair<string, int> &bb)
{
    return aa.second > bb.second;
}

bool compare(Music a, Music b)
{
    return a.play > b.play;
}

void insert(string genre, int play)
{
    auto it = maxGenre.find(genre);
    if( it != maxGenre.end() ) 
        it->second += play;
    else
        maxGenre[genre] = play;
}

vector<int> solution(vector<string> genres, vector<int> plays) {
    vector<int> answer;
    vector<Music> music;

    for(int i=0; i<genres.size(); i++)
    {
        Music msc(i, genres[i], plays[i]);
        music.push_back(msc);
        insert(genres[i], plays[i]);
    }
    vector<pair<string, int>> vec(maxGenre.begin(), maxGenre.end());
    sort(vec.begin(), vec.end(), desc);
    sort(music.begin(), music.end(), compare);

    int cnt = 1;
    for(auto v : vec)
    {
        cnt = 1;

        for(int i=0; i<music.size(); i++)
        {
            if(v.first != music[i].genre)
                continue;
            answer.push_back(music[i].idx);

            if( cnt == 2 ) break;
            cnt++;
        }
    }

    return answer;
}
