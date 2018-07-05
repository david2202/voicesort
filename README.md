# Spring boot run

Assumes that you have CNTLM running as a local proxy.

| Property               | value        |
| :--------------------- | :----------- |
| http.proxyHost         | localhost    |
| http.proxyPort         | 3128         |
| spring.profiles.active | dev          |
| spring.profiles.active | dev-postgres |

See the script/postgres directory for running postgres locally in a
docker container.
