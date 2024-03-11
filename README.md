# Watchdog Backend Server
Watchdog GraphQL is a backend server to serve requests from clients. It is built using  Java17 and spring boot 3.2.2 version. 

## Developement

### Developer Local Setup Guide

1. Setup local Postgres Server & create a new database called watchdog using - ```createdb watchdog;```
2. Build Code (Java JAR file) - ```mvn clean install -DPOSTGRES_HOST=<db-host> -DPOSTGRES_USER=<db-user> -DPOSTGRES_DB=<db-name> -DPOSTGRES_PASSWORD=<db-password>```
3. Run Spring Service - ```mvn spring-boot:run -DPOSTGRES_HOST=<db-host> -DPOSTGRES_USER=<db-user> -DPOSTGRES_DB=<db-name> -DPOSTGRES_PASSWORD=<db-password>```
4. Graphiql page (Interactive Graphql Page) - `http:localhost:8080/graphiql?path=/graphql`
5. Populate local db with test data using - ```psql -h localhost -U username -d watchdog -a -f src/main/resources/sql/data.sql```

### Manage Tasks

1. Create New Task 
   - Add a new issue under https://github.com/mitul01/watchdog-graphql/issues
   - Select appropriate label for the task
   - Select project as `WatchDog`
2. Work on Pending Task
   - Select any task under issues https://github.com/mitul01/watchdog-graphql/issues
   - Mark the task as In Progress
   - Select on `Create a branch` for that issue 
   - Checkout the created branch and start working

### Contribute

1. Create and checkout new branch respective to the issue 
2. Commit changes to the newly created branch
3. Open pull request to merge changes from your branch into main
4. Preflights (checks) should be in successful state
5. Get the code reviewed from others
6. Merge the pull request

## Github Workflows

| Workflow File | Run On    | Steps    |
| :---:   | :---: | :---: |
| maven.yml | On Pull Request   | Build and test maven code   |
| ci.yml | On Push to Main   | CI Supply Chain |

### CI Supply Chain
1. Unit test code
2. Git tag latest commit with new patch version
3. Build Containers using pack
4. Publish Container to ghcr.io
5. Repository dispatch to watchdog-config to package code
