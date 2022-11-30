# 🙂 mariaDB login

🚛 mariadb 로그인 방법 (docker mariadb 에서 실행)
<br>

```bash
mysql -u root -p
```
<br>
🚛 database 변환   
<br>

```sql
MariaDB [mysql]> use {사용할 DB 이름름}
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed
```
<br>

# 🙂 docker mariadb image

🚛 docker image 중지실행 방법
```bash
docker stop {mariadb image 이름}
docker restart {mariadb image 이름}
```
<br>

🚛 docker에서 실행 중인 image 보는 방법
```bash
~$ docker ps
CONTAINER ID   IMAGE                       COMMAND                  CREATED         STATUS      PORTS   NAMES
{mariadb container ID}   mariadb:1.1.1.1             "docker-entrypoint.s…"   2 months ago    Up 18 minutes   0.0.0.0:13306->3306/tcp, :::13306->3306/tcp   {mariadb image 이름}
```

<br>

🚛 docker mariadb image 접속 방법
```bash
~$ docker exec -it {mariadb container ID} bash
root@{mariadb container ID}:
```
