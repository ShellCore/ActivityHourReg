function getUserId(contents) {
    if (typeof contents.user === 'undefined') {
        throw ("User data not found in request");
    }
    var user = contents.user;
    if (typeof user.userId === 'undefined') {
        throw ("User id not found in request");
    }
    
    return user.userId;
}
