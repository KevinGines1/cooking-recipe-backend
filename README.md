# Challenge ReciMe Backend (CMB)

Challenge ReciMe Backend or `Cooking Recipe Backend` (CMB) is a RESTful Backend service
that provides the creation, retrieval, search, deletion, and update of a cooking recipe.

- [Service Technical Specifications](#service-technical-specs)
- [Setup Guide](#setup-guide)
- [API Specifications](#api-specs)
- [Database Specifications](#database-specs)

## Service Technical Specs
- Java Spring Boot v3.5.11
- Java 17

## Setup Guide
> TODO
## API Specs
### Request Headers
> ⚠ These request headers are required for all types of requests.

| field        | type | description                                                                                                               | required? (Yes/Optional/Conditionally Required) |
|--------------|------|---------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------|
| x-request-id | UUID | identifier for the request coming in to the service<br><br>*This will be helpful in tracing process flows in the service* | Yes                                             |
---
### Create Recipe

**Request Body**

| field        | type      | description                                                | required? (Yes/Optional/Conditionally Required) |
|--------------|-----------|------------------------------------------------------------|-------------------------------------------------|
| title        | string    | title of recipe                                            | Yes                                             |
| servings     | number    | number of servings                                         | Yes                                             |
| description  | string    | description of the recipe                                  | Optional                                        |
| ingredients  | string [] | ingredients in this recipe. Must follow Ingredient schema  | Yes                                             |
| diet_type    | string [] | diet types of the recipe. Must follow the Diet Type schema | Optional                                        |
| instructions | string    | instructions of recipe (in RichText format*)               | Yes                                             |

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

| field        | type      | description                                                | required? (Yes/Optional/Conditionally Required) |
|--------------|-----------|------------------------------------------------------------|-------------------------------------------------|
| title        | string    | title of recipe                                            | Optional                                        |
| servings     | number    | number of servings                                         | Optional                                        |
| description  | string    | description of the recipe                                  | Optional                                        |
| ingredients  | string [] | ingredients in this recipe. Must follow Ingredient schema  | Optional                                        |
| diet_type    | string [] | diet types of the recipe. Must follow the Diet Type schema | Optional                                        |
| instructions | string    | instructions of recipe (in RichText format*)               | Optional                                        |

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

| field       | type      | description                                 | required? (Yes/Optional/Conditionally Required) |
|-------------|-----------|---------------------------------------------|-------------------------------------------------|
| parameter   | string    | property to search/filter                   | Yes                                             |
| operation   | string    | comparator ("EQUALS", "IN")                 | Yes                                             |
| value       | string    | value to compare against                    | Yes                                             |
| values      | string [] | values to compare against                   | Yes                                             |

**Sample Request**

`POST /recipe/search`

```json
{
   "searchCriteria": [
      {
         "parameter": "text",
         "operation": "EQUALS",
         "values": "Adobo"
      }
   ]
}
```

**Sample Response**

`200 OK`

```json
TODO
```
---
---
## Database Specs
![./docs/diagrams/db_relationship_diagram.png](/docs/diagrams/db_relationship_diagram.png)
*Source file*: `/docs/diagrams/db_relationship_diagram_src.txt`

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







