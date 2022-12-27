# pprof

## 1. 환경설정

```vim
setenv HEAP_PROFILE_TIME_INTERVAL 10
setenv HEAPPROFILE /home/pprof/my.hprof
setenv LD_PRELOAD /usr/lib64/libtcmalloc.so
```

<br>

## 2. 실행 방법

<br>
아래처럼 하면 heapcheck 옵션처럼 메모리 영역을 볼 수 있답니다
<br>

```bash
[bell2@root]:/home/bell2/pprof % pprof test  my.prof --functions --heapcheck
Using local file test.
Using local file my.prof.
Welcome to pprof!  For help, type 'help'.
(pprof) top
Total: 30 samples
      16  53.3%  53.3%       16  53.3% ?? /usr/src/debug////////glibc-2.17-c758a686/nptl/../sysdeps/unix/syscall-template.S:81
       5  16.7%  70.0%        5  16.7% ?? /usr/src/debug////////glibc-2.17-c758a686/misc/../sysdeps/unix/syscall-template.S:81
       2   6.7%  76.7%        2   6.7% ?? /usr/src/debug////////glibc-2.17-c758a686/nptl/../nptl/sysdeps/unix/sysv/linux/x86_64/lowlevellock.S:371
       2   6.7%  83.3%        2   6.7% ?? /usr/src/debug////////glibc-2.17-c758a686/misc/../sysdeps/unix/syscall-template.S:81
       1   3.3%  86.7%        1   3.3% ?? /usr/src/debug////////glibc-2.17-c758a686/nptl/../sysdeps/unix/syscall-template.S:81
       1   3.3%  90.0%        1   3.3% ?? /usr/src/debug////////glibc-2.17-c758a686/socket/../sysdeps/unix/syscall-template.S:81
       1   3.3%  93.3%        1   3.3% __GI___pthread_mutex_lock
```

<br>

## 3. 결과 그림으로 뽑기

<br>

```vim
$ pprof test my.heap --pdf > test.pdf
```
