!#
sudo docker pull postgres:9.6
sudo docker run -p 5432:5432 --name postgres -e POSTGRES_PASSWORD=password -d postgres:9.6
sudo docker ps -a

# Stop your postgres container

# sudo docker stop 89865a726b42

# Start your postgres container

# sudo docker start 89865a726b42

# Remove your docker container

# sudo docker rm 89865a726b42
