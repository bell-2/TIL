# ldd
ldd는 프로그램의 라이브러리의 의존성을 확인할 때 사용하는 명령어 입니다.
<br>

프로그램을 서버에 설치하고 간혹 제대로 기동이 안될 때가 있는데
그러면 라이브러리가 제대로 링크가 안되어있다던가.. 그런 경우가 있는데
<br>
그 때 ldd 명령어를 사용하면 조금 더 빨리 해결할 수 있습니다 💫

<br>

```bash
% ldd --help
사용법: ldd [옵션]... <파일>...
      --help              이 도움말을 출력하고 끝납니다
      --version           버전 정보를 표시하고 끝납니다
  -d, --data-relocs       데이터 리로케이션을 처리합니다
  -r, --function-relocs   데이터 및 함수 리로케이션을 처리합니다
  -u, --unused            사용하지 않는 직접 의존성을 표시합니다
  -v, --verbose           전체 정보를 표시합니다

```

<br>

사용법은 매우 간단합니당
만약에 a.out 이라는 프로그램이 있다고 해봅시다

<br>


``` bash
% ldd a.out
	linux-vdso.so.1 =>  (0x00007ffc72d86000)
	libc.so.6 => /usr/lib64/libc.so.6 (0x00002abb77b70000)
	/lib64/ld-linux-x86-64.so.2 (0x00002abb7794e000)
```

<br>

a.out에서 사용하고 있는 라이브러리는 이렇군요~

자세히 봐볼까요?

<br>


```bash
% ldd --verbose a.out
	linux-vdso.so.1 =>  (0x00007ffc47b5e000)
	libc.so.6 => /usr/lib64/libc.so.6 (0x00002b6fe5b26000)
	/lib64/ld-linux-x86-64.so.2 (0x00002b6fe5904000)

	Version information:
	./a.out:
		libc.so.6 (GLIBC_2.2.5) => /usr/lib64/libc.so.6
	/usr/lib64/libc.so.6:
		ld-linux-x86-64.so.2 (GLIBC_2.3) => /lib64/ld-linux-x86-64.so.2
		ld-linux-x86-64.so.2 (GLIBC_PRIVATE) => /lib64/ld-linux-x86-64.so.2
```

<br>

ldd를 통해 우리가 평소에 자주 사용하는 command도 들여다볼 수 있습니다.

<br>


```bash
% ldd /usr/bin/docker
	linux-vdso.so.1 =>  (0x00007ffd70716000)
	libpthread.so.0 => /usr/lib64/libpthread.so.0 (0x00002afc52330000)
	libltdl.so.7 => /usr/lib64/libltdl.so.7 (0x00002afc5254c000)
	libc.so.6 => /usr/lib64/libc.so.6 (0x00002afc52756000)
	/lib64/ld-linux-x86-64.so.2 (0x00002afc5210e000)
	libdl.so.2 => /usr/lib64/libdl.so.2 (0x00002afc52b17000)
```

<br>

예를 들어 docker는 이렇게 구성이 되어있네요.
익숙한 pthread도 보이구요. 

참고로 vsdo는 virtual dynamic shared object로 가상 동적 공유 오브젝트를 뜻합니다.
