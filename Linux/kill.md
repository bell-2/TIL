# ๐ Kill

kill์ด๋ผ๋ ํจ์๋ก Process์ signal์ ๋ณด๋ผ ์ ์์ต๋๋ค.
<br>

๊ธฐ๋ฅ ๋๋ฌธ์ kill์ด ์๋๋ผ send_signal๋ก ํด์ผํ๋๊ฑฐ ์๋๋๋ผ๋ ์๊ธฐ๋ ์์ต๋๋ค.

<br>
command๋ก๋ ์๋์ ๊ฐ์ด ์ธ ์ ์์ต๋๋ค.

```bash
$ kill -9 {pid}
```
๐ ์ฐธ๊ณ ๋ก pid๋ process id๋ฅผ ์ค์ฌ์ฐ๋ ๋จ์ด๋๋๋ค

<br>

C์์ด๋ Process๋ฅผ ๋ค๋ฃจ๋ค๋ณด๋ฉด kill() ํจ์๋ก ์ฐ๊ธฐ ๋ง๋ จ์๋๋ค.
<br>
์ค๋ kill์ ๋๋ฌผ ์ฐ๋ ํ๋ฆฌ๊ณ  ์ ๋ฆฌํ๋ฌ ์์ต๋๋ค.

<br>

```c
#include <signal.h>
int kill(pid_t pid, int signal)
```
<br>
์ฌ์ฉ๋ฐฉ๋ฒ์ ์์ ๊ฐ์ต๋๋ค.
<br>
signal์ ๋ณด๋ธ๋ค๋๊ฒ ์ฌ์ค ์ฉ ๋ง์์ ๋ค์ง๋ ์์ต๋๋ค.
์๋๋ฉด process๋ ๋ง์ ์ ์ ๋ฃ์ต๋๋ค.
ํญ์ ๋ณ์๋ ์๊ธฐ๊ณ  ๋ง์๋๋ค.

<br>
์๋ฌดํผ!!  
<br>
kill()์ ์ธ์๋ ์๋์ ๊ฐ์ด ์ฌ์ฉํ  ์ ์์ต๋๋ค.

<br>

## ๐ฑ Parameters
- pid > 0: signal์ pid์ ํด๋นํ๋ process๋ก ์ ๋ฌ
- pid == 0: signal์ process group์ผ๋ก ์ ๋ฌ
- pid == -1: signal์ ๋ชจ๋  ๊ถํ์ด ์๋ process๋ก ์ ๋ฌ
- pid < -1: signal์ด ๋ชจ๋  process group์ผ๋ก ์ ๋ฌ
- signal == 0: signal์ ์ ๋ฌ๋์ง ์๊ณ , ์กด์ฌ ์ฌ๋ถ๋ฅผ ๋ณด๋ด๋ ์ฉ๋๋ก ์ฌ์ฉ๋๊ธฐ๋ ํจ

๊ทธ๋ ๋ค๋ฉด Return ๊ฐ์ ์ด๋ป๊ฒ ์ฌ๊น์?

<br>

## ๐ฑ Return Value

- 0: ์ฑ๊ณต์ด๋ผ๋ฉด ๋ฌด์กฐ๊ฑด 0์ด๋๋๋ค
- -1: ์คํจ๋๋๋ค

kill()์ ์คํจ๋ผ๋ฉด (-1) errno๋ ๊ฐ์ด ์ค๋๋ค.
return ๊ฐ๋ฅผ ๋ก๊ทธ๋ก ๋จ๊ธฐ๊ฑฐ๋ ์ฒดํฌํ  ๋๋ errno๋ ๊ผญ ๊ฐ์ด ์ฐ์ด์ฃผ์ธ์.
์ ์ฐ์ผ๋ฉด์? ์ ๋ ์๊ณ  ์ถ์ง ์์์ต๋๋ค.

<br>

## ๐ฑ Errno
- EINVAL: signal์ด ์ ํจํ์ง ์์
- EPERM: process์ ๊ถํ์ด ์์
- ESRCH: process๋ฅผ ์ฐพ์ ์ ์์. ์ข๋น ํ๋ก์ธ์ค์ผ ์๋ ์์ ใทใท

<br>

---
<br>
kill์ header์ ์๋ signal.h์ 

<br>

```bash
$ vi /usr/include/signal.h
```
์ฌ๊ธฐ์ ๋ณผ ์ ์์ต๋๋ค.

<br>

---
## ๐ฑsignal 
signal์ ํญ๋ชฉ์ด ์ ํด์ ธ ์์ต๋๋ค.

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
์ด๋ ๊ฒ ํ์ธ์ด ๊ฐ๋ฅํฉ๋๋ค.

๋ณดํต command๋ก kill์ ํ๊ฒ ๋คํ๋ฉด

```bash
$ kill -9 {pid}
$ kill -KILL {pid}
```
์ด๋ ๊ฒ ์๋๋ค.

๋ง์ฝ signal์ ์์จ์ค๋ค๋ฉด?
default๋ SIGTERM์ผ๋ก ๋ฉ๋๋ค.

<br>
by.bell22 ๐
