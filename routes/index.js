var express = require('express');
var router = express.Router();
var fs = require('fs');
const DIR = './files/';

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

router.get('/file', function(req, res, next) {
    if(!req.query.file_name) {
        res.status(400).send("Missing file name.");
        return;
    }

    let file_name = req.query.file_name;
    var file_data = DIR + file_name;
    //sends binary data back to client...easy!
    res.download(file_data);
});

router.post('/upload', function(req, res, next) {
    //error, no file specified
    if(!req.files || !req.files.upload_file) {
        res.status(400).send("Error upload file: Bad request.");
        return;
    }


    let uploaded_file = req.files.upload_file;
    let file_name = uploaded_file.name;

    //change location if needed

    //if directory "files" does not exist, create it
    if(!fs.existsSync(DIR)){
        fs.mkdirSync(DIR);
    }

    //move file to directory
    uploaded_file.mv(DIR + file_name, function(err) {
        if(err) {
            res.status(500).send(err);
            return;
        }
        res.status(200).send("File " + file_name + " uploaded."); 
    });
});

module.exports = router;
