put text/plain data ==> the server side should consumes text/plain
curl -v -H "Content-Type: text/plain" -X PUT --data "data_string" -u login:password localhost

put json or xml data ==> the server side should consumes application/json
curl -v -H "Content-Type: application/json" -X PUT --data "@fname.json" -u login:password localhost