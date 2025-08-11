# Spring Boot REST test project

## Commands for browser:

Add unit:
```
fetch('http://localhost:8080/create', {
method: 'POST',
headers: {
'Content-Type': 'application/json'
},
body: JSON.stringify(

{
  "numberOfRooms": "15",
  "unitType": "FLAT",
  "floor": "1",
  "cost": "99.99",
  "description": "its a flat"
}

)
})
.then(response => response.text())
.then(data => console.log(data));
```

Book unit with ID 1:

```
fetch('http://localhost:8080/book', {
method: 'POST',
headers: {
'Content-Type': 'application/json'
},
body: JSON.stringify(

{
  "livingUnitId": "1",
  "startDate": "2025-10-01",
  "endDate": "2025-10-07"
}

)
})
.then(response => response.text())
.then(data => console.log(data));
```

Get all units:
```
fetch('http://localhost:8080', {
method: 'GET',
headers: {
'Content-Type': 'application/json'
},
})
.then(response => response.text())
.then(data => console.log(data));
```

Get units with id 1:
```
fetch('http://localhost:8080/1', {
method: 'GET',
headers: {
'Content-Type': 'application/json'
},
})
.then(response => response.text())
.then(data => console.log(data));
```

Search units:
```
fetch('http://localhost:8080?page=0&size=5&numberOfRooms=2', {
method: 'GET',
headers: {
'Content-Type': 'application/json'
},
})
.then(response => response.text())
.then(data => console.log(data));
```

To run Docker database:

```
docker compose -f compose.yaml up
```

I'm writing Java Spring REST service where I can book LivingUnits and store bookings into database using JPA. How to handle booking dates? It may be multiple periods