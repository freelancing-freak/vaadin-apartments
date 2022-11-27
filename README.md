# Vaadin apartments

Admin panel

### Deployment

1. heroku login
2. mvn package -Pproduction
3. heroku deploy:jar target/vaadin-apartments-0.0.1-SNAPSHOT.jar --app vaadin-apartments-prod

*. heroku buildpacks:clear
