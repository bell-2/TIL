#include <string>
#include <vector>
#include <iostream>

using namespace std;
struct lu{
    int x = 0;
    int y = 0;
    void set(int _x, int _y){
        x = _x;
        y = _y;
    }
    int getX() {return x;}
    int getY() {return y;}
};

lu S;
lu E;

// start point init
void findStartLu(vector<string> wallpaper)
{
    for(int i=0; i<wallpaper.size(); i++)
    {
        for(int j=0; j<wallpaper[i].size(); j++)
        {
            int lux=0, luy=0;
            if( wallpaper[i][j] == '#'){
                S.set(i, j);
                E.set(i+1, j+1);
                return;
            }
        }
    }
}

void setLu(int x, int y)
{
    int lux=S.getX(), luy=S.getY();
    int rdx=E.getX(), rdy=E.getY();
    
    lux = min(S.getX(),x);
    luy = min(S.getY(),y);
    
    rdx = max(E.getX(),x+1);
    rdy = max(E.getY(),y+1);
    
    S.set(lux, luy);
    E.set(rdx, rdy);
}

vector<int> solution(vector<string> wallpaper)
{
    vector<int> answer;
    findStartLu(wallpaper);
        
    for(int i=0; i<wallpaper.size(); i++)
    {
        for(int j=0; j<wallpaper[i].size(); j++)
        {
            int lux=0, luy=0;
            if( wallpaper[i][j] == '#'){
               setLu(i, j);
            }
        }
    }
    answer.push_back(S.x);
    answer.push_back(S.y);
    answer.push_back(E.x);
    answer.push_back(E.y);

    return answer;
}