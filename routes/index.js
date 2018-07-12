var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

router.get('/file', function(req, res, next) {
    let file_name = req.query.file_name;
    var file_data = './' + file_name;
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
    uploaded_file.mv('./' + file_name, function(err) {
        if(err) {
            res.status(500).send(err);
            return;
        }
        res.status(200).send("File " + file_name + " uploaded."); 
    });
});

module.exports = router;
