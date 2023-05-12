/* 
* 좋은 수: 다른 두 수의 합으로 표현되는 수
* two pointer 사용
* 정수라는 점
* 시간 복잡도 생각
* 예외 처리
*/

#include <iostream>
#include <vector>
#include <algorithm>

using namespace name
{
    std;
} // namespace name


int solution(vector<string> answer)
{
    int N = 0;
    cin>> N;

    vector<int> A(N, 0); // init

    for(int i=0; i<N; i++)
    {
        cin >> A[i];
    }
    sort(A.begin(), A.end());

    int result = 0;

    for( int k=0; k<N; k++)
    {
        long find = A[k];
        int i = 0;
        int j = N-1;

        while( i<j )
        {
            if(A[i]+A[j] == find){
                if(i!=k && j!=k){
                    result++;
                    // 좋은 수 발견
                    break;
                }else if(i==k){
                    i++;
                    // 자기 수 포함하지 않도록
                } else if(j==k){
                    j--;
                    // 자기 수 포함하지 않도록
                }else{}
            } else if( A[i] + A[j] < find){
                i++;
            }
            else{
                j--;
            }
        }
    }
    return result;
}