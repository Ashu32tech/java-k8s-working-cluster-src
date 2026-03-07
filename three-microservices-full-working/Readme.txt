minikube start --driver=docker 
Build All 3 Images

From project root:

cd api-gateway
mvn clean package
docker build -t api-gateway .
minikube image load api-gateway:latest

cd ../order-service
mvn clean package
docker build -t order-service .
minikube image load order-service:latest 

cd ../payment-service
mvn clean package
docker build -t payment-service .
minikube image load payment-service:latest

cd full-microservices-helm-chart
helm install microservices .



minikube service api-gateway -n micro-demo

//clean the deployment
helm delete microservices 
