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
minikube stop

//docker image push
docker login
PS D:\aiInAction> docker tag api-gateway:latest ashu33tech/api-gateway:latest
PS D:\aiInAction> docker push ashu33tech/api-gateway:latest
PS D:\aiInAction> docker tag payment-service:latest ashu33tech/payment-service:latest
PS D:\aiInAction> docker push ashu33tech/payment-service:latest   
PS D:\aiInAction> docker tag order-service:latest ashu33tech/order-service:latest    
PS D:\aiInAction> docker push ashu33tech/order-service:latest  
