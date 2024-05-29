db.mongo.insertOne({
    "name": "MongoDB",
    "type": "database",
    "count": 1,
    "info": {
        "x": 203,
        "y": 102
    }
});

db.mongo.find();

db.mongo.drop();

db.mongo.insertOne({
    "name": "MongoDB",
    "type": "database",
    "count": 1,
    "info": {
        "x": 203,
        "y": 102
    }
});

db.mongo.find({"name": "MongoDB"});

db.mongo.findOne({"name": "MongoDB"});

db.mongo.find().count();

db.mongo.updateOne(
    {"name": "MongoDB"},
    {$set: {"count" : 10}}
);

db.mongo.deleteOne({
    "name": "MongoDB"
});

