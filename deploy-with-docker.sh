sbt clean package;
cp ~/projects/samples/people-work/target/scala-2.11/people-work_2.11-0.1.jar ~/projects/samples/spark-demo/mnt/spark-apps/app.jar
docker-compose -f ~/projects/samples/spark-demo/custom/docker-compose-submit.yml up -d;
