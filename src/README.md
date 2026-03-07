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

| field        | type   | description                                  | required? (Yes/Optional/Conditionally Required) |
|--------------|--------|----------------------------------------------|-------------------------------------------------|
| title        | string | title of recipe                              | Yes                                             |
| servings     | string | number of servings                           | Yes                                             |
| description  | string | description of the recipe                    | Yes                                             |
| diet_type    | string | diet type of the recipe                      | Optional                                        |
| instructions | string | instructions of recipe (in RichText format*) | Yes                                             |

**Sample Request**

`POST /recipe`
```json
{
  "title": "Pork Adobo",
  "servings": "3-4",
  "description": "Filipino Staple Food",
  "diet_type": "",
  "instructions": "1. Mix all ingredients together\\n2. Cook until meat is brown\\n3. Add water, cook for 45 minutes\\n4. Serve"
}
```

**Sample Response**

`201 CREATED`

```json
TODO
```
---
### Find Recipe
`GET /recipe/{recipeId}`

**Request Path Parameter**

| field        | type   | description                                  | required? (Yes/Optional/Conditionally Required) |
|--------------|--------|----------------------------------------------|-------------------------------------------------|
| recipeId     | UUID   | Unique identifier of the recipe              | Yes                                             |

**Sample Request**

`GET /recipe/2c24bcc0-ac04-4b97-b157-7acaebb6153d`

**Sample Response**

`200 OK`

```json
TODO
```
---
### Update Recipe
`PATCH /recipe/{recipeId}`

**Request Path Parameter**

| field        | type   | description                                  | required? (Yes/Optional/Conditionally Required) |
|--------------|--------|----------------------------------------------|-------------------------------------------------|
| recipeId     | UUID   | Unique identifier of the recipe              | Yes                                             |

**Request Body**

| field        | type   | description                                  | required? (Yes/Optional/Conditionally Required) |
|--------------|--------|----------------------------------------------|-------------------------------------------------|
| title        | string | title of recipe                              | Optional                                        |
| servings     | string | number of servings                           | Optional                                        |
| description  | string | description of the recipe                    | Optional                                             |
| diet_type    | string | diet type of the recipe                      | Optional                                        |
| instructions | string | instructions of recipe (in RichText format*) | Optional                                             |

**Sample Request**

`PATCH /recipe/2c24bcc0-ac04-4b97-b157-7acaebb6153d`
> API supports updating of a specific field only
```json
{
  "title": "Adobong Baboy",
  "diet_type": "LOW_CARB"
}
```

**Sample Response**

`200 OK`

```json
TODO
```
---
### Delete Recipe
`DELETE /recipe/{recipeId}`

**Request Path Parameter**

| field        | type   | description                                  | required? (Yes/Optional/Conditionally Required) |
|--------------|--------|----------------------------------------------|-------------------------------------------------|
| recipeId     | UUID   | Unique identifier of the recipe              | Yes                                             |

**Sample Request**

`DELETE /recipe/2c24bcc0-ac04-4b97-b157-7acaebb6153d`

**Sample Response**

`200 OK`

```json
TODO
```
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
1. Add `tags` for recipes making it easier for user's to find and filter recipes
    - Examples: User's can a tag a recipe as: `#protein`, `#calorie_deficit`, `#carbo_loading`







