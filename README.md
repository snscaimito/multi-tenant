# multi-tenant
A proof of concept for multi-tenancy

- secure RESTful interface
- user details are stored in tenant-specific MongoDB instances (containers)
- JWT to know who's calling
- Redis to store and retrieve `{ username, password, tenant database information ... }`


	curl -i -H "Content-Type: application/json" -X POST -d '{
	    "username": "hans",
	    "password": "geheim"
	}' http://localhost:8080/login

	HTTP/1.1 200
	Vary: Origin
	Vary: Access-Control-Request-Method
	Vary: Access-Control-Request-Headers
	Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoYW5zIiwiZXhwIjoxNjEzODk4NDczfQ.cwr1ChJiQMmvjDG1c8S_vQ0jbjprfBvdU1lcpcg7LqjjA3FG-wmhhzjDqQ7i5iV0VDrJV_nL8_PMjyMTSuMX-g
	X-Content-Type-Options: nosniff
	X-XSS-Protection: 1; mode=block
	Cache-Control: no-cache, no-store, max-age=0, must-revalidate
	Pragma: no-cache
	Expires: 0
	X-Frame-Options: DENY
	Content-Length: 0
	Date: Thu, 11 Feb 2021 09:07:53 GMT


	curl -H "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoYW5zIiwiZXhwIjoxNjEzODk4NDczfQ.cwr1ChJiQMmvjDG1c8S_vQ0jbjprfBvdU1lcpcg7LqjjA3FG-wmhhzjDqQ7i5iV0VDrJV_nL8_PMjyMTSuMX-g" http://localhost:8080/greet/Stephan

	{"message":"Hello, Stephan"}%
