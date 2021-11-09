<h1 align="center">Java project that imports data from a Excel - SGT/CNJ</h1>
<p align="center">This is a Java project that imports data from a Excel file.</p>

### Objective:
<p>Create a Spring Boot project that reads a Excel file (SGT/CNJ) and import all data (e.g. motions data) into a Postgres database.</p>

### SGT/CNJ file:
https://www.cnj.jus.br/sgt/versoes.php?tipo_tabela=M

<hr>
<h4 align="center">Known issues</h4>

```
Issue: 
  "Could not change permissions of directory "/var/lib/postgresql/data": Operation not permitted"
  
Solution:
  Use named volumes such like the following: 
  e.g.:
    (...)
    volumes:
      - postgres:/mnt/d/volume/Postgresql/sgt/data
    (...) 
  volumes:
    postgres:
```
