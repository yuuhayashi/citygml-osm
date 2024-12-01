cp ../../conversion.json .
java -Dfile.encoding=utf-8 -jar ../../citygml-osm-jar-with-dependencies.jar unpack
java -Dfile.encoding=utf-8 -jar ../../citygml-osm-jar-with-dependencies.jar pack
rm *.zip