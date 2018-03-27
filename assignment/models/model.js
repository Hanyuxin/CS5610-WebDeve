var mongoose = require('mongoose');
//used in local machine
// var db = mongoose.connect('mongodb://localhost:27017/webdev');

//used in heroku
var db = mongoose.connect('mongodb://yuxinhan:123456@ds111138.mlab.com:11138/heroku_kjm6g01l');

module.exports = db;
