<h1 align="center">Java project that imports data from a Excel - SGT/CNJ</h1>
<p align="center">This is a Java project that imports data from a Excel file.</p>

### Objective:
<p>Create a Spring Boot project that reads a Excel file (SGT/CNJ) and import all data (e.g. motions data) into a Postgres database.</p>

### SGT/CNJ file:
https://www.cnj.jus.br/sgt/versoes.php?tipo_tabela=M

### How to share the maven repository of a image with the container:


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

