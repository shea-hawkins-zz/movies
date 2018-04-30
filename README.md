# movie service

## Running

Run this using [sbt](http://www.scala-sbt.org/). There is a prepacked version that is included with the repo, and it can be run using a bash command.

```bash
sbt run
```

or

```bash
bash ./sbt run
```

And then go to <http://localhost:9000> to see the running web application.

To run tests:
```bash
sbt test
```


## Routes


# GET star_wars/movies
Returns the list of movies grouped by director

# GET star_wars/characters/:movie_id
Returns the list of characters given a certain movie
