# ReciMe Coding Challenge (RCC)

ReciMe Coding Challenge aka `Recipe Core Console` (RCC) is a RESTful Backend
that provides the creation, retrieval, search, deletion, and update of a cooking recipe.

- [Service Technical Specifications](#service-technical-specs)
- [Setup Guide](#setup-guide)
- [API Specifications](#api-specs)
  - [Create Recipe](#create-recipe)
  - [Find Recipe](#find-recipe)
  - [Update Recipe](#update-recipe)
  - [Delete Recipe](#delete-recipe)
  - [Search Recipe](#search-recipe)
  - [API Schema](#api-schema)
    - [Ingredient Schema](#ingredient-schema)
    - [Diet Type Schema](#diet-type-schema)
    - [Search Criteria Schema](#search-criteria-schema)
- [Database Specifications](#database-specs)
- [Future Implementations](#future-implementations)
- [References](#references)

## Service Technical Specs
- Java Spring Boot v3.5.11
- Java 17
- PostgreSQL 14.17

## Setup Guide
1. Clone and open this repository on your IDE
2. On your IDE's terminal, run `mvn clean install`

#### IntelliJ
1. In IntelliJ, create a configuration file by clicking the `Current File` dropdown in the upper right corner then clicking `Edit Configurations...`
2. Click `Add New...` > `Application`
3. Name the configuration file (e.g. `AppConfig`), make sure that you're using `Java 17`
4. Click the rightmost button on the `Main class` field and Select `Application.java` from the options. 
5. Apply your changes 
6. You may now click on `Run/Debug App Config` in the upper right corner just beside the configuration file that you created.

## API Specs
> API Postman Collection can be found in: [./docs/ReciMe_Coding_Challenge.postman_collection.json](./docs/ReciMe_Coding_Challenge.postman_collection.json)
### Request Headers
> ⚠ These request headers are required for ALL types of requests. This will be helpful in tracing process flows in the service

| field        | type | description                                          | required? (Yes/Optional/Conditionally Required) |
|--------------|------|------------------------------------------------------|-------------------------------------------------|
| x-request-id | UUID | identifier for the request coming in to the service  | Yes                                             |
---
### Create Recipe

**Request Body**

| field        | type          | description                                                | required? (Yes/Optional/Conditionally Required) |
|--------------|---------------|------------------------------------------------------------|-------------------------------------------------|
| title        | string        | title of recipe                                            | Yes                                             |
| servings     | number        | number of servings                                         | Yes                                             |
| description  | string        | description of the recipe                                  | Optional                                        |
| ingredients  | Ingredient [] | ingredients in this recipe. Must follow Ingredient schema  | Yes                                             |
| diet_type    | Diet Type []  | diet types of the recipe. Must follow the Diet Type schema | Optional                                        |
| instructions | string        | instructions of recipe (in RichText format*)               | Yes                                             |

**Sample Request**

`POST /recipe`
```json
{
   "title": "Pork Adobo",
   "servings": 4,
   "dietTypes": [
      {
         "name": "VEGETARIAN"
      }
   ],
   "description": "Filipino Staple Food",
   "instructions": "1. Mix all ingredients together\\n2. Cook until meat is brown\\n3. Add water, cook for 45 minutes\\n4. Serve",
   "ingredients": [
      {
         "name": "Pork"
      }
   ]
}
```

**Sample Response**

`201 CREATED`

```json
{
   "id": "6e753255-0ee9-4147-a531-ea99049d6358",
   "title": "Pork Adobo",
   "description": "Filipino Staple Food",
   "servings": 4,
   "instructions": "1. Mix all ingredients together\\n2. Cook until meat is brown\\n3. Add water, cook for 45 minutes\\n4. Serve",
   "ingredients": [
      {
         "id": "a2e3a1b5-9a52-449c-a003-cf4b95968cbc",
         "name": "Pork",
         "description": null,
         "createdDate": null,
         "updatedDate": null
      }
   ],
   "dietTypes": [
      {
         "id": "0f714133-0b1d-4be6-b354-cf0ce3ace0b0",
         "name": "VEGETARIAN",
         "description": null,
         "createdDate": "2026-03-07T22:08:59.668351Z",
         "updatedDate": null
      }
   ]
}
```
---
### Find Recipe
`GET /recipe/{recipeId}`

**Request Path Parameter**

| field        | type   | description                                  | required? (Yes/Optional/Conditionally Required) |
|--------------|--------|----------------------------------------------|-------------------------------------------------|
| recipeId     | UUID   | Unique identifier of the recipe              | Yes                                             |

**Sample Request**

`GET /recipe/6e753255-0ee9-4147-a531-ea99049d6358`

**Sample Response**

`200 OK`

```json
{
   "id": "6e753255-0ee9-4147-a531-ea99049d6358",
   "title": "Pork Adobo",
   "description": "Filipino Staple Food",
   "servings": 4,
   "instructions": "1. Mix all ingredients together\\n2. Cook until meat is brown\\n3. Add water, cook for 45 minutes\\n4. Serve",
   "ingredients": [
      {
         "id": "a2e3a1b5-9a52-449c-a003-cf4b95968cbc",
         "name": "Pork",
         "description": null,
         "createdDate": "2026-03-07T21:25:43.305307Z",
         "updatedDate": null
      }
   ],
   "dietTypes": [
      {
         "id": "0f714133-0b1d-4be6-b354-cf0ce3ace0b0",
         "name": "VEGETARIAN",
         "description": null,
         "createdDate": "2026-03-07T22:08:59.668351Z",
         "updatedDate": null
      }
   ]
}
```
---
### Update Recipe
`PATCH /recipe/{recipeId}`

**Request Path Parameter**

| field        | type   | description                                  | required? (Yes/Optional/Conditionally Required) |
|--------------|--------|----------------------------------------------|-------------------------------------------------|
| recipeId     | UUID   | Unique identifier of the recipe              | Yes                                             |

**Request Body**

| field        | type          | description                                                | required? (Yes/Optional/Conditionally Required) |
|--------------|---------------|------------------------------------------------------------|-------------------------------------------------|
| title        | string        | title of recipe                                            | Optional                                        |
| servings     | number        | number of servings                                         | Optional                                        |
| description  | string        | description of the recipe                                  | Optional                                        |
| ingredients  | Ingredient [] | ingredients in this recipe. Must follow Ingredient schema  | Optional                                        |
| diet_type    | Diet Type []  | diet types of the recipe. Must follow the Diet Type schema | Optional                                        |
| instructions | string        | instructions of recipe                                     | Optional                                        |

**Sample Request**

`PATCH /recipe/6e753255-0ee9-4147-a531-ea99049d6358`
> API supports updating of a specific field only
```json
{
   "title": "Pig Adobo"
}
```

**Sample Response**

`200 OK`

```json
{
   "id": "6e753255-0ee9-4147-a531-ea99049d6358",
   "title": "Pig Adobo",
   "description": "Filipino Staple Food",
   "servings": 4,
   "instructions": "1. Mix all ingredients together\\n2. Cook until meat is brown\\n3. Add water, cook for 45 minutes\\n4. Serve",
   "ingredients": [
      {
         "id": "a2e3a1b5-9a52-449c-a003-cf4b95968cbc",
         "name": "Pork",
         "description": null,
         "createdDate": "2026-03-07T21:25:43.305307Z",
         "updatedDate": null
      }
   ],
   "dietTypes": [
      {
         "id": "0f714133-0b1d-4be6-b354-cf0ce3ace0b0",
         "name": "VEGETARIAN",
         "description": null,
         "createdDate": "2026-03-07T22:08:59.668351Z",
         "updatedDate": null
      }
   ]
}
```
---
### Delete Recipe
`DELETE /recipe/{recipeId}`

**Request Path Parameter**

| field        | type   | description                                  | required? (Yes/Optional/Conditionally Required) |
|--------------|--------|----------------------------------------------|-------------------------------------------------|
| recipeId     | UUID   | Unique identifier of the recipe              | Yes                                             |

**Sample Request**

`DELETE /recipe/6e753255-0ee9-4147-a531-ea99049d6358`

**Sample Response**

`200 OK`

---
### Search Recipe
**Request Body**

| field             | type                 | description                                                  | required? (Yes/Optional/Conditionally Required) |
|-------------------|----------------------|--------------------------------------------------------------|-------------------------------------------------|
| searchCriteria    | SearchCriteria []    | List of `SearchCriteria`. Must follow Search Criteria schema | Yes                                             |

> **Parameter Guide**
> 
> - Use `text` for generic search. The endpoint will perform a weighted *full-text search* against recipe `title`, `description`, and `instruction` in that particular order.
> - Use `servings` to filter based on the number of servings the recipe yields.
> - Use `ingredients` to filter based on ingredient name.
> - Use `diet_types` to filter based on diet type name.

> Passing an empty `searchCriteria` will fetch ALL recipes.
> ```json
> {
>    "searchCriteria": []
> }
> ```



**Sample Request**

`POST /recipe/search`

```json
{
   "searchCriteria": [
      {
         "parameter": "text",
         "operation": "EQUALS",
         "values": ["Pork", "meat"]
      }
   ]
}
```

**Sample Response**

`200 OK`

```json
[
   {
      "id": "6e753255-0ee9-4147-a531-ea99049d6358",
      "title": "Pork Adobo",
      "description": "Filipino Staple Food",
      "servings": 4,
      "instructions": "1. Mix all ingredients together\\n2. Cook until meat is brown\\n3. Add water, cook for 45 minutes\\n4. Serve",
      "ingredients": [
         {
            "id": "a2e3a1b5-9a52-449c-a003-cf4b95968cbc",
            "name": "Pork",
            "description": null,
            "createdDate": "2026-03-07T21:25:43.305307Z",
            "updatedDate": null
         }
      ],
      "dietTypes": []
   }
]
```
---
### API Schema
#### Ingredient Schema

| field       | type      | description                                                                             |
|-------------|-----------|-----------------------------------------------------------------------------------------|
| name        | string    | Name for the ingredient                                                                 |
| description | string    | Description for additional information about the ingredient                             |

#### Diet Type Schema

| field       | type      | description                                                   |
|-------------|-----------|---------------------------------------------------------------|
| name        | string    | Name for the diet type                                        |
| description | string    | Description for additional information about the diet type    |

#### Search Criteria Schema

| field       | type      | description                                                                                  |
|-------------|-----------|----------------------------------------------------------------------------------------------|
| parameter   | string    | property to search/filter (accepted values: `text`, `servings`, `ingredients`, `diet_types`) |
| operation   | string    | comparator (accepted values: `EQUALS`, `IN`)                                                 |
| values      | string [] | values to compare against (if checking against one value, pass a list with one element)      |

---
## Database Specs
![./docs/diagrams/db_relationship_diagram.png](/docs/diagrams/db_relationship_diagram.png)
*Source file*: [/docs/diagrams/db_relationship_diagram_src.txt](./docs/diagrams/db_relationship_diagram_src.txt)

### Notes
> `recipe_ingredients`
> - a `conjunction table` showing the many-to-many relationship between
> a recipe and an ingredient.
> 
> *A recipe can have multiple ingredients and an ingredient can be
> used in multiple recipes.*

> `diet_types`
> - a `conjunction table` showing the many-to-many relationship between
> a recipe and its diet types
> - this can also support the creation of a user's own diet type when
> they want to create a customized diet for themselves.
>
> *A recipe can be part of many diet types (e.g. Vegetarian, Ketone, etc.) and a diet type can be
> used for multiple recipes* 

### Future implementations
1. Add `tags` for recipes making it easier for users to find and filter recipes
    - Examples: User's can a tag a recipe as: `#protein`, `#calorie_deficit`, `#carbo_loading`
2. Add `quantity` to ingredient conjunction table to represent quantity of that ingredient  for that particular recipe.
3. Optimize the full-text search by adding *indices (or indexes)* to the search vector
4. Optimize the conjunction tables b adding *indices (or indexes)* to the respective foreign keys
5. Unit tests and integration tests
6. `operation` field in Search feature is currently not used. Can be utilized for more flexible and powerful filtering (e.g. `GREATER_THAN`, `LESS_THAN`)
7. Search pagination and limit number of results

### References
1. https://recime.notion.site/ReciMe-Backend-Developer-Coding-Challenge-848bbf4e5e1c4e89ae42e742eebd98d5
2. https://www.datacamp.com/blog/many-to-many-relationship
3. https://iniakunhuda.medium.com/postgresql-full-text-search-a-powerful-alternative-to-elasticsearch-for-small-to-medium-d9524e001fe0
4. https://www.baeldung.com/jpa-many-to-many
5. https://www.baeldung.com/intro-to-querydsl
6. [Postman collection for testing](./docs/ReciMe_Coding_Challenge.postman_collection.json)







