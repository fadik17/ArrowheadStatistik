'use strict';

var 
	fs = require('fs'),
	url = require('url'),
	path = require('path'),
	http = require('http');

// Get root directory from command line, default is the current directory
var root = path.resolve(process.argv[2] || '.');

console.log('Static root dir: ' + root);

// Create server
var server = http.createServer(function (request, response){
		// Get path of URL, like '/css/bootstrap.css'
		var pathName = url.parse(request.url).pathname;
		
		// Get the corresponding local file path, like '/srv/www/css/bootstrap.css'
		var filePath = path.join(root, pathName);

		// Get the file status
		fs.stat(filePath, function (err, stats) {
			if( !err && stats.isFile()) {
			// No error and the file exists
			console.log('200 ' + request.url);

			// Send 200 responses
			response.writeHead(200);

			// Send file stream to response
			fs.createReadStream(filePath).pipe(response);
			}else{

			// Error or file not exists
			console.log('404 ' + request.url);

			// Send 404 response
			response.writeHead(404);
			response.end('404 Not Found');
			}
		});
  });

server.listen(8080);


console.log('Server is running at localhost.);
console.log('Server is running at http://127.0.0.1:8080/');
