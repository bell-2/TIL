# 👀 Kill

kill이라는 함수로 Process에 signal을 보낼 수 있습니다.
<br>

기능 때문에 kill이 아니라 send_signal로 해야하는거 아니냐라는 얘기도 있습니다.

<br>
command로는 아래와 같이 쓸 수 있습니다.

```bash
$ kill -9 {pid}
```
🙂 참고로 pid는 process id를 줄여쓰는 단어랍니다

<br>

C쟁이는 Process를 다루다보면 kill() 함수로 쓰기 마련입니다.
<br>
오늘 kill에 눈물 찔끔 흘리고 정리하러 왔습니다.

<br>

```c
#include <signal.h>
int kill(pid_t pid, int signal)
```
<br>
사용방법은 위와 같습니다.
<br>
signal을 보낸다는게 사실 썩 마음에 들지는 않습니다.
왜냐면 process는 말을 잘 안 듣습니다.
항상 변수는 생기고 말입니다.

<br>
아무튼!!  
<br>
kill()의 인자는 아래와 같이 사용할 수 있습니다.

<br>

## 🌱 Parameters
- pid > 0: signal을 pid에 해당하는 process로 전달
- pid == 0: signal을 process group으로 전달
- pid == -1: signal을 모든 권한이 있는 process로 전달
- pid < -1: signal이 모든 process group으로 전달
- signal == 0: signal은 전달되지 않고, 존재 여부를 보내는 용도로 사용되기도 함

그렇다면 Return 값은 어떻게 올까요?

<br>

## 🌱 Return Value

- 0: 성공이라면 무조건 0이랍니다
- -1: 실패랍니다

kill()은 실패라면 (-1) errno도 같이 줍니다.
return 값를 로그로 남기거나 체크할 때는 errno도 꼭 같이 찍어주세요.
안 찍으면요? 저도 알고 싶지 않았습니다.

<br>

## 🌱 Errno
- EINVAL: signal이 유효하지 않음
- EPERM: process에 권한이 없음
- ESRCH: process를 찾을 수 없음. 좀비 프로세스일 수도 있음 ㄷㄷ

<br>

---
<br>
kill의 header에 있는 signal.h은 

<br>

```bash
$ vi /usr/include/signal.h
```
여기서 볼 수 있습니다.

<br>

---
## 🌱signal 
signal은 항목이 정해져 있습니다.

<br>

```bash
$ kill -l
 1) SIGHUP	 2) SIGINT	 3) SIGQUIT	 4) SIGILL	 5) SIGTRAP
 6) SIGABRT	 7) SIGBUS	 8) SIGFPE	 9) SIGKILL	10) SIGUSR1
11) SIGSEGV	12) SIGUSR2	13) SIGPIPE	14) SIGALRM	15) SIGTERM
16) SIGSTKFLT	17) SIGCHLD	18) SIGCONT	19) SIGSTOP	20) SIGTSTP
21) SIGTTIN	22) SIGTTOU	23) SIGURG	24) SIGXCPU	25) SIGXFSZ
26) SIGVTALRM	27) SIGPROF	28) SIGWINCH	29) SIGIO	30) SIGPWR
31) SIGSYS	34) SIGRTMIN	35) SIGRTMIN+1	36) SIGRTMIN+2	37) SIGRTMIN+3
38) SIGRTMIN+4	39) SIGRTMIN+5	40) SIGRTMIN+6	41) SIGRTMIN+7	42) SIGRTMIN+8
43) SIGRTMIN+9	44) SIGRTMIN+10	45) SIGRTMIN+11	46) SIGRTMIN+12	47) SIGRTMIN+13
48) SIGRTMIN+14	49) SIGRTMIN+15	50) SIGRTMAX-14	51) SIGRTMAX-13	52) SIGRTMAX-12
53) SIGRTMAX-11	54) SIGRTMAX-10	55) SIGRTMAX-9	56) SIGRTMAX-8	57) SIGRTMAX-7
58) SIGRTMAX-6	59) SIGRTMAX-5	60) SIGRTMAX-4	61) SIGRTMAX-3	62) SIGRTMAX-2
63) SIGRTMAX-1	64) SIGRTMAX
```
이렇게 확인이 가능합니다.

보통 command로 kill을 하겠다하면

```bash
$ kill -9 {pid}
$ kill -KILL {pid}
```
이렇게 씁니다.

만약 signal을 안써준다면?
default는 SIGTERM으로 됩니다.

<br>
by.bell22 🙂
