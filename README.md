**Сервер путевых листов**

запуск в докере:

``
docker run -d --rm -e DATABASE_URL=<jdbc url> -e DATABASE_USERNAME=<username> -e DATABASE_PASSWORD=<password> -p "8080:8080" kesler/apps:routeforms-server-2.0.0.3
``
