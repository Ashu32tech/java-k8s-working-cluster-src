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

//aws setup
eksctl create cluster --name microservices-cluster  --region ap-south-1 --node-type t3.medium --nodes 2

cd C:\Users\ashu3\.kube

attrib -h -r config

aws eks --region ap-south-1 update-kubeconfig --name microservices-cluster


cd full-microservices-helm-chart

helm upgrade --install microservices . --namespace micro-demo --create-namespace

kubectl get pods -n micro-demo

kubectl get svc -n micro-demo

api-gateway   LoadBalancer   EXTERNAL-IP

http://<EXTERNAL-IP>

kubectl logs -f deployment/api-gateway -n micro-demo

helm delete microservices -n micro-demo
eksctl delete cluster --name microservices-cluster


eksctl delete cluster --name microservices-cluster
