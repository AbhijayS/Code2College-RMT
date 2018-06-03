const express = require('express')
var fs = require('fs')
const path = require('path')
const exphbs = require('express-handlebars')
var bodyparser = require('body-parser')
const app = express()
const faker = require('faker');

var optWeights = JSON.parse(fs.readFileSync('option-weights.json', 'utf8'));
var quesWeights = JSON.parse(fs.readFileSync('question-weights.json', 'utf8'));

var Student = class {
	constructor(first, last, school) {
		this.name = first+" "+last;
		this.school = school;
	}
	calculateDropOutFactor(raw) {
		if(0<=raw && raw<=4){
			this.dropOutFactor = 'A';
			return 'A';
		}
		else if(5<=raw && raw<=9){
			this.dropOutFactor = 'B';
			return 'B';
		}
		else if(10<=raw && raw<=14){
			this.dropOutFactor = 'C';
			return 'C';
		}
		else if(15<=raw && raw<=19){
			this.dropOutFactor = 'D';
			return 'D';
		}
		else if(20<=raw && raw<=22){
			this.dropOutFactor = 'F';
		 return 'F';
		}
		else{
			this.dropOutFactor = null;
			return null;
		}
  }
	calculateChangeDegreeFactor(newDegree){
		this.changeDegreeFactor = newDegree;
	}
	toString(){
		return this.name+"\n"+"Drop Out Risk: " + this.dropOutFactor + "\n" + "Change Degree Risk: " + this.changeDegreeFactor+"\n";
	}
};

var gsjson = require('google-spreadsheet-to-json');
// Create App Instance
var hbs = exphbs({
	extname: '.html', //set extension to .html so handlebars knows what to look for
});
app.engine('html', hbs);
app.set('view engine', 'html');
app.use('/', express.static(path.join(__dirname, 'public')))
app.use(bodyparser.urlencoded({extended:false}));
app.use(bodyparser.json());

app.get('/', function(req, res){
	res.render('code2college');

})

app.get('/view-details', function(req, res){
	var studentList = [];
	var fakeNames = [];
	gsjson({
		spreadsheetId: '1gV6eBWqUHXndSvco8c6TLY9q-tjnyHk-toUFzouEGE8',
		// other options...
	})
	.then(function(data) {
		console.log("Data Fetched");
		var students = data;
		for(var i = 0; i < students.length; i++){
			var newStud = new Student(students[i].firstName, students[i].lastName, students[i].school);
			var rawScore = 0;
			for(var j = 0; j < quesWeights.length; j++){
				var answer = students[i][Object.keys(quesWeights[j])[0]];
				// console.log(answer);
				for(var k = 0; k < optWeights.length; k++){
					if(optWeights[k][answer] != null) {
						var weight = (quesWeights[j][(Object.keys(quesWeights[j])[0])])*optWeights[k][answer];
						if(weight > -1){
							rawScore+=weight;
						}else{
							newStud.calculateChangeDegreeFactor(optWeights[k][answer]);
						}
					}
				}
			}
			newStud.calculateDropOutFactor(rawScore);
			newStud.fakeName = faker.name.findName();
			studentList.push(newStud);
		}
		for(var i = 0; i < studentList.length; i++){
			console.log(studentList[i].toString());
		}
		res.render('details', {"students": studentList, "fakeNames": fakeNames});
	})
	.catch(function(err) {
		console.log(err.message);
		console.log(err.stack);
	});
})
app.listen(80, () => console.log('Example app listening on port 80!'))
