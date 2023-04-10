# spring-boot-webflux-mongodb-tracing-issue-4-10-2023

Steps to reproduce:

1. Remove records from "entity" collection if any exists.
2. Call http://localhost:8080/entity/1
3. Check logs