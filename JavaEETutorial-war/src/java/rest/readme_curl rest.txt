put text/plain data ==> the server side should consumes text/plain
curl -v -H "Content-Type: text/plain" -X PUT --data "data_string" -u login:password localhost

put json or xml data ==> the server side should consumes application/json
curl -v -H "Content-Type: application/json" -X PUT --data "@fname.json" -u login:password localhost

POST is similar to PUT

curl -X POST --data-binary "<book><description>Science fiction comedy book</description><illustrations>false</illustrations><isbn>1-84023-742-2</isbn><nbOfPage>354</nbOfPage><price>12.5</price><title>The Hitchhiker's Guide to the Galaxy</title></book>" -H "Content-Type: application/xml" http://localhost:8080/cdi/resources/book -v
curl -X POST --data-binary "{\"description\":\"Science fiction comedy book\",\"illustrations\":false,\"isbn\":\"1-84023-742-2\",\"nbOfPage\":354,\"price\":12.5,\"title\":\"The Hitchhiker's Guide to the Galaxy\"}" -H "Content-Type: application/json" http://localhost:8080/cdi/resources/book -v