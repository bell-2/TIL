# π mariaDB login

π mariadb λ‘κ·ΈμΈ λ°©λ² (docker mariadb μμ μ€ν)
<br>

```bash
mysql -u root -p
```
<br>
π database λ³ν   
<br>

```sql
MariaDB [mysql]> use {μ¬μ©ν  DB μ΄λ¦λ¦}
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed
```
<br>

# π docker mariadb image

π docker image μ€μ§μ€ν λ°©λ²
```bash
docker stop {mariadb image μ΄λ¦}
docker restart {mariadb image μ΄λ¦}
```
<br>

π dockerμμ μ€ν μ€μΈ image λ³΄λ λ°©λ²
```bash
~$ docker ps
CONTAINER ID   IMAGE                       COMMAND                  CREATED         STATUS      PORTS   NAMES
{mariadb container ID}   mariadb:1.1.1.1             "docker-entrypoint.sβ¦"   2 months ago    Up 18 minutes   0.0.0.0:13306->3306/tcp, :::13306->3306/tcp   {mariadb image μ΄λ¦}
```

<br>

π docker mariadb image μ μ λ°©λ²
```bash
~$ docker exec -it {mariadb container ID} bash
root@{mariadb container ID}:
```
