# Dib challenge

### Chosen technologies

- Spring Boot - based on position and it's part of the challenge
- Maven - you get it for free at [start.spring.io](start.spring.io), plus it's a nice way to manage dependencies
- Spring Data JPA with Hibernate - used it on previous project and it's the fastest and _easiest_ way to interact with DB
- Spring Web - used it on previous project; creating REST API with it is as easy as pie
- H2 DB - it's relational & in-memory, why including container for MySql or PostgreSQL DB :)
- Flyway - used it on previous project; it's nice to have DB versioned, plus it's easy to create tables on project start-up
- `RestTemplate` - used it couple of times on previous project to consume 3rd-party REST API, so I was playing safe (didn't explore other possibilities)

### Tackling the problem

The main idea was to separate all objects used for exposing API from objects used to consume 3rd-party API, because on the first sight 3rd-party API is something that might change in the future, and it's a good practice to encapsulate things that change. 

Therefore, main package `com.example.dibchallenge` contains 2 packages:
1. `beer` - containing everything related to beer entity and API that needs to be exposed
2. `punk_api_lib` - containing everything needed to interact with [Punk API](https://punkapi.com/documentation/v2)

#### `beer` package

At the root there are:

- Controller - exposed REST API, 
- DTO - object defining properties seen by API consumer, that is returned by Controller
- Entity - Hibernate entity containing column & relationship mappings
- Mapper - _Object_ responsible for converting one type of object into another, e.g. converting Entity to DTO, or DTO to Entity object
- Repository - Interface for interacting with DB
- Service (API) - methods provided by the service
- ServiceImpl - implementation of service API

All beer related config are stored in `beer.properties` file, and therefore there's a `BeerPropertiesConfig` class in `config` package, so that we can use those properties in code (e.g. inject them).

Every beer related exception in placed in `exception` package.

`mash_temp` package contains Entity and Service for mash temperatures.

`strategy` package contains 2 interfaces, `BeerModel` and `BeerStrategy`. `BeerModel` defines which properties are relevant from 3rd-party API response, and `BeerStrategy` defines methods that need to be implemented when creating a beer "library" using 3rd-party API. With this approach we can use whichever 3rd-party API for consuming beers. All we have to do is adapt its response to our needs.

#### `punk_api_lib` package

Contains DTOs for mapping JSON response objects. `PunkApiBeerDto` is the main one and therefore implements `BeerModel` interface.

All exceptions are placed in `exception` package.

`strategy` package contains `PunkApiBeerStrategy` which is implementation of `BeerStrategy`, and uses `RestTemplate` to consume Punk API.

### What would I do differently ...

- ... if I were given more time? I would probably try to optimize some things like DB calls, API calls or anything that I find suspicious (there are some TODOs in code). Maybe even experiment with unit/integration tests, since I've never had a chance to focus on that part of development process. 
- ... a second time around? Well, that would depend on discussion about requirements. At the moment I'm satisfied with solution and the time would tell if it was the good one. As Don Roberts said: 
> The first time you do something, you just do it. The second time you do something similar, you wince at the duplication, but you do the duplicate thing anyway. The third time you do something similar, you refactor.

### Playing with API

When server is **running**, API documentation is available on [localhost:9000/swagger-ui.html](http://localhost:9000
/swagger-ui.html).

##### cURL

- Get all beers
```
curl localhost:9000/beers 
```

Response example:

```json
{
    "content": [
        {
            "id": 1,
            "name": "Stereo Wolf Stout - Prototype Challenge",
            "description": "Roasty, dark and complex, Stereo Wolf is a 5.2% ABV Stout brewed with a cacophony of hop-driven fruit and spice. Dark chocolate stalks alongside the resonating bitterness, through to Stereo Wolf’s lingering dry finish. Balancing roasty malt and new world hops at 5.2% ABV is no mean feat, but Stereo Wolf takes it in its stride.",
            "meanTemperature": 65.0000
        }
    ],
    "pageable": {
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "pageNumber": 0,
        "pageSize": 50,
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "totalElements": 1,
    "last": true,
    "totalPages": 1,
    "first": true,
    "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
    },
    "numberOfElements": 1,
    "size": 50,
    "number": 0,
    "empty": false
}
```

---

- Get beer by ID
```
curl localhost:9000/beers/{id}
```

Response example:

```json
{
    "id": 1,
    "name": "Stereo Wolf Stout - Prototype Challenge",
    "description": "Roasty, dark and complex, Stereo Wolf is a 5.2% ABV Stout brewed with a cacophony of hop-driven fruit and spice. Dark chocolate stalks alongside the resonating bitterness, through to Stereo Wolf’s lingering dry finish. Balancing roasty malt and new world hops at 5.2% ABV is no mean feat, but Stereo Wolf takes it in its stride.",
    "meanTemperature": 65.0000
}
```

Error response:

```json
{
    "timestamp": "2020-02-23T16:56:51.486+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Beer not found!",
    "path": "/beers/10"
}
```

---

- Delete beer by ID
```
curl -X "DELETE" localhost:9000/beers/{id}
```

Response example:

`204 No Content`

---

- Insert up to 10 beers in DB
```
curl -X "POST" localhost:9000/beers/insert-from-punk-api
```

Response example (number of beers inserted in DB):

```
5
```


##### IntelliJ IDEA IDE

Root of the project includes `beer_req.http` file which can be used from IntelliJ IDEA IDE to run HTTP(S) requests.

##### Postman

In the root of project there's a Postman collection `DibChallenge.postman_collection.json` containing all API calls currently implemented, which can be [imported](https://learning.postman.com/docs/postman/collection-runs/working-with-data-files/) in Postman.

### Running the project

Thanks to the Spring Boot, starting the project is as easy as pie (assuming you are at the root of the project):

```
./mvnw spring-boot:run
```
    
