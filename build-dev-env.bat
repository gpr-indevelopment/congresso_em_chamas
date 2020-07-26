start "" code frontend
start "" idea64 backend/pom.xml
start "" "C:\Program Files\Docker\Docker\Docker Desktop.exe"
timeout 60
docker-compose -f docker-compose-dev.yml up -d
exit