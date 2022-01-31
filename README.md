<h1 align="center">Java project that imports data from a Excel - SGT/CNJ</h1>
<p align="center">This is a Java project that imports data from a Excel file.</p>

### Objective:
<p>Create a Spring Boot project that reads a Excel file (SGT/CNJ) and import all data (e.g. motions data) into a Postgres database and generate load data scripts and/or reports.</p>

### SGT/CNJ file:
https://www.cnj.jus.br/sgt/versoes.php?tipo_tabela=A

### How to change database password:
Do the next procedments every time you recreate database volume.
```console
bscpaz@2am:/$ docker exec -it sgt-db bash
bash-5.1# su postgres
/$ psql
postgres=# \password postgres
Enter new password: <new-password>
postgres=# \q
```

### Visual Studio worksapce
```console
Open remote project [WSL:Ubuntu-18.04] in "/home/tr***/workspace/sgt-import" path
```

<hr>
<h4 align="center">Known issues</h4>

```
Issue: 
  "Could not change permissions of directory "/var/lib/postgresql/data": Operation not permitted"
  
Solution:
  Do not map the volume pointing to a Windows path. Instead, map to a WSL2 directory, like /var/lib/docker/volumes.
  e.g.: /var/lib/docker/volumes/postgresql/data
```

```
Issue: 
  "ERROR [internal] load metadata for docker.io/library/postgres:14.0-alpine ".
  "failed to create LLB definition: rpc error"
  
Solution:
  Docker desktop -> Settings -> Docker Engine -> Change the "features": { buildkit: true} to "features": { buildkit: false}.
```

