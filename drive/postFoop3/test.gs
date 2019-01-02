function testSaveData() {
    try {
        var jsonString = '{"user" : {"name" : "Cesar Morales Garduño","userId" : "MOGC"},"client" : {"client" : "David Márquez Hernández","contact" : "Elsa Paula Gutiérrez","assignKey" : "FD1801-ASIGIOAN"},"foop3" : [{"num" : 1,"description" : "Desarrollo de aplicación Expediente RH y Produce","app" : "Expediente RH y Produce","begin" : "2018-12-01","end" : "2018-12-15","scheduledHour" : 152,"appliedHour" : 152},{"num" : 2, "description" : "Desarrollo de aplicación Expediente RH y Produce","app" : "Expediente RH y Produce","begin" : "2018-12-16","end" : "2018-12-31","scheduledHour" : 120,"appliedHour" : 120}]}';
        var json = JSON.parse(jsonString);
        saveData(json);
    } catch (ex) {
        Logger.log(ex);
    }
}

function testCreatePDF() {
    try {
        var userId = "MOGC";
        var date = new Date();
        
        var path = createPdfFile(userId, date);
        Logger.log(path);
    } catch (ex) {
        Logger.log(ex);
    }
}

function testGetShareUrl() {
    var path = "FOOP 003 MOGC 2019-01.pdf";
    Logger.log(path);
    try {
        var url = getFileUrl(path);
        Logger.log(url);
    } catch (ex) {
        Logger.log(ex);
    }
}

function testObtenerNombreArchivo() {
    var userId = "MOGC";
    var dateStr = new Date();
    var fileName = obtenerNombreArchivo(userId, dateStr);
    Logger.log(fileName)
}

function testCleanData() {
    cleanData();
}