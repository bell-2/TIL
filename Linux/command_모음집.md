자주 쓰는데 갑자기 생각안날 때가 있는 command 모음집 😣

---

## tcpdump
### tcp dump 파일 생성
```bash
tcpdump -i [인터페이스이름] -w [파일이름]
# tcpdump -i em1 -w test.pcap
```
<br>

### (추가 😁) pcap 파일 Wireshark에서 url으로 찾기 
```bash
ip.addr == 100.100.100.100 && http.request.uri contains "/onvif/"
```

<br>

### PID의 메모리 사용률 확인
```bash
top -p {확인할 PID 번호}
```
메모리릭을 확인하는 당신은 VIRT와 RES 필드를 확인하세요 😂

<br>

### disk full 발생 시 Process 정보 확인
```bash
dmesg | gail
```
disk 용량 확인하고 싶다면!?
```bash
du -h * --max-depth=1 | sort -hr
```

<br>

### HTTP 메시지 Simple test 방법
```bash
nc -v {IP 주소} {포트 번호}
# message...
```
<br>
서버용으로 쓰고 싶다면 !?

```bash
nc -l {listen할 port}
```

<br>   

### gdb 사용법
참고로 core 발생한 바이너리의 pid가 core 파일 이름에 있다
```bash
$ gdb ./{core 발생한 바이너리} {core 파일}
(gdb) info threads
# thread List
(gdb) thread 58
# 58번 thread 
```
만약 coredump가 설치 되어있는 환경이라면 
```bash
coredumpctl gdb {PID}
# /var/lib/systemd/coredump
```

<br>

### package 설치시
Ubuntu에서는 sudo
CentOS에서는 yum으로 설치

<br>

## VIM 이것저것
### vim에서 맞춤법 확인
```vim
: set spell
```

### vim에서 단어 블록해서 찾기
```vim
shift + 3
```

### man page 열기
```vim
shift + k
```
<br>

### 내 Linux Public IP 확인하기
```bash
curl ifconfig.me
```
<br>

### 서버에 특정 port를 쓰는 Process가 있는지 확인하기
```bash
netstat -ap | grep :{port 번호}
```

<br>

### 물리적 CPU 개수 확인
```bash
grep 'physical id' /proc/cpuinfo | uniq | wc -l
```
<br>

### CPU 당 코어 수 확인
```bash
grep 'cpu cores' /proc/cpuinfo | uniq
```

<br>

### OS 정보 커널 정보 확인
```bash
uname -a
```

<br>

### 하위 디렉터리 경로 탐색
```bash
ls -lRs | find {디렉터리} -name "{검색하고 싶은 단어}" -print
```

<br>

### 방화벽 확인
참고로 방화벽은 root 계정으로 해야한답니닷
```bash
# 방화벽 상태 확인
systemctl status firewalld.service

# 방화벽 내리기
systemctl stop firewalld.service
```

위에 커맨드로 내리기만 한다면 서버를 다시 기동시키면 방화벽이 또 올라간답니다

```bash
# 사용안하도록 설정
systemctl disable firewalld.service
```

<br>

### Process 실행 위치 확인
```bash
ls -al /proc/{PID 번호} | grep exe 
```

<br>

### Process 실행 시 stdout/err 출력 안나오게 하는 방법
```bash
>/dev/null 2>&1 & 
```

<br>

### git diff 시 color로 보고싶을 때
```bash
git config --global --add color.ui true
```