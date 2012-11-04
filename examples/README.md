scalding.avro example usage
===========================

This example can be built and run with one of the following commands:
- For the fields API version
-- sbt "run-main scalding.avro.examples.WordCountAvroJob scalding.avro.examples.WordCountAvroJob --hdfs -input /etc/*.conf -output myavro"
- For the typed API version:
-- sbt "run-main scalding.avro.examples.WordCountAvroTypedJob scalding.avro.examples.WordCountAvroTypedJob --hdfs -input /etc/*.conf -output myavro"


The first class specification is for SBT, the second is for Scalding. If you tell SBT what your main class is in the build.sbt file you can skip the first class specification on the command line. 
