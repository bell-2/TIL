# prompt statement 수정

linux 환경은 직접 뭔가 설정하지 않으면 꽤 투박한.. 매력이 있다

이것저것 설정하면 그래도 이거만큼 뿌듯한게 없는 듯

현재 개발 중인 내용을 문서화 하고 있었는데, 현재 위치를 같이 첨부해야하는 경우가 있었다. <br>
 디렉터리에 이동할 때마다 현재 디렉터리의 위치를 pwd 명령어로 확인하는게 너무 번거로웠고, 그래서 PS (Prompt Statement)를 수정을 하게 됐다

<br>

## 🔨 PS1과 PS2

bash에서 사용하는 설정을 바꿀 수 있는 환경 설정 변수가 여러가지가 있다.
<br>그중 PS1과 PS2라는 환경 변수가 있는데, 뜻은 Prompt 상태 정도가 되시겠다.

<br>

PS1은 처음에 쉘창을 켰을 때 나타나는 화면의 설정에 대한 내용이고
<br>PS2는 완전한 입력이 되지 않았을 때 '>' 기호로 표기되는 설정을 뜻한다.

<br>


```bash
# PS1
[root@bell2]  

# PS2
[root@bell2]  hello \
>
>
```

<br>

그리고 이를 설정할 수 있는 bashrc 파일이 있고, 여기서 환경 변수 설정을 할 수가 있당

<br>

```bash
[root@bell2] cat ~/.bashrc
...
PS1="[\u@\$PWD]\\$ "
...
```


<br>

### Before

```bash
[root@root] cd bell2
[root@bell2] pwd
/root/bell2 
```
<br>

### After

```bash
[root@root] cd bell2
[root@/root/bell2] 
```