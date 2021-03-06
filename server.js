var express = require('express');

const bodyParser = require('body-parser');
const app = express();
const path = require('path');
const http = require('http');

var passport = require('passport');
var cookieParser = require('cookie-parser');
var session      = require('express-session');

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.use(express.static(path.join(__dirname, 'dist')));
app.use(express.static(path.join(__dirname, 'src/assets')));

app.use(session({
  secret: 'this is the secret',
  resave: true,
  saveUninitialized: true
}));
app.use(cookieParser());

app.use(passport.initialize());
app.use(passport.session());

//CORS
app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "https://cs5610-webdev-yuxinhan.herokuapp.com");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  res.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
  res.header("Access-Control-Allow-Credentials", "true");
  next();
});


const port=process.env.PORT || '3100';
app.set('port', port);

// Create HTTP server
const server = http.createServer(app);


require("./assignment/app.js")(app);

// For Build: Catch all other routes and return the index file -- BUILDINg
app.get('*', function (req, res) {
  res.sendFile(path.join(__dirname, 'dist/index.html'));
});


server.listen( port , function() {
  console.log('Node app is running on port', app.get('port'))});
