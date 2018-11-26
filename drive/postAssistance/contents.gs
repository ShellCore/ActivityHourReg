function getUserId(contents) {
    if (typeof contents.user === 'undefined') {
        throw ("User not defined in JSON");
    }
    var user = contents.user;
    if (typeof user.userId === 'undefined') {
        throw ("User id not defined in JSON");
    }
    
    return user.userId;
}

function getDate(contents) {
    if (typeof contents.month === 'undefined') {
        throw ("Date register not found");
    }
    
    var month = contents.month;
    var day = month[0];
    
    if (day == null) {
        throw ("No register found");
    }
    
    return day.day;
}
