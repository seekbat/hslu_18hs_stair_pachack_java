# hslu_18hs_stair_pachack_java

## Pachack-java

A basic snakehack starter kit written in java using maven, grizzly and jersey.


### How to start

1) [Fork this repo](https://github.com/CryDeb/hslu_18hs_stair_pachack_java/fork).

2) Clone repo to your development environment:
```
git clone git@github.com:USERNAME/hslu_18hs_stair_pachack_java.git $GOPATH/github.com/USERNAME/hslu_18hs_stair_pachack_java
cd $GOPATH/github.com/USERNAME/hslu_18hs_stair_pachack_java
```

3) Import the project into your ide. This repos uses intellij.

4) Try to run it
6) Test the client in your browser: [http://127.0.0.1:8080/start](http://127.0.0.1:8080/start)

### Heroku
[![Deploy](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)

#### Heroku-cli commands
download heroku cli
https://devcenter.heroku.com/articles/getting-started-with-java#set-up

login to heroku
```
heroku login
```
create new heroku app, `--region eu` is important for fast enough response times
```
heroku create yourfancyname --region eu
```
push to heroku
```
git push heroku master
```
delete heroku git
```
git remote rm heroku
```

