# πΈlimit.conf
<br>

```bash
$ vi /etc/security/limit.conf 
```
μ΄κ³³μμ μ°λ¦¬ μλ²μ λ¦¬μμ€ μ ν μ€μ μ ν΄μ€ μ μμ΅λλ€.

μ¬κΈ°μ μ€μ ν  μ μλ ν­λͺ©μ μλμ κ°μ΅λλ€.  
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

nproc(Process μ΅λ μμ± κ°μ)κ³Ό nofile(fd μ΅λ κ°μ) λλ¬Έμ μ§κΈ μ΄ κΈμ μ κ³  μλ΅λλ€. π£

κ·Έλ¦¬κ³  μ°Έκ³ λ‘, /etc/security/ λλ ν°λ¦¬μ μ΄λ° λλ ν°λ¦¬λ μμ΅λλ€.  

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
/etc/securitey/limit.conf μ€μ  κ°μ μ ν¨νκ² λ§λ€λΌλ©΄ 20-nproc.conf λ΄μ©μ μ­μ νκ±°λ νμΌλͺμ λ³κ²½ν΄μΌ νλ΅λλ€.   

<br> 

μμ€ν μμμ΄ λΆμ‘±ν΄μ μ€μ νμΌμ λ°κΏ¨λλ°, 20-nproc.confλ₯Ό κ·Έλλ‘ λλ©΄ μ΄λ»κ² λλκ΅¬μ? μ λ μκ³  μΆμ§ μμμ΅λλ€.