conn = new Mongo();

db = conn.getDB("test");

db.prueba.createIndex({"address.zip": 1}, {unique: false});

db.prueba.insert({"address": {"city": "Paris", "zip": "123"}, "name": "Mike", "phone": "1234"});
db.prueba.insert({"address": {"city": "Marsel", "zip": "321"}, "name": "Helga", "phone": "4321"});

db = conn.getDB("tenistas");
db.dropDatabase();


