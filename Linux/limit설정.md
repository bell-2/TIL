# 🌸limit.conf
<br>

```bash
$ vi /etc/security/limit.conf 
```
이곳에서 우리 서버의 리소스 제한 설정을 해줄 수 있습니다.

여기서 설정할 수 있는 항목은 아래와 같습니다.  
<br>

- core - limits the core file size (KB)
- data - max data size (KB)
- fsize - maximum filesize (KB)
- memlock - max locked-in-memory address space (KB)
- nofile - max number of open file descriptors
- rss - max resident set size (KB)
- stack - max stack size (KB)
- cpu - max CPU time (MIN)
- nproc - max number of processes
- as - address space limit (KB)
- maxlogins - max number of logins for this user
- maxsyslogins - max number of logins on the system
- priority - the priority to run user process with
- locks - max number of file locks the user can hold
- sigpending - max number of pending signals
- msgqueue - max memory used by POSIX message queues (bytes)
- nice - max nice priority allowed to raise to values: [-20, 19]
- rtprio - max realtime priority

nproc(Process 최대 생성 개수)과 nofile(fd 최대 개수) 때문에 지금 이 글을 적고 있답니다. 😣

그리고 참고로, /etc/security/ 디렉터리에 이런 디렉터리도 있습니다.  

<br>

```bash
$ pwd
etc/security/limits.d
$ cat 20-nproc.conf
# Default limit for number of user's processes to prevent
# accidental fork bombs.
# See rhbz #432903 for reasoning.

*          soft    nproc     4096
root       soft    nproc     unlimited
```

<br>
/etc/securitey/limit.conf 설정 값을 유효하게 만들라면 20-nproc.conf 내용을 삭제하거나 파일명을 변경해야 한답니다.   

<br> 

시스템 자원이 부족해서 설정파일을 바꿨는데, 20-nproc.conf를 그대로 두면 어떻게 되냐구요? 저도 알고 싶지 않았습니다.