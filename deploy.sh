sbt clean package;
docker cp ~/projects/samples/people-work/target/scala-2.11/people-work_2.11-0.1.jar spark-master:/opt/spark-apps/app.jar
spark-submit \
  --driver-java-options "-Ddm.logging.level=DEBUG" \
  --class "io.hyperbuffer.samples.spark.FlightsDemo" \
  --master spark://spark-master:7077 \
  --deploy-mode cluster \
  /opt/spark-apps/app.jar
