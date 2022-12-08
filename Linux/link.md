
# Symbolic Link

### Symbolic Link 생성

<br>

 ```bash
CMD> ln -s {링크걸고 싶은 파일} {링크 이름}
``` 


```bash
합계 0
drwxr-xr-x. 2 pp pp 48 12월 17 17:17 .
drwxr-xr-x. 3 pp pp 17 12월 17 16:58 ..
-rw-r--r--. 1 pp pp  0 12월 17 16:59 library.so.0
-rw-r--r--. 1 pp pp  0 12월 17 17:17 library.so.0.1.0
 ```

만약 링크 걸고 싶은 파일이 library.so.0.1.0 이고 >> {링크 걸고 싶은 파일}

링크 이름으로 지정하고 싶은 이름이 link_test 라면 >> {링크 이름}


```bash
CMD> ln -s library.so.0.1.0 link_test
 ```

<br>
 
---

### Symbolic Link 변경

<br>

 ```bash
CMD> ln -Tfs {바꾸고 싶은 파일} {기존 링크 이름}
 ```
<br>

옵션만 -Tfs로 바꿔주면 되고, 파라미터 순서는 똑깥다
만약에 바꾸고 싶은 파일이 library.so.0.2.0 이고 >> {바꾸고 싶은 파일}

관련된 기존 링크 이름이 link_test 라면 >> {기존 링크 이름}

<br>


```bash
CMD> ln -Tfs library.so.0.2.0 link_test
 ```


<br>

---

### Symbolic Link 제거

<br>

```bash
CMD> rm {제거할 링크 이름}
 ```

<br>

링크 제거는 엄청 간단하다 rm 명령어에 (rm 명령어는 항상 조심하자)

제거할 링크 이름 >> link_test 만 적어주면 끝이다

 
```bash
CMD> rm link_test
 ```
